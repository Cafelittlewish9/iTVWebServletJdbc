package model.service.restful;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.LoginDAO;
import model.dao.jdbc.LoginDAOjdbc;
import model.vo.LoginVO;
@Path("/login")//這是RESTful，不是servlet，這個不是url-pattern
public class LoginRestful {
	private LoginDAO dao;

	public LoginRestful(){
		this.dao=new LoginDAOjdbc();
	}
	
	public boolean login(String memberAccount, String ip) {
		LoginVO bean = new LoginVO();
		bean.setMemberAccount(memberAccount);
		bean.setIp(ip);
		return dao.insert(bean);
	}
	
	@GET
	@Path("/loginlog/{memberAccount}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<LoginVO> loginLog(@PathParam("memberAccount")String memberAccount) {
		return dao.selectAll(memberAccount);
	}
	
	@GET
	@Path("/lastLogin/{memberAccount}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public java.util.Date lastLogin(@PathParam("memberAccount")String memberAccount) {
		java.util.Date result = null;
		if (memberAccount != null && memberAccount.trim().length() != 0) {
			LoginVO bean = dao.select(memberAccount);
			if (bean != null) {
				result = bean.getLoginTime();
			}
		}
		return result;
	}
}
