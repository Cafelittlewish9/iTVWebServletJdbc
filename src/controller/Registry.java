package controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.MemberService;
import model.vo.MemberVO;
@WebServlet("/controller/registry.controller")
public class Registry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;
	
	
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
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
@WebServlet("/controller/registry.controller")
public class Registry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;
	
	
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
