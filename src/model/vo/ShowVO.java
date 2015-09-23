package model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShowVO implements Serializable {
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private java.util.Date showTime;
	@XmlElement(required = true)
	private int videoId;
	@XmlElement(required = true)
	private VideoVO video;

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(showTime);
		return "節目網址: " + video.getVideoWebsite() + " (" + date + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ShowVO)) {
			return false;
		}
		ShowVO bean = (ShowVO) obj;
		return new EqualsBuilder().append(this.memberId, bean.getMemberId()).append(this.showTime, bean.getShowTime())
				.isEquals();

	}

	@Override
	public int hashCode() {
		 return new HashCodeBuilder()
                 .append(this.memberId)
                 .append(this.showTime)
                 .toHashCode();
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

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public VideoVO getVideo() {
		return video;
	}

	public void setVideo(VideoVO video) {
		this.video = video;
	}
}
