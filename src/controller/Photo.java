package controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.service.MemberService;
import model.vo.MemberVO;
@WebServlet(name = "Photo", urlPatterns = {"/upload.do"})
public class Photo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MemberService ms;
	
	public void init() throws ServletException {
		ms=new MemberService();
	}	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Part memberPhoto=request.getPart("memberPhoto");
//		String filename=request.getParameter("memberPhoto");
//		int id=Integer.parseInt(request.getParameter("memberId"));
//		InputStream is=memberPhoto.getInputStream();
//		ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
////		byte[] buff = new byte[(int)memberPhoto.getSize()];  
////		int rc = 0; 
////		while ((rc = is.read(buff, 0, 100)) > 0) { 
////		swapStream.write(buff, 0, rc); 
////		} 
//		OutputStream out = new FileOutputStream("d://" + filename);
//        byte[] buffer = new byte[1024];
//        int length = -1;
//        while ((length = is.read(buffer)) != -1) {
//            out.write(buffer, 0, length);
//        }
//        is.close();
//        out.close();
//        }
		System.out.println("小貝");
		Part filePart1 = request.getPart("memberPhoto");
		System.out.println("你別死呀~~~~~~~~");
        String header = filePart1.getHeader("Content-Disposition");
        String filename = header.substring(
        		header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        
        InputStream in = filePart1.getInputStream();
        
        OutputStream out = new FileOutputStream("d:/png/" + filename);
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        
        out.close();
        in.close();
    }
		
		
}
/*
 //boolean isMultipart = ServletFileUpload.isMultipartContent(request);	
		
		//Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		DiskFileItemFactory factory = new DiskFileItemFactory(
				yourMaxMemorySize, yourTempDirectory);
		
		//Set factory constraints
		//factory.setSizeThreshold(yourMaxMemorySize); 
		//factory.setRepository(yourTempDirectory); 

		//Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory); 

		//Set overall request size constraint
		upload.setHeaderEncoding("utf-8");
		upload.setFileSizeMax(1024*1024*2);
		upload.setSizeMax(1024*1024*10);
		

		//Parse the request
			List<FileItem> lists;
			try {	
				lists = upload.parseRequest(request);
				Iterator<FileItem> iter = lists.iterator();
				for (FileItem fileItem : lists) {        
			        String fieldName = fileItem.getFieldName();
			        if(fileItem.isFormField()){
			          String value = fileItem.getString();          
			          value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
			        }else{
			          Long size = fileItem.getSize();          
			          String fileName = fileItem.getName();
			          //System.out.println("檔案名："+fileName+"\t大小：" + size + "byte");
			          
			          if(fileName.endsWith(".exe")){
			            request.setAttribute("msg", "不允許上傳的類型！");
			          }else{
			        	  //String path=request.getContextPath();
			        	  //File file = new File(path);
			        	  //fileItem.write(file);
			        	  MemberVO bean=ms.login1("Pikachu","A");
			        	  bean.setMemberPassword("A".getBytes());
			        	  bean.setMemberPhoto(fileItem.get());
			        	  ms.update(bean);
			            request.setAttribute("msg", "上傳成功！");
			          }
			        }
			      }
			    }catch(FileSizeLimitExceededException e){
			      request.setAttribute("msg", "檔案太大");
			    }catch(FileUploadException e){
			                        request.setAttribute("msg", e.toString());
			      e.printStackTrace();
			    }catch(Exception e){
			                        request.setAttribute("msg", e.toString());
			      e.printStackTrace();
			    }    
			    request.getRequestDispatcher("tester.html").forward(request, response);
			  }

－－－
<%@ page language="java" pageEncoding="UTF-8"%>  
<%@page import="java.sql.ResultSet"%>  
<%@page import="java.io.File"%>  
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>  
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>  
<%@page import="java.util.List"%>  
<%@page import="org.apache.commons.fileupload.FileItem"%>  
<%@page import="org.apache.commons.fileupload.FileUploadException"%>  
<%@page import="utils.ImageFileUpload"%>  
  
<%  
    String msg = "";  
    ResultSet rs = null;  
    ImageFileUpload ifu = new ImageFileUpload();  
      
        try {  
            if (ServletFileUpload.isMultipartContent(request)) {  
                DiskFileItemFactory dfifactory = new DiskFileItemFactory();  
                //   
                dfifactory.setSizeThreshold(100 * 1204);  
                // getSizeThreshold()  
                dfifactory.setRepository(new File("C:\\Java\\temp"));  
  
                ServletFileUpload sfu = new ServletFileUpload(dfifactory);  
  
                List<FileItem> list = sfu.parseRequest(request);  
                for (FileItem fi : list) {  
                    if (!fi.isFormField()) {  
                        if (fi.getSize() != 0) {  
                            if (fi.getSize() > (400 * 1024)) {  
                                msg = "400kb";  
                            } else {  
                                File f = new File(fi.getName());  
                                //   
                                String destSavePath = getServletContext()  
                                        .getRealPath(getServletInfo())  
                                        + "\\images\\" + f.getName();  
  
                                //   
                                if (ifu.saveFilename(f.getName())) {  
                                    //   
                                    if (ifu.saveImage(fi, destSavePath)) {  
                                        msg = "";  
                                    }  
                                }  
                            }  
                        }  
                    }  
                    fi.delete();  
                }  
            }  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        }  
          
    //   
    rs = ifu.getAll();  
%>  */