package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import model.service.ChannelService;
import model.vo.ChannelVO;
import model.vo.ShowVO;
import model.vo.VideoVO;
import util.ConvertType;

/**
 * Servlet implementation class ChannelServlet
 */
@WebServlet("/ChannelServlet")
public class ChannelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ChannelService cs;
    public ChannelServlet() {
    	cs=new ChannelService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String memberId = request.getParameter("memberId");
			String channelNo = request.getParameter("channelNo");
			String broadcastWebsite = request.getParameter("broadcastWebsite");
			String prodaction = request.getParameter("prodaction");
			
			//驗證資料
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("error", errors);
			
			if(prodaction!=null) {
				if(prodaction.equals("Insert")) {
					if(memberId==null || memberId.length()==0) {
						errors.put("memberId", "請\"輸入\"您的帳號"+ prodaction );
					}
					if(broadcastWebsite==null){
						errors.put("broadcastWebsite", "請設定您所欲觀看的頻道網址");
					}
				}else if (prodaction.equals("Update")||prodaction.equals("delete")){
					errors.put("updateChannel","您未執行任何刪除或修改");
				}
			}
			
			//轉換資料
			int id = 0;
					
			if(memberId!=null && memberId.length()!=0) {
				id = ConvertType.convertToInt(memberId);
				if(id==-1000) {
					errors.put("id", "只能輸入數字");
					}
			}
			
			//呼叫Model
			ChannelVO bean = new ChannelVO();
			bean.setMemberId(id);
			bean.setChannelNo(Byte.parseByte(channelNo));
			bean.setBroadcastWebsite(broadcastWebsite);
			
			//根據Model執行結果導向View
			cs = new ChannelService();
			
			
			if(prodaction!=null && prodaction.equals("Select")) {
				Collection<ChannelVO> result = cs.allChannel(id);
				request.setAttribute("select", result);
				request.getRequestDispatcher(
						".jsp").forward(request, response);
			}else if(prodaction!=null && prodaction.equals("Insert")) {
				boolean result = cs.addChannel(bean);
				if(result==false) {
					errors.put("action", "新增失敗");
				} else {					
					
					/*JSONArray one = new JSONArray();
						Map map =new HashMap();
						map.put("memberId",result.getMemberId());
						map.put("showTime",result.getChannelNo());
						map.put("website",result.getBroadcastWebsite());
						one.add(map);*/
					
					//轉換contentType必要
					response.setContentType("text/html; charset=utf-8");
//					String insert = JSONValue.toJSONString(one);
					System.out.println(insert);
					PrintWriter out = response.getWriter();
					out.println(insert);
//					request.setAttribute("insert", insert);
				}
//				request.getRequestDispatcher(
//						"/pages/Success.jsp").forward(request, response);
			}else if(prodaction!=null && prodaction.equals("Update")) {
				ChannelVO result = cs.updatechannel(bean);
				if(result==null) {
					errors.put("action", "Update fail");
				} else {
					request.setAttribute("update", result);
				}
				request.getRequestDispatcher(
						"/pages/Success.jsp").forward(request, response);
			} else if(prodaction!=null && prodaction.equals("Delete")) {
				boolean result = cs.deletechannel(bean);
				if(!result) {
					request.setAttribute("delete", 0);
				} else {
					request.setAttribute("delete", 1);
				}
				request.getRequestDispatcher(
						"/pages/Success.jsp").forward(request, response);
			} else  {
				//沒有按時直接送這段
				List<ChannelVO> res = cs.select(bean);
				JSONArray list = new JSONArray();
				for (ChannelVO row : res) {
					Map map =new HashMap();
					map.put("memberId",row.getMemberId());
					map.put("showTime",row.getChannelNo());
					map.put("website",row.getBroadcastWebsite());
					list.add(map);
				}
//				//轉換contentType必要
				response.setContentType("text/html; charset=utf-8");
				String json = JSONValue.toJSONString(list);
				PrintWriter out = response.getWriter();
				out.println(json);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.doGet(request, response);
	}

}
