package com.conting.vo;

/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */
public class RoomVO {
	private String title;
	private int capacity;
	private int purpose;
	private int beginTime;
	private int endTime;
	private int lockDown;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getPurpose() {
		return purpose;
	}
	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}
	public int getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getLockDown() {
		return lockDown;
	}
	public void setLockDown(int lockDown) {
		this.lockDown = lockDown;
	}
	
	@Override
	public String toString() {
		return "RoomVO [title=" + title + ", capacity=" + capacity + ", purpose=" + purpose + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", lockDown=" + lockDown + "]";
	}
}
