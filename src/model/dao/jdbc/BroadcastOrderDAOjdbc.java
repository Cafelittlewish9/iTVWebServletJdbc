package model.dao.jdbc;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.BroadcastOrderDAO;
import model.vo.BroadcastOrderVO;
import util.GC;
import util.HibernateUtil;

public class BroadcastOrderDAOjdbc implements BroadcastOrderDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	// private static final String SELECT_ALL = "SELECT * FROM BroadcastOrder
	// ORDER BY broadcastWatchTimes DESC";

	@Override
	public List<BroadcastOrderVO> selectAll() {
		List<BroadcastOrderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from BroadcastOrderVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_MEMBERACCOUNT_OR_BROADCASTTITLE =
	// "SELECT * FROM BroadcastOrder WHERE memberAccount like ? OR
	// broadcastTitle like ?";

	@Override
	public List<BroadcastOrderVO> selectByMemberAccountOrBroadcastTitle(String memberAccount, String broadcastTitle) {
		List<BroadcastOrderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from BroadcastOrderVO WHERE memberAccount like ? OR broadcastTitle like ?")
					.setParameter(0, "%" + memberAccount + "%").setParameter(1, "%" + broadcastTitle + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_MEMBERACCOUNT = "SELECT * FROM
	// BroadcastOrder WHERE memberAccount = ?";

	@Override
	public BroadcastOrderVO selectByMemberAccount(String memberAccount) {
		BroadcastOrderVO bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from BroadcastOrderVO WHERE memberAccount = ?").setParameter(0,
					memberAccount);
			bean = (BroadcastOrderVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

//	private static final String INSERT = "INSERT INTO BroadcastOrder(memberAccount, broadcastWebsite, broadcastTitle, broadcastTime) VALUES(?, ?, ?, ?)";

	@Override
	public int insert(BroadcastOrderVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String UPDATE = "UPDATE BroadcastOrder SET broadcastTitle = ? WHERE memberAccount = ?";

	@Override
	public int update(BroadcastOrderVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String DELETE = "DELETE FROM BroadcastOrder WHERE memberAccount = ?";

	@Override
	public int delete(String memberAccount) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from BroadcastOrderVO where memberAccount = ?").setParameter(0, memberAccount);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		BroadcastOrderDAOjdbc dao = new BroadcastOrderDAOjdbc();
		System.out.println(dao.selectAll());
	}
}