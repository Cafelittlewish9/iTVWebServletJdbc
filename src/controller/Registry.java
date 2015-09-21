package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.MemberService;
import model.vo.MemberVO;
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
		request.setAttribute("ErrorMsgKey", errorMsg);
		String username=request.getParameter("memberAccount");
		String password=request.getParameter("memberPassword");
		String usermail=request.getParameter("memberEmail");
		String operation=request.getParameter("operation");
		String broadcastWebsite=request.getParameter("broadcastWebsite");
		String path = request.getContextPath();
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		
		if (username==null||username.length()==0) {
			errorMsg.add("請輸入帳號");
		}
		if(password==null||password.length()==0){
			errorMsg.add("密碼不能空白");
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		request.setAttribute("UserIdkey", username);
		if(errorMsg.isEmpty()){
			request.getRequestDispatcher("HomePageVersion3.jsp").forward(request, response);
			return ;
		}else{
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return ;
		}
		
		/*if(operation!=null && operation.equals("註冊")&& bean != null){		
			HttpSession session = request.getSession();
			String ip = request.getRemoteAddr();// 有個ip and then?
			session.setAttribute("user", bean);
			response.sendRedirect(path + "/HomePageVersion3.jsp");
			return;
	} else if (operation!=null && operation.equals("提取密碼")){
		request.getRequestDispatcher("LoginPage.html").forward(request, response);//送去客服頁面
		return;
	}else{
		errors.put("password", "登入失敗，請再試一遍");// 放在session裡
		request.getRequestDispatcher("show.html").forward(request, response);
	}*/
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
