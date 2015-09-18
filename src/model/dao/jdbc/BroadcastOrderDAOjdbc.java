package model.dao.jdbc;

import java.util.List;

import model.dao.BroadcastOrderDAO;
import model.vo.BroadcastOrderVO;
import util.GC;

public class BroadcastOrderDAOjdbc implements BroadcastOrderDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT * FROM BroadcastOrder ORDER BY broadcastWatchTimes DESC";

	@Override
	public List<BroadcastOrderVO> selectAll() {
		List<BroadcastOrderVO> list = null;
		return list;
	}

	private static final String SELECT_BY_MEMBERACCOUNT_OR_BROADCASTTITLE = "SELECT * FROM BroadcastOrder WHERE memberAccount like ? OR broadcastTitle like ?";

	@Override
	public List<BroadcastOrderVO> selectByMemberAccountOrBroadcastTitle(String memberAccount, String broadcastTitle) {
		List<BroadcastOrderVO> list = null;
		return list;
	}
	private static final String SELECT_BY_MEMBERACCOUNT = "SELECT * FROM BroadcastOrder WHERE memberAccount = ?";

	@Override
	public BroadcastOrderVO selectByMemberAccount(String memberAccount) {
		BroadcastOrderVO bean = null;
		return bean;
	}

	private static final String INSERT = "INSERT INTO BroadcastOrder(memberAccount, broadcastWebsite, broadcastTitle, broadcastTime) VALUES(?, ?, ?, ?)";

	@Override
	public int insert(String memberAccount, String broadcastWebsite, String broadcastTitle,
			java.util.Date broadcastTime) {
		int result = -1;
		return result;
	}

	private static final String UPDATE = "UPDATE BroadcastOrder SET broadcastTitle = ? WHERE memberAccount = ?";

	@Override
	public int update(String broadcastTitle, String memberAccount) {
		int result = -1;
		return result;
	}

	private static final String DELETE = "DELETE FROM BroadcastOrder WHERE memberAccount = ?";

	@Override
	public boolean delete(String memberAccount) {
		boolean result = false;
		return result;
	}
}