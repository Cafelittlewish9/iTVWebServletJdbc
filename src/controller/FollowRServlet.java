package controller;
//使用RESTfulservice的servlet，但不曉得與一般的servlet差在哪裡
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.FollowService;
import model.vo.FollowVO;
@WebServlet("/follow")
public class FollowRServlet extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 2010L;
    private FollowService service = null;
    
//    public void setFollowRestful(FollowRestful service){
//        this.service = service;
//    }
    public void init() throws ServletException{
    	service = new FollowService();
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
    	String followId=request.getParameter("followId");
//      轉換型別
        int id=Integer.parseInt(memberId);
        int fid=Integer.parseInt(followId);
        FollowVO bean = new FollowVO();
        bean.setFollowId(fid);
        bean.setMemberId(id);
        //還是應該用bid去求出被黑的人的memberAccount?!
//      呼叫model
        service.follow(bean);
//      setAttribute共享資訊並轉交
        request.setAttribute("followList", service.followList(id));
    }
    //要如何只讓作者本人及管理員看到刪除這顆按鈕?權限角色?!
    public void delete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String memberId =request.getParameter("memberId");
    	String followId=request.getParameter("followId");
        int id=Integer.parseInt(memberId);
        int fid=Integer.parseInt(followId);
        service.unfollow(id, fid);
        request.setAttribute("followList", service.followList(id));
    }
    
    public void list(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String memberId =request.getParameter("memberId");
    	int id=Integer.parseInt(memberId);
        request.setAttribute("followList", service.followList(id));        
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String operation = request.getParameter("operation");

        String viewName = null;
        if(operation!=null){
			 if (operation.equals("insert")) {
				insert(request, response);
				viewName = ".html";
			} else if (operation.equals("delete")) {
				delete(request, response);
				viewName = ".html";
			} else if (operation.equals("list")) {
				list(request, response);
				viewName = ".html";
			}
        }

        RequestDispatcher view = request.getRequestDispatcher(viewName);
        view.forward(request, response);
    }   

}