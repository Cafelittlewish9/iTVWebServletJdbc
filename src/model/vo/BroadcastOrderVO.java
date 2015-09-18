package model.vo;

public class BroadcastOrderVO {
	private String memberAccount;
	private String broadcastWebsite;
	private String broadcastTitle;
	private java.util.Date broadcastTime;
	private long broadcastWatchTimes;

	@Override
	public String toString() {
		return memberAccount + "標題: " + broadcastTitle + "網址: " + broadcastWebsite + System.lineSeparator();
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getBroadcastWebsite() {
		return broadcastWebsite;
	}

	public void setBroadcastWebsite(String broadcastWebsite) {
		this.broadcastWebsite = broadcastWebsite;
	}

	public String getBroadcastTitle() {
		return broadcastTitle;
	}

	public void setBroadcastTitle(String broadcastTitle) {
		this.broadcastTitle = broadcastTitle;
	}

	public java.util.Date getBroadcastTime() {
		return broadcastTime;
	}

	public void setBroadcastTime(java.util.Date broadcastTime) {
		this.broadcastTime = broadcastTime;
	}

	public long getBroadcastWatchTimes() {
		return broadcastWatchTimes;
	}

	public void setBroadcastWatchTimes(long broadcastWatchTimes) {
		this.broadcastWatchTimes = broadcastWatchTimes;
	}

}
