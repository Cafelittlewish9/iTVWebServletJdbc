package model.service.restful;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.BroadcastOrderDAO;
import model.dao.jdbc.BroadcastOrderDAOjdbc;
import model.vo.BroadcastOrderVO;
@Path("/broadcastOrder")
public class BroadcastOrderRestful {
	private BroadcastOrderDAO dao;

	public BroadcastOrderRestful() {
		this.dao = new BroadcastOrderDAOjdbc();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public BroadcastOrderVO createBroadcast(BroadcastOrderVO bean) {
		BroadcastOrderVO result = null;
		int temp = dao.insert(bean.getMemberAccount(), bean.getBroadcastWebsite(), bean.getBroadcastTitle(),
				bean.getBroadcastTime());
		if (temp == 1) {
			bean = dao.selectByMemberAccount(bean.getMemberAccount());
		}
		return result;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<BroadcastOrderVO> broadcastOrder() {
		return dao.selectAll();
	}
	@GET
	@Path("/list/{keyword}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<BroadcastOrderVO> searchBroadcast(@PathParam("keyword")String keyword) {
		Collection<BroadcastOrderVO> list = null;
		if (keyword != null && keyword.trim().length() != 0) {
			list = dao.selectByMemberAccountOrBroadcastTitle(keyword, keyword);
		}
		return list;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean changeTitle(BroadcastOrderVO bean) {
		int temp = dao.update(bean.getBroadcastTitle(), bean.getMemberAccount());
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}
	@DELETE
	@Path("/{bean}")
	public boolean removeBroadcast(@PathParam("bean")BroadcastOrderVO bean) {
		return dao.delete(bean.getMemberAccount());
	}
}
