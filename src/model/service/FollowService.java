package model.service;

import java.util.Collection;
import model.dao.FollowDAO;
import model.dao.jdbc.FollowDAOjdbc;
import model.vo.FollowVO;

public class FollowService {
	private FollowDAO dao;

	public FollowService() {
		this.dao = new FollowDAOjdbc();
	}

	public boolean follow(int memberId, int followId) {
		FollowVO bean = new FollowVO();
		bean.setMemberId(memberId);
		bean.setFollowId(followId);
		int result = dao.insert(bean);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	//!!!!是反過來的，例如輸入(1,2) 在資料庫裡會消失的是 "memberID:2 followID:1"!!!!!
	//感謝測試員，說明一下：因為sql指令的where條件確實是先寫followId才是memberId，2015/9/21已修正
	public boolean unfollow(int followId, int memberId) {
		return dao.delete(followId, memberId);
	}
	
	public Collection<FollowVO> followList(int memberId) {
		return dao.selectByMemberId(memberId);
	}
}
