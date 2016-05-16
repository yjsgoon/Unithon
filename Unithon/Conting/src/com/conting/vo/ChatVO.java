package com.conting.vo;


/**
 * 
 * @since 	2016. 2. 14.
 * @version	
 * @author 	Yoon JiSoo
 */
public class ChatVO {
	private String title;
	private String chatContent;
	private String profileName;
	private int chatTime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public int getChatTime() {
		return chatTime;
	}
	public void setChatTime(int chatTime) {
		this.chatTime = chatTime;
	}
	
	@Override
	public String toString() {
		return "ChatVO [title=" + title + ", chatContent=" + chatContent + ", profileName=" + profileName
				+ ", chatTime=" + chatTime + "]";
	}
}
