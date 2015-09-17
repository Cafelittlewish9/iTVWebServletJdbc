import model.dao.jdbc.ArticleClassDAOjdbc;
import model.dao.jdbc.CloudDAOjdbc;
import model.vo.ArticleClassVO;
import model.vo.CloudVO;

public class UpdateDemo {

	public static void main(String[] args) {
		
//		※ReportMemberDAOJdbc
//		ReportMemberVO bean = new ReportMemberVO();
//		ReportMemberDAOJdbc DAO = new ReportMemberDAOJdbc();
//		java.util.Date date = new java.util.Date();
//		
//		bean.setOrderId(10);
//		bean.setReportedMemberId(3);
////		bean.setReportTime(date);
//		bean.setReportTime(java.sql.Date.valueOf("2001-01-01"));
//		bean.setReportReason("一直噴柱柱");
//
//		DAO.update(bean.getReportedMemberId(), bean.getReportTime(), bean.getReportReason(), bean.getOrderId());
		
//		.......................................

		CloudDAOjdbc dao = new CloudDAOjdbc();
		CloudVO bean = new CloudVO();
		
		
		System.out.println(dao.updateFile("C:/file/Pikachu/1000000.doc", 4154788, 13));
		
		
		
		
		
	}

}
