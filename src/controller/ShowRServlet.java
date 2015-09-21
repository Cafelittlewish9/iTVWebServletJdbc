package controller;
//使用RESTfulservice的servlet，但不曉得與一般的servlet差在哪裡
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.restful.ShowRestful;
import model.vo.ShowVO;
import util.ConvertType;
@WebServlet("/showServlet")
public class ShowRServlet extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 2010L;
    private ShowRestful service = null;
    
//    public void setShowRestful(ShowRestful service){
//        this.service = service;
//    }
    public void init() throws ServletException{
    	service = new ShowRestful();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        this.processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        this.processRequest(request, response);
    }   

    public void insert(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
//    	接收資料
    	String memberId =request.getParameter("memberId");
        String website=request.getParameter("website");
//        String video=request.getParameter("video");
//        String member=request.getParameter("member");
//      轉換型別
        int id=Integer.parseInt(memberId);
        java.util.Date time=new java.util.Date();
//      呼叫model
        ShowVO bean = new ShowVO();//bean要set以上東西才成為一個bean
        bean.setMemberId(id);
        bean.setShowTime(time);
        bean.setWebsite(website);
        service.addShow(bean);
//      setAttribute共享資訊並轉交
        request.setAttribute("showList", service.showList(id));
    }
    
    public void delete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        String memberId = request.getParameter("memberId");
        int id=Integer.parseInt(memberId);
        String website=request.getParameter("website");
        service.removeShow(id, website);
        request.setAttribute("showList", service.showList(id));
    }

    public void list(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String memberId =request.getParameter("memberId");
    	int id=Integer.parseInt(memberId);
    	request.setAttribute("showList", service.showList(id));        
    }
    
    public void update(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String memberId = request.getParameter("memberId");
    	int id=Integer.parseInt(memberId);
    	String showTime=request.getParameter("showTime");
//    	java.util.Date time=ConvertType.convertToUTCTime(showTime);
        java.util.Date time = null;
    	if(showTime!=null && showTime.length()!=0) {
    		time = ConvertType.convertToUtilDate(showTime);
    		if(new java.util.Date(0).equals(time)) {
//    			errors.put("make", "Make must be a date of YYYY-MM-DD");
    		}
    	}    	
        String website=request.getParameter("website");
//        String video=request.getParameter("video");
//        String member=request.getParameter("member");
        ShowVO bean = new ShowVO();
      //service.changeShow(bean, showTime);
      request.setAttribute("showList", service.showList(id));
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String operation = request.getParameter("operation");

        String viewName = null;
        if(operation!=null){
			 if (operation.equals("insert")) {
				insert(request, response);
				viewName = "show.html";
			} else if (operation.equals("delete")) {
				delete(request, response);
				viewName = "show.html";
			} else if (operation.equals("list")) {
				list(request, response);
				viewName = "show.html";
			} else if (operation.equals("update")) {
				update(request, response);
				viewName = "show.html";
			}
        }

        RequestDispatcher view = request.getRequestDispatcher(viewName);
        view.forward(request, response);
    }   

}