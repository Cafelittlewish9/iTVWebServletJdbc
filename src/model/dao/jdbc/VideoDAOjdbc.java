package model.dao.jdbc;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
 import model.dao.VideoDAO;
import model.vo.VideoCommentsVO;
import model.vo.VideoVO;
import util.HibernateUtil;

public class VideoDAOjdbc implements VideoDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
	// private DataSource datasource;
	//
	// public VideoDAOjdbc() {
	// try {
	// InitialContext context = new InitialContext();
	// this.datasource = (DataSource) context.lookup(GC.DATASOURCE);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	// private static final String SELECT_BY_VIDEOTITLE = "SELECT
	// v.*,m.memberAccount FROM video v Join member m ON v.memberId = m.memberId
	// WHERE videoTitle LIKE ?";

	@Override
	public List<VideoVO> selectByVideoTitle(String videoTitle) {
		List<VideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoVO where videoTitle like ?");
			query.setParameter(0, "%" + videoTitle + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_VIDEOCLASSNAME = "SELECT
	// v.*,m.memberAccount FROM video v Join member m ON v.memberId = m.memberId
	// WHERE videoClassName = ?";

	@Override
	public List<VideoVO> selectByVideoClassName(String videoClassName) {
		List<VideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoVO where videoClassName = ?");
			query.setParameter(0, videoClassName);
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_ALL = "SELECT v.*,m.memberAccount FROM
	// video v Join member m ON v.memberId = m.memberId ORDER BY videoWatchTimes
	// DESC";

	@Override
	public List<VideoVO> selectAll() {
		List<VideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String INSERT = "insert into video(memberId,
	// videoWebsite, videoClassName, videoTitle, videoName, videoPath,
	// videoWatchTimes, videoDescription) values (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public int insert(VideoVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String UPDATE_DESCRIPTION = "update video set
	// videoDescription = ?, videoDescriptionModifyTime = GETUTCDATE() where
	// videoId = ?";

	@Override
	public int update(VideoVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String UPDATE_WATCHTIMES = "update video set
	// videoWatchTimes = ? where videoId = ?";

	private static final String DELETE = "delete from video where videoId = ?";

	@Override
	public int delete(int videoId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from VideoVO where videoId = ?").setParameter(0, videoId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// SelectALL
		VideoDAO temp = new VideoDAOjdbc();
		List<VideoVO> list = temp.selectAll();
		for (VideoVO bean : list) {
			System.out.println(bean.getMember().getMemberAccount());
			Set<VideoCommentsVO> temp2 = bean.getVideoComments();
			for (VideoCommentsVO bean2 : temp2) {
				System.out.println(bean2.getCommentContent());
			}
		}
//		VideoVO bean4 = new VideoVO();
//		MemberVO xx = new MemberVO();
//		xx.setMemberId(2);
//		bean4.setMember(xx);
//		bean4.setVideoWebsite("xzzxc");
//		bean4.setVideoClassName("default");
//		bean4.setVideoTitle("1323");
//		bean4.setVideoName("好吃");
//		bean4.setVideoPath("CCCCCCCCCCCC");
//		bean4.setVideoUploadTime(new java.util.Date());
//		bean4.setVideoDescriptionModifyTime(new java.util.Date());
//		boolean i = temp.update("Cxvczxcdvasdvasd", 32);
//		System.err.println(i);
	}
}
