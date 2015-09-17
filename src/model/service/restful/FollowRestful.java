package model.service.restful;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.FollowDAO;
import model.dao.jdbc.FollowDAOjdbc;
import model.vo.FollowVO;
@Path("/follow")
public class FollowRestful {
	private FollowDAO dao;

	public FollowRestful() {
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

	public boolean unfollow(int memberId, int followId) {
		return dao.delete(memberId, followId);
	}
	@GET
	@Path("/list/{memberId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<FollowVO> followList(@PathParam("memberId")int memberId) {
		return dao.selectByMemberId(memberId);
	}
}
