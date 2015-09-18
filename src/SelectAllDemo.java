import java.util.List;

import model.dao.jdbc.VideoDAOjdbc;
import model.vo.VideoVO;

public class SelectAllDemo {

	public static void main(String[] args) throws Exception {
		
//		※ReportMemberDAOJdbc
		
//		ReportMemberDAOJdbc DAO = new ReportMemberDAOJdbc();
//		List<ReportMemberVO> beans= DAO.select();
//		for(ReportMemberVO bean:beans){
//		System.out.print(bean+"\n");
//		}
		    
//		..........................................
		
		VideoDAOjdbc dao = new VideoDAOjdbc();
		List<VideoVO> beans = dao.selectByVideoTitle("冬");
		for(VideoVO bean:beans){
			System.out.println(bean);
		}
		
		
	}

}
