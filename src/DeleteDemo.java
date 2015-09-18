import model.dao.jdbc.ReportReplyArticleDAOjdbc;
import model.dao.jdbc.ReportVideoDAOjdbc;
import model.dao.jdbc.ShowDAOjdbc;
import model.dao.jdbc.VideoCommentsDAOjdbc;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.ReportReplyArticleVO;
import model.vo.ReportVideoVO;
import model.vo.ShowVO;
import model.vo.VideoCommentsVO;
import model.vo.VideoVO;

public class DeleteDemo {

	public static void main(String[] args) {
		
//		※ReportMemberDAOJdbc
//		int id = 10;
//		ReportMemberDAOJdbc DAO = new ReportMemberDAOJdbc();
//		boolean bool = DAO.delete(id);
//		System.out.println(bool);
		
//		..............................................

		VideoDAOjdbc dao = new VideoDAOjdbc();
		VideoVO bean = new VideoVO();
		System.out.println(dao.delete(13));
		
		
	}

}
