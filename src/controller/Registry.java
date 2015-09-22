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
	
	@Override
	public void init() throws ServletException {
		ms=new MemberService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> errorMsg=new ArrayList<String>();
		request.setAttribute("ErrorMsgKey", errorMsg);//突然地與errors混淆
		String username=request.getParameter("memberAccount").toLowerCase();
		String userRegExp="^[a-zA-Z0-9]{6,20}$";//帳號正規式驗證
		String password=request.getParameter("memberPassword");
		String pwdRegExp="^[a-zA-Z0-9]{8,20}$";//密碼正規式驗證
		String usermail=request.getParameter("memberEmail");
		String mailRegExp="^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]{2,4}$";

		String operation=request.getParameter("operation");
		String broadcastWebsite=request.getParameter("broadcastWebsite");
		String nickname = request.getParameter("memberNickname");
		String birthday=request.getParameter("memberBirthday");
		String path = request.getContextPath();
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		
		if (username==null||username.trim().length()==0) {
			errorMsg.add("請輸入帳號");
		}else if(!username.trim().matches(userRegExp)){
			errors.put("username", "帳號只能是英文字母、數字，長度必須在6-20之間");
		}
		if(password==null||password.trim().length()==0){
			errorMsg.add("密碼不能空白");
		}else if(password.trim().matches(pwdRegExp)){
			errors.put("password", "密碼只能是英文字母、數字，長度必須在8-20之間");
		}
		
		if (usermail == null || usermail.trim().length() == 0) {
            errors.put("usermail", "信箱請勿空白");
        } else if (usermail.trim().matches(mailRegExp)) {
            errors.put("useremail", "信箱只能是英文字母、數字，不能有.-*/@等特殊字元");
        }
	
		if(errorMsg!=null && !errorMsg.isEmpty()) {
			request.getRequestDispatcher(
					"/Login.jsp").forward(request, response);
			return;
		}
		
		MemberVO bean=ms.login1(username, password);
		if(operation!=null && operation.equals("註冊")&& bean != null){
			errorMsg.add("帳號已存在，請再輸入其他帳號");		
		}else{
			try {
				ms.registry1(username, password, usermail);
				bean.setMemberNickname(nickname);
				bean.setMemberBirthday(ConvertType.convertToUtilDate(birthday));
				bean.setBroadcastWebsite(broadcastWebsite);
				ms.update(bean);
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("Exception", e.getMessage());
		        RequestDispatcher failureView = request.getRequestDispatcher("/Login.jsp");
		        failureView.forward(request, response);
			}
		}
		
		
		request.setAttribute("UserIdkey", bean);
		if(errorMsg.isEmpty()){
			request.getRequestDispatcher("HomePageVersion3.jsp").forward(request, response);
			return ;
		}else{
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return ;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
