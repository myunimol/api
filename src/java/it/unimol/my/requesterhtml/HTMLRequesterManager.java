package it.unimol.my.requesterhtml;

import it.unimol.my.careers.CareersInfo;
import it.unimol.my.careers.CareersParser;
import it.unimol.my.careers.CareersParserManager;
import it.unimol.my.utils.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mashape.unirest.http.exceptions.UnirestException;


public class HTMLRequesterManager {
	public static final String NO_CAREER_ACCESS = "NOCAREERACCESS";
	private static HTMLRequesterManager manager;
	
	static {
		manager = new HTMLRequesterManager();
	}
	
	public static HTMLRequesterManager getManager() {
		return manager;
	}
	
	private Map<String, HTMLRequesterInterface> requesters;
	
	public HTMLRequesterManager() {
		this.requesters = new HashMap<String, HTMLRequesterInterface>();
	}
	
	public HTMLRequesterInterface getInstance(String pUsername, String pPassword, String pCareerId) 
			throws HTMLRequesterException {
		String careerId;
		try {
			if (pCareerId != null) {
				if (pCareerId.equals(NO_CAREER_ACCESS)) {
					careerId = null;
				} else {
					careerId = pCareerId;
				}
			} else {
				careerId = getFirstCareer(pUsername, pPassword);
			}
			
			String key = pUsername + "@" + StringUtils.md5(pUsername+pPassword);
			
			if (careerId != null)
				key += "." + StringUtils.md5(key + careerId);
			
			if (this.requesters.get(key) == null) {
				//Logouts all the other requester with the same username
				List<String> toRemove = new ArrayList<>();
				
				for (String requesterKey : this.requesters.keySet()) {
					if (requesterKey.startsWith(pUsername + "@")) {
						HTMLRequesterInterface requester = this.requesters.get(requesterKey);
						requester.logout(pUsername, pPassword);
						toRemove.add(requesterKey);
					}
				}
				
				for (String keyToRemove : toRemove) {
					this.requesters.remove(keyToRemove);
				}
				
				HTMLRequesterInterface requester = registerRequester(pUsername, pPassword, careerId);
				this.requesters.put(key, requester);
				
				return requester;
			} else {
				HTMLRequesterInterface requester = this.requesters.get(key);
				if (requester.isTimeout()) {
					System.out.println("Timed out " + pUsername);
					requester.logout(pUsername, pPassword);
					this.requesters.remove(key);
					
					requester = registerRequester(pUsername, pPassword, careerId);
					this.requesters.put(key, requester);
				}
				return requester;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new HTMLRequesterException("No MD5 algorithm!");
		}
	}
	
	private HTMLRequesterInterface registerRequester(String pUsername, String pPassword, String pCareerId) 
			throws HTMLRequesterException {
		HTMLRequesterInterface requester = new HTMLRequester();
		if (requester.connect(pUsername, pPassword)) {
			//If the student actually has more than a career
			if (pCareerId != null) {
				boolean setCareerResult = requester.setCareer(pUsername, pPassword, pCareerId);
				if (!setCareerResult) {
					throw new HTMLRequesterException("Unable to set the career");
				}
			}
		} else
			throw new HTMLRequesterException("Unable to connect");
		
		return requester;
	}
	
	private String getFirstCareer(String pUsername, String pPassword) throws HTMLRequesterException {
		try {
			CareersParser careers = CareersParserManager.getCareersParser();
			
			CareersInfo info = careers.getCareersIds(pUsername, pPassword);
			
			if (info.getCareers().size() > 0) {
				return info.getCareers().get(0).getId();
			} else
				return null;
		} catch (UnirestException e) {
			throw new HTMLRequesterException(e.getMessage());
		}
		
	}
	
	public HTMLRequester getInstance() {
		return new HTMLRequester();
	}
}
