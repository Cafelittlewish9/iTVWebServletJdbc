import java.util.List;

import model.dao.jdbc.ReportMemberDAOjdbc;
import model.dao.jdbc.ReportReplyArticleDAOjdbc;
import model.vo.ReportReplyArticleVO;

public class SelectAllDemo {

	public static void main(String[] args) throws Exception {
		
//		※ReportMemberDAOJdbc
		
//		ReportMemberDAOJdbc DAO = new ReportMemberDAOJdbc();
//		List<ReportMemberVO> beans= DAO.select();
//		for(ReportMemberVO bean:beans){
//		System.out.print(bean+"\n");
//		}
		    
//		..........................................
		
		ReportReplyArticleDAOjdbc dao = new ReportReplyArticleDAOjdbc();
		List<ReportReplyArticleVO> beans = dao.selectAll();
		for(ReportReplyArticleVO bean:beans){
			System.out.println(bean);
		}
		
		
	}

}
