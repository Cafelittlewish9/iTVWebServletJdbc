package model.service.restful;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.ChannelDAO;
import model.dao.jdbc.ChannelDAOjdbc;
import model.vo.ChannelVO;
@Path("/channel")
public class ChannelService {
	private ChannelDAO dao;

	public ChannelService() {
		this.dao = new ChannelDAOjdbc();
	}
	
	@GET
	@Path("/list/{memberId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<ChannelVO> allChannel(@PathParam("memberId")int memberId) {
		return dao.selectByMemberId(memberId);
	}

	public boolean addChannel(int memberId, byte channelNo, String broadcastWebsite) {
		ChannelVO bean = new ChannelVO();
		bean.setMemberId(memberId);
		bean.setChannelNo(channelNo);
		bean.setBroadcastWebsite(broadcastWebsite);
		int result = dao.insert(bean);
		if (result == 1) {
			return true;
		}
		return false;
	}

	public boolean addChannel(ChannelVO bean) {
		int result = dao.insert(bean);
		if (result == 1) {
			return true;
		}
		return false;
	}

	public boolean changeChannel(String broadcastWebsite, int memberId, byte channelNo) {
		int result = dao.update(broadcastWebsite, memberId, channelNo);
		if (result == 1) {
			return true;
		}
		return false;
	}

	public boolean changeChannel(ChannelVO bean) {
		int result = dao.update(bean.getBroadcastWebsite(), bean.getMemberId(), bean.getChannelNo());
		if (result == 1) {
			return true;
		}
		return false;
	}

	public boolean removeChannel(int memberId, byte channelNo) {
		return dao.delete(memberId, channelNo);
	}

	public boolean removeAllChannel(int memberId) {
		return dao.deleteAll(memberId);
	}
}
