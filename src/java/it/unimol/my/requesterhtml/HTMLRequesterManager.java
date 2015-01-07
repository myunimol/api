package it.unimol.my.requesterhtml;

import it.unimol.my.utils.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.helper.StringUtil;


public class HTMLRequesterManager {
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
	
	public HTMLRequesterInterface getInstance(String pUsername, String pPassword) {
		try {
			String key = pUsername + StringUtils.md5(pUsername+pPassword);
			if (this.requesters.get(key) == null) {
				HTMLRequesterInterface requester = registerRequester(key, pUsername, pPassword);
				
				return requester;
			} else {
				HTMLRequesterInterface requester = this.requesters.get(key);
				if (requester.isTimeout()) {
					System.out.println("Timed out " + pUsername);
					requester.logout(pUsername, pPassword);
					
					requester = registerRequester(key, pUsername, pPassword);
				}
				return requester;
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NO MD5 ALGORITHM!!!");
			return null;
		}
	}
	
	private HTMLRequesterInterface registerRequester(String pKey, String pUsername, String pPassword) {
		HTMLRequesterInterface requester = new HTMLRequester();
		if (requester.connect(pUsername, pPassword))
			this.requesters.put(pKey, requester);
		
		return requester;
	}
	
	public HTMLRequester getInstance() {
		return new HTMLRequester();
	}
}
