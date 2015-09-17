import model.dao.jdbc.ReportMemberDAOjdbc;
import model.vo.ReportMemberVO;

public class InsertDemo {

	public static void main(String[] args) {

//		※ReportMemberDAOJdbc
		
//		ReportMemberDAOJdbc DAO = new ReportMemberDAOJdbc();
//		ReportMemberVO bean = new ReportMemberVO();
//		java.util.Date date = new java.util.Date();
//
//		bean.setOrderId(10);
//		bean.setReportedMemberId(3);
//		bean.setReportTime(date);
////		bean.setReportTime(java.sql.Date.valueOf("2015-06-07"));
//		bean.setReportReason("一直噴水柱");
//		ReportMemberVO count = DAO.insert(bean);
//
//		System.out.println(count);
		
//		.......................................
		
		
		ReportMemberDAOjdbc dao = new ReportMemberDAOjdbc();
		ReportMemberVO bean = new ReportMemberVO();
		bean.setReportedMemberId(4);
		bean.setReportReason("吵死了");
	
		System.out.println(dao.insert(bean));

	}

}
