package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.dao.ArticleDAO;
import model.dao.BroadcastOrderDAO;
import model.vo.ArticleVO;
import model.vo.BroadcastOrderVO;
import model.vo.MemberVO;
import util.ConvertType;
import util.GC;

public class BroadcastOrderDAOjdbc implements BroadcastOrderDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;
//	private DataSource ds;
	
//	public BroadcastOrderDAOjdbc(){
//		try {
//			Context ctx = new InitialContext();
//			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	private static final String SELECT_ALL = "SELECT * FROM BroadcastOrder ORDER BY broadcastWatchTimes DESC";
	
	
	@Override
	public List<BroadcastOrderVO> selectAll() {
		BroadcastOrderVO bcvo;
		List<BroadcastOrderVO> list = null;
		try (
//				Connection conn=ds.getConnection();
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();) {
			list = new ArrayList<BroadcastOrderVO>();
			while (rs.next()) {
				bcvo = new BroadcastOrderVO();
				bcvo.setMemberAccount(rs.getString("memberAccount"));
				bcvo.setBroadcastWebsite(rs.getString("broadcastWebsite"));
				bcvo.setBroadcastTitle(rs.getString("broadcastTitle"));
				bcvo.setBroadcastTime(ConvertType.convertToLocalTime(rs.getTimestamp("broadcastTime")));				
				bcvo.setBroadcastWatchTimes(rs.getLong("broadcastWatchTimes"));
				list.add(bcvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_MEMBERACCOUNT_OR_BROADCASTTITLE = "SELECT * FROM BroadcastOrder WHERE memberAccount like ? OR broadcastTitle like ?";

	@Override
	public List<BroadcastOrderVO> selectByMemberAccountOrBroadcastTitle(String memberAccount, String broadcastTitle) {
		BroadcastOrderVO bcvo = new BroadcastOrderVO();
		List<BroadcastOrderVO> list = null;
		try (
//				Connection conn=ds.getConnection();
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_MEMBERACCOUNT_OR_BROADCASTTITLE);) {			
			pstmt.setString(1, "%"+ memberAccount + "%");
			pstmt.setString(2, "%"+ broadcastTitle + "%");
			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<BroadcastOrderVO>();
			while (rs.next()) {
				bcvo = new BroadcastOrderVO();
				bcvo.setMemberAccount(rs.getString("memberAccount"));
				bcvo.setBroadcastWebsite(rs.getString("broadcastWebsite"));
				bcvo.setBroadcastTitle(rs.getString("broadcastTitle"));
				bcvo.setBroadcastTime(ConvertType.convertToLocalTime(rs.getTimestamp("broadcastTime")));				
				bcvo.setBroadcastWatchTimes(rs.getLong("broadcastWatchTimes"));
				list.add(bcvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	private static final String SELECT_BY_MEMBERACCOUNT = "SELECT * FROM BroadcastOrder WHERE memberAccount = ?";

	@Override
	public BroadcastOrderVO selectByMemberAccount(String memberAccount) {
		BroadcastOrderVO bean = null;
		try (
//				Connection conn=ds.getConnection();
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_MEMBERACCOUNT);) {			
			pstmt.setString(1, memberAccount );
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BroadcastOrderVO();
				bean.setMemberAccount(rs.getString("memberAccount"));
				bean.setBroadcastWebsite(rs.getString("broadcastWebsite"));
				bean.setBroadcastTitle(rs.getString("broadcastTitle"));
				bean.setBroadcastTime(ConvertType.convertToLocalTime(rs.getTimestamp("broadcastTime")));				
				bean.setBroadcastWatchTimes(rs.getLong("broadcastWatchTimes"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bean;
	}

	private static final String INSERT = "INSERT INTO BroadcastOrder(memberAccount, broadcastWebsite, broadcastTitle, broadcastTime) VALUES(?, ?, ?, ?)";

	@Override
	public int insert(String memberAccount, String broadcastWebsite, String broadcastTitle,
			java.util.Date broadcastTime) {
		int result = -1;
		try (
//				Connection conn=ds.getConnection();
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {
			pstmt.setString(1, memberAccount);
			pstmt.setString(2, broadcastWebsite);
			pstmt.setString(3, broadcastTitle);
			pstmt.setTimestamp(4, (java.sql.Timestamp)broadcastTime);
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;	
	}

	private static final String UPDATE = "UPDATE BroadcastOrder SET broadcastTitle = ? WHERE memberAccount = ?";

	@Override
	public int update(String broadcastTitle, String memberAccount) {
		int result = -1;
		try (
//				Connection conn=ds.getConnection();
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
			pstmt.setString(1, broadcastTitle);
			pstmt.setString(2, memberAccount);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM BroadcastOrder WHERE memberAccount = ?";

	@Override
	public boolean delete(String memberAccount) {
		boolean result = false;
		try (
//				Connection conn=ds.getConnection();
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			pstmt.setString(1, memberAccount);
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	// 測試程式
	public static void main(String[] args) throws SQLException, ParseException {
		BroadcastOrderDAO temp = new BroadcastOrderDAOjdbc();
		BroadcastOrderVO bcvo = new BroadcastOrderVO();
		System.out.println(temp.selectByMemberAccount("Pikachu"));


	}
}