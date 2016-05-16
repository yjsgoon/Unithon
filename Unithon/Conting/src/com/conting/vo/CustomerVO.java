package com.conting.vo;

/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */
public class CustomerVO {
	private String profileName;
	private String visit;
	private boolean chief;
	
	public String getVisit() {
		return visit;
	}
	
	public void setVisit(String visit) {
		this.visit = visit;
	}

	public Boolean getChief() {
		return chief;
	}

	public void setChief(Boolean chief) {
		this.chief = chief;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	@Override
	public String toString() {
		return "CustomerVO [profileName=" + profileName + ", visit=" + visit + ", chiefRoom=" + chief + "]";
	}
}
