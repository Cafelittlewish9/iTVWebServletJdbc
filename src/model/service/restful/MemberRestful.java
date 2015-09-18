package model.service.restful;

import java.sql.SQLException;
import java.util.Arrays;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.dao.MemberDAO;
import model.dao.jdbc.MemberDAOjdbc;
import model.vo.MemberVO;

//註冊後轉update頁面要由controller處理
//查詢、修改個人資料、搜尋會員
@Path("/member")
public class MemberRestful {
	private MemberDAO dao;

	public MemberRestful() {
		this.dao = new MemberDAOjdbc();
	}

	// 會員查詢個資
	@GET
	@Path("/info/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public MemberVO checkInfo(@PathParam("username") String username, @PathParam("password") String password) {
		MemberVO mvo = this.login1(username, password);
		return mvo;
	}
	// ↑是否是指連查詢個資都要輸入一次帳密？

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public int update(MemberVO mvo) {
		int result = -1;
		if (mvo != null) {
			mvo.setMemberEmail(mvo.getMemberEmail());
			mvo.setMemberFB(mvo.getMemberFB());
			mvo.setMemberGoogle(mvo.getMemberGoogle());
			mvo.setMemberTwitter(mvo.getMemberTwitter());
			mvo.setMemberName(mvo.getMemberName());
			mvo.setMemberNickname(mvo.getMemberNickname());
			mvo.setMemberBirthday(mvo.getMemberBirthday());
			mvo.setMemberPhoto(mvo.getMemberPhoto());
			mvo.setMemberRegisterTime(mvo.getMemberRegisterTime());
			mvo.setMemberSelfIntroduction(mvo.getMemberSelfIntroduction());
			mvo.setBroadcastWebsite(mvo.getBroadcastWebsite());
			mvo.setBroadcastTitle(mvo.getBroadcastTitle());
			mvo.setBroadcastClassName(mvo.getBroadcastClassName());
			mvo.setBroadcastTime(mvo.getBroadcastTime());
			mvo.setBroadcastDescription(mvo.getBroadcastDescription());
			mvo.setBroadcastWatchTimes(mvo.getBroadcastWatchTimes());
			result = dao.update(mvo);
		}
		return result;
	}

	// registry1採帳號密碼信箱註冊；registry2是採信箱密碼註冊
	public String registry1(String username, String password, String usermail) throws SQLException {
		String result = null;
		MemberVO mvo = new MemberVO();
		if (username.length() == 0) {
			result = "Please keyin a username.";
		} else {
			if (dao.getId(username) != 0) {
				result = "Please change another username.";
			} else {
				mvo.setMemberAccount(username);
				mvo.setMemberPassword(password.getBytes());
				mvo.setMemberEmail(usermail);
				// mvo.setMemberRegisterTime(new java.util.Date());
				dao.insert(mvo);
				result = "success";
			}
		}
		return result;
	}

	// registry1採帳號密碼信箱註冊；registry2是採信箱密碼註冊
	public String registry2(String usermail, String password) throws SQLException {
		String result = null;
		MemberVO mvo = new MemberVO();
		if (usermail.length() == 0) {
			result = "Please keyin a your mail.";
		} else {
			if (dao.getId(usermail.substring(0, usermail.indexOf("@"))) != 0) {
				result = "Please change another mail address to registry.";
			} else {
				mvo.setMemberAccount(usermail.substring(0, usermail.indexOf("@")));
				mvo.setMemberPassword(password.getBytes());
				mvo.setMemberEmail(usermail);
				// mvo.setMemberRegisterTime(new java.util.Date());
				dao.insert2(mvo);
				result = "success";
			}
		}
		return result;
	}

	// login1是採用帳號登入；login2是採信箱登入
	public MemberVO login1(String username, String password) {
		int memberId = dao.getId(username);
		MemberVO mvo = dao.findByPK(memberId);
		if (mvo != null) {
			if (password != null && password.length() != 0) {
				byte[] memberPwd = mvo.getMemberPassword();
				byte[] temp = password.getBytes();
				if (Arrays.equals(memberPwd, temp)) {
					mvo = dao.findByPK(memberId);
					return mvo;
				}
			}
		}
		return mvo;
	}

	// login1是採用帳號登入；login2是採信箱登入
	public MemberVO login2(String usermail, String password) {
		int memberId = dao.getId(usermail.substring(0, usermail.indexOf("@")));
		MemberVO mvo = dao.findByPK(memberId);
		if (mvo != null) {
			if (password != null && password.length() != 0) {
				byte[] memberPwd = mvo.getMemberPassword();
				byte[] temp = password.getBytes();
				if (Arrays.equals(memberPwd, temp)) {
					mvo = dao.findByPK(memberId);
					return mvo;
				}
			}
		}
		return mvo;
	}

	// 會員修改密碼
	public String changePassword(String username, String oldPassword, String newPassword) {
		MemberVO mvo = this.login1(username, oldPassword);// 透過帳密拿到該會員的資料
		String result = "Error, please contact the administrator!";
		if (mvo != null) {
			byte[] temp = newPassword.getBytes();
			mvo.setMemberPassword(temp);
			if (dao.update(mvo) == 1) {
				result = "You've successfully changed your password!";
			}
		}
		return result;
	}

	// 用戶忘記密碼
	public String retrivePwd(String usermail) {
		String result = null;
		if (dao.getId(usermail.substring(0, usermail.indexOf("@"))) != 0) {
			double replace = Math.random() * 100;// 亂數產生一組數字去轉成md5回傳給使用者
			MemberVO mvo = new MemberVO();
			MemberRestful service = new MemberRestful();
			// String newPwd=md5("");
			// service.changePassword(usermail.substring(0,usermail.indexOf("@")),
			// new String(mvo.getMemberPassword()),newPwd);
			// CharsetEncoder
			result = "";
		} else {
		}
		return result;
	}

	/*
	 * public static String md5(String str) { String md5=null; try {
	 * MessageDigest md=MessageDigest.getInstance("MD5"); byte[]
	 * barr=md.digest(str.getBytes()); //將 byte 陣列加密 StringBuffer sb=new
	 * StringBuffer(); //將 byte 陣列轉成 16 進制 for (int i=0; i < barr.length; i++)
	 * {sb.append(byte2Hex(barr[i]));} String hex=sb.toString();
	 * md5=hex.toUpperCase(); //一律轉成大寫 } catch(Exception e)
	 * {e.printStackTrace();} return md5; }
	 * 
	 * public static String byte2Hex(byte b) { String[]
	 * h={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"}; int
	 * i=b; if (i < 0) {i += 256;} return h[i/16] + h[i%16]; }
	 */

	// 測完了，沒問題可以動呦吼吼。
	public int suspendMember(int memberId, boolean suspendRight) {
		int result = -1;
		if (memberId != 0) {
			result = dao.switchSuspend(memberId, suspendRight);
		}
		return result;
	}
}
