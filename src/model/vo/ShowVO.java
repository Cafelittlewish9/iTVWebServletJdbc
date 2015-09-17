package model.vo;

import java.text.SimpleDateFormat;

public class ShowVO {
	private int memberId;
	private java.util.Date showTime;
	private String website;
	private VideoVO video;
	private MemberVO member;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(showTime);
		return "節目網址: " + website + " (" + date + ")";
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public java.util.Date getShowTime() {
		return showTime;
	}
	public void setShowTime(java.util.Date showTime) {
		this.showTime = showTime;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public VideoVO getVideo() {
		return video;
	}
	public void setVideo(VideoVO video) {
		this.video = video;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	
	
}
