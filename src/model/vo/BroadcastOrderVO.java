package model.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BroadcastOrderVO {
	@XmlElement(required = true)
	private String memberAccount;
	@XmlElement(required = true)
	private String broadcastWebsite;
	@XmlElement(required = true)
	private String broadcastTitle;
	@XmlElement(required = true)
	private java.util.Date broadcastTime;
	@XmlElement(required = true)
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
