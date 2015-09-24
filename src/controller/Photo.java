package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.service.MemberService;
import util.ConvertType;

@WebServlet(name = "Photo", urlPatterns = { "/upload.do" })
public class Photo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MemberService ms;

	public void init() throws ServletException {
		ms = new MemberService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("小貝");
		Part filePart1 = request.getPart("memberPhoto");
		System.out.println("你別死呀~~~~~~~~");
		String header = filePart1.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));

		InputStream in = filePart1.getInputStream();
//		String path = "d:/png/" + filename;
//		OutputStream out = new FileOutputStream(path);
//		byte[] buffer = new byte[1024];
//		int length = -1;
//		while ((length = in.read(buffer)) != -1) {
//			out.write(buffer, 0, length);
//		}
//		ms.changePhoto("Pikachu", in);
////		out.close();
//		in.close();

	}

}
