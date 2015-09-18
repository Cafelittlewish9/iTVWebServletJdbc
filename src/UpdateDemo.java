import model.dao.jdbc.ArticleClassDAOjdbc;
import model.dao.jdbc.CloudDAOjdbc;
import model.dao.jdbc.VideoCommentsDAOjdbc;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.ArticleClassVO;
import model.vo.CloudVO;
import model.vo.VideoCommentsVO;
import model.vo.VideoVO;

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
		
//		........................................
		
		VideoDAOjdbc dao = new VideoDAOjdbc();
		dao.update(100000, 13);
		System.out.println();
		
		
	}

}
