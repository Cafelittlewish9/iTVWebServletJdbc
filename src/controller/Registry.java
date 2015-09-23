package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.MemberService;
import model.vo.MemberVO;
import util.ConvertType;

@WebServlet("/registry.do")
public class Registry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;
	// 如有必要就連註冊時間也一併登錄於login table

	@Override
	public void init() throws ServletException {
		ms = new MemberService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		List<String> errorMsg = new ArrayList<String>();
		request.setAttribute("ErrorMsgKey", errorMsg);// 要與前端共享的錯誤訊息
		//接收資料
		String username = request.getParameter("memberAccount").toLowerCase();
//		String userRegExp = "^[a-zA-Z0-9]{6,20}$";// 帳號正規式驗證
		String password = request.getParameter("memberPassword");
//		String pwdRegExp = "^[a-zA-Z0-9]{8,20}$";// 密碼正規式驗證
		String usermail = request.getParameter("memberEmail");
//		String mailRegExp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]{2,4}$";//這個信箱驗證只要是有兩個點的都會出錯ex @yahoo.com.tw
		String broadcastWebsite = request.getParameter("broadcastWebsite");
		String nickname = request.getParameter("memberNickname");
		String birthday = request.getParameter("memberBirthday");
		System.out.println("0"+birthday);
		String operation = request.getParameter("operation");
		
		String path = request.getContextPath();
//		檢查資料
		if (username == null || username.trim().length() == 0) {
			errorMsg.add("請輸入帳號");
		} 
//			else if (!username.trim().matches(userRegExp)) {
//			errorMsg.add("帳號限英文字母、數字，長度必須在6-20之間");
//		}
		if (password == null || password.trim().length() == 0) {
			errorMsg.add("密碼不能空白");
		}
//			else if (password.trim().matches(pwdRegExp)) {
//			errorMsg.add("密碼限英文字母、數字，長度必須在8-20之間");
//		}
		if (usermail == null || usermail.trim().length() == 0) {
			errorMsg.add("信箱請勿空白");
		} 

		if(errorMsg!=null && !errorMsg.isEmpty()) {
			request.getRequestDispatcher(
					"/SignUp.jsp").forward(request, response);
			System.out.println(errorMsg.get(0));
			return;
		}
		
		if(!errorMsg.isEmpty()){//以上任一錯誤發生時，留在註冊畫面
			request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			return ;
		}else{
//			呼叫service服務，將資料送去DB型轉已寫在model裡
			MemberVO bean=ms.getOneMember(username);
			if(operation!=null && operation.equals("註冊")&& bean != null){
				errorMsg.add("帳號已存在，請再輸入其他帳號");	
				System.out.println(errorMsg.get(0));
			}else{
				try {				
					ms.registry1(username, password, usermail,broadcastWebsite);
					bean.setMemberAccount(username);
					bean.setMemberPassword(password.getBytes());
					bean.setMemberEmail(usermail);
					bean.setBroadcastWebsite(broadcastWebsite);
					if (nickname!=null){
						bean.setMemberNickname(nickname);
					}else{
						bean.setMemberNickname(null);
					}
					if (birthday!=""){
						bean.setMemberBirthday(ConvertType.convertToUtilDate(birthday));					
					}else{
						bean.setMemberBirthday(null);
					}
					ms.update(bean);
				} catch (Exception e) {
					errorMsg.add("儲存資料時發生錯誤，請檢查，例外="+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("SignUp.jsp");
			rd.forward(request, response);
			return;
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("HomePageVersion3.jsp");
			rd.forward(request, response);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
