package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.MemberService;
import model.vo.MemberVO;
@WebServlet("/registry")
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
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if (username==null||username.length()==0) {
			errorMsg.add("請輸入信箱");
		}
		if(password==null||password.length()==0){
			errorMsg.add("密碼不能空白");
		}
	
		if(errorMsg!=null && !errorMsg.isEmpty()) {
			request.getRequestDispatcher(
					"/LoginPage.html").forward(request, response);
			return;
		}

		
		MemberVO bean=ms.login2(username, password);		
		if(bean!=null){
			errorMsg.add("帳號已存在，請再輸入其他帳號");
		}else{
			try {
				ms.registry2(username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("UserIdkey", username);
		if(errorMsg.isEmpty()){
			request.getRequestDispatcher("index.html").forward(request, response);
			return ;
		}else{
			request.getRequestDispatcher("LoginPage.html").forward(request, response);
			return ;
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
