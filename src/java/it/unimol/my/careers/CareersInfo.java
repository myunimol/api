package it.unimol.my.careers;

import java.util.List;

public class CareersInfo {
	private String result = "success";
	private List<CareerInfo> careers;
	
	public CareersInfo(List<CareerInfo> careers) {
		this.careers = careers;
	}
	
	public List<CareerInfo> getCareers() {
		return careers;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setCareers(List<CareerInfo> careers) {
		this.careers = careers;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
}
