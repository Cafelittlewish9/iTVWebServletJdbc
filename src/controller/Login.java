package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.LoginService;
import model.service.MemberService;
import model.vo.MemberVO;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;
	private LoginService ls;	
	
	public void init() throws ServletException {
		ms=new MemberService();
		ls=new LoginService();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String path = request.getContextPath();
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		if(username==null || username.length()==0) {
			errors.put("username", "請輸入註冊信箱");
		}
		
		if(password==null || password.length()==0) {
			errors.put("password", "請輸入密碼");
		}
		
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"LoginPage.html").forward(request, response);
			return ;
		}
		
		MemberVO bean = ms.login2(username, password);
		
		if(bean==null) {
			errors.put("password", "登入失敗，請再試一遍");//放在session裡
			
			request.getRequestDispatcher(
					"LoginPage.html").forward(request, response);
			return ;
		} else {
			HttpSession session = request.getSession();
			String ip=request.getRemoteAddr();//有個ip and then?
			
			session.setAttribute("user", bean);	
			response.sendRedirect(path+"/index.html");
			return;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

}
