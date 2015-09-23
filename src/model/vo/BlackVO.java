package model.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BlackVO implements Serializable {
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private int blackedId;
	@XmlElement(required = true)
	private MemberVO member;

	@Override
	public String toString() {
		return memberId + ": " + blackedId + " (被黑的)";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof BlackVO)) {
			return false;
		}
		BlackVO bean = (BlackVO) obj;
		return new EqualsBuilder()
				.append(this.memberId, bean.getMemberId())
				.append(this.blackedId, bean.getBlackedId())
				.isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.memberId).append(this.blackedId).toHashCode();
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getBlackedId() {
		return blackedId;
	}

	public void setBlackedId(int blackedId) {
		this.blackedId = blackedId;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
}
