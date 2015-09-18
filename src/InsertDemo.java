import model.dao.jdbc.VideoDAOjdbc;
import model.vo.VideoVO;

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

		VideoDAOjdbc dao = new VideoDAOjdbc();
		VideoVO bean = new VideoVO();
		bean.setMemberId(1);
		bean.setVideoWebsite("http://www.iTV.com/video/Pikachu100");
		bean.setVideoClassName("生活");
		bean.setVideoTitle("TEST");
		bean.setVideoName("TEST");
		bean.setVideoPath("C:\\video\\Pikachu\\TEST.mp4");
		bean.setVideoDescription("TEST");
		
		System.out.println(dao.insert(bean));
		
	}

}
