package controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.service.MemberService;
@WebServlet("/pic")
public class Pic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;
	
	public void init() throws ServletException {
		ms=new MemberService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String uploader = request.getParameter("");
		String XXX = request.getParameter("XXX");
		
		if(XXX != null){
			byte[]a=ms.getPhoto(1);
			//InputStream is=new InputStream();要求要放字串，但我拿到的東西是byte陣列
			response.setContentType("image/jpg");
			OutputStream os=response.getOutputStream();
			
			byte[] b=new byte[1024];
			int len=a.length;
			while (len!=-1){
			os.write(b,0,len);
		}
			request.getRequestDispatcher("tester.html").forward(request, response);
			os.close();
		}
		
		
	
	}
	
	
	
	
	
}
