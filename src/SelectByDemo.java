import java.util.List;

import model.dao.jdbc.ChannelDAOjdbc;
import model.dao.jdbc.LoginDAOjdbc;
import model.vo.ChannelVO;
import model.vo.LoginVO;

public class SelectByDemo {

	public static void main(String[] args) {
		
//		※ReportMemberDAOJdbc
		
//		ReportMemberDAOJdbc DAO = new ReportMemberDAOJdbc();
//		ReportMemberVO bean = DAO.select(1);
//		System.out.println(bean);
//		
//		..........................................
		
		LoginDAOjdbc dao = new LoginDAOjdbc();
		System.out.println(dao.select("Pikachu"));
		
		
		
	}

}
