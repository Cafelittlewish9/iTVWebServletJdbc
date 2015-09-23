package model.dao.jdbc;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.MemberDAO;
import model.vo.MemberVO;
import util.HibernateUtil;

public class MemberDAOjdbc implements MemberDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
//	private DataSource ds;
//
//	public MemberDAOjdbc() {
//		try {
//			Context ctx = new InitialContext();
//			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

//	private static final String INSERT = "INSERT INTO member (memberAccount,memberPassword,memberEmail,broadcastWebsite) VALUES (?, cast( ? as varbinary(50)), ?,?)";

	@Override
	public int insert(MemberVO member) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(member);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// 用戶只需要輸入密碼跟信箱，自動產生memberAccount和broadcastWebsite網址
	// 問題在於若不同人在不同網站申請相同帳號的信箱，後者會無法申請，因為自動產生的broadcastWebsite會重複
//	private static final String INSERT2 = "INSERT INTO member (memberEmail,memberPassword,memberAccount,broadcastWebsite) VALUES (?,cast( ? as varbinary(50)), ?,?)";

	@Override
	public int insert2(MemberVO member) {
		// 要先檢查bean是否為null
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(member);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String GET_MEMBER_LIST = "SELECT memberId,memberAccount, broadcastWebsite FROM member ORDER BY memberAccount";

	@Override
	public List<MemberVO> getMemberList() {
		List<MemberVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

//	private static final String GET_ID = "SELECT memberId FROM member WHERE memberAccount=?";

	@Override
	public int getId(String memberAccount) {
		int memberId = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("select memberId from MemberVO where memberAccount = ?").setParameter(0, memberAccount);
			memberId = (Integer) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return memberId;
	}

	// 新增
//	private static final String UPDATE = "UPDATE member SET memberPassword=?, memberEmail=?, memberFB=?, memberGoogle=?, memberTwitter=?, memberNickname=?,"
//			+ "memberBirthday=?,memberPhoto=?,memberSelfIntroduction=?,broadcastTitle=?,broadcastClassName=?,"
//			+ "broadcastTime=?,broadcastDescription=? WHERE memberId=?";

	@Override
	public int update(MemberVO member) {
		// 要先檢查bean是否為null
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(member);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String FIND_BY_PK = "SELECT memberId,memberAccount,memberEmail,memberFB,memberGoogle,memberTwitter,memberName,"
//			+ "memberNickname,memberBirthday,memberPhoto,memberRegisterTime,memberSelfIntroduction,"
//			+ "broadcastWebsite,broadcastTitle,broadcastClassName,broadcastTime,broadcastDescription,"
//			+ "broadcastWatchTimes FROM member WHERE memberId=?";

	@Override
	public MemberVO findByPK(int memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MemberVO bean = null;
		try {
			session.beginTransaction();
			bean = (MemberVO) session.get(MemberVO.class, memberId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	// 設定用戶是否被停權
//	private static final String SWITCH_SUSPEND = "UPDATE member SET suspendMember = ? WHERE memberId = ?";

	@Override
	public int switchSuspend(int memberId, boolean suspendRight) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MemberVO bean = (MemberVO) session.get(MemberVO.class, memberId);
			bean.setSuspendMember(suspendRight);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	public static final String MEMBER_NICNAME = "Select memberNickname from member where memberAccount =?";

	@Override
	public String getMemberNickname(String memberAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String memberNickname = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("select memberNickname from MemberVO where memberAccount = ?").setParameter(0, memberAccount);
			memberNickname = (String) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return memberNickname;
	}

//	public static final String MEMBER_ACCOUNT = "SELECT memberAccount FROM member WHERE memberAccount = ?";

	@Override
	public String getMemberAccount(String memberAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String memberAccountResult = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("select memberAccount from MemberVO where memberAccount = ?").setParameter(0, memberAccount);
			memberAccountResult = (String) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return memberAccountResult;
	}

//	private static final String PHOTO_OUT = "select memberPhoto from member where memberId=?";

	@Override
	public byte[] photoOut(int memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		byte[] memberPhoto = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("select memberPhoto from MemberVO where memberId = ?").setParameter(0, memberId);
			memberPhoto = (byte[]) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return memberPhoto;
	}

//	private static final String FIND_BY_MEMBER_ACCOUNT = "SELECT memberId,memberAccount,memberEmail,memberFB,memberGoogle,memberTwitter,memberName,"
//			+ "memberNickname,memberBirthday,memberPhoto,memberRegisterTime,memberSelfIntroduction,"
//			+ "broadcastWebsite,broadcastTitle,broadcastClassName,broadcastTime,broadcastDescription,"
//			+ "broadcastWatchTimes FROM member WHERE memberAccount=?";

	@Override
	public MemberVO findByMemberAccount(String memberAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MemberVO bean = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberVO where memberAccount = ?").setParameter(0, memberAccount);
			bean = (MemberVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public MemberVO findByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MemberVO bean = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberVO where memberEmail = ?").setParameter(0, email);
			bean = (MemberVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}
//	private static final String UPDATE_PHOTO = "UPDATE Member SET memberPhoto = ? WHERE memberAccount = ?";

	@Override
	public int updatePhoto(int memberId, byte[] photo) {
		int result = -1;
		MemberVO bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			bean = (MemberVO) session.get(MemberVO.class, memberId);
			bean.setMemberPhoto(photo);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args) throws SQLException, ParseException {

		MemberDAO temp = new MemberDAOjdbc();
		System.out.println(temp.findByMemberAccount("xikachu"));
		
	}

}