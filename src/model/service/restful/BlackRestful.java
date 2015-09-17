package model.service.restful;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.BlackDAO;
import model.dao.jdbc.BlackDAOjdbc;
import model.vo.BlackVO;

/**
 * @author iTV小組成員
 *
 */
@Path("/black")
public class BlackRestful {
	private BlackDAO dao;

	/**
	 * 初始化BlackDAO
	 */
	public BlackRestful() {
		this.dao = new BlackDAOjdbc();
	}

	/**
	 * 增加黑名單
	 * @param memberId 黑別人的人
	 * @param blackedId 被黑的人
	 * @return Collection<BlackVO>
	 */	
	public boolean insertBlackList(int memberId, int blackedId) {
		return dao.markBlack(memberId, blackedId);
	}

	/**
	 * 查詢所有黑名單
	 * @param memberId 黑別人的人 
	 * @return Collection<BlackVO>
	 */
	@GET
	@Path("/list/{memberId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<BlackVO> searchBlackAccount(@PathParam("memberId")int memberId) {
		return dao.getList(memberId);
	}

	/**
	 * 刪除單一黑名單
	 * @param memberId 黑別人的人
	 * @param blackedId 被黑的人
	 * @return boolean
	 */
	public boolean removeBlackAccount(int memberId, int blackedId) {
		return dao.removeBlack(memberId, blackedId);
	}
	
	/**
	 * 刪除全部黑名單
	 * @param memberId 黑別人的人 
	 * @return boolean
	 */	
	public boolean removeAll(int memberId) {
		return dao.removeAll(memberId);
	}

	
	public static void main(String[] args) {
		BlackRestful service = new BlackRestful();
		boolean bool = service.insertBlackList(1, 3);
		System.out.println(bool);
		System.out.println(service.searchBlackAccount(1));

	}
}
