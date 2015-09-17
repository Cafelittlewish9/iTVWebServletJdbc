package model.service.restful;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.ReportVideoDAO;
import model.dao.VideoDAO;
import model.dao.jdbc.ReportVideoDAOjdbc;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.ReportVideoVO;
@Path("/reportVideo")
public class ReportVideoRestful {
	private ReportVideoDAO dao;
	private VideoDAO dao2;
	
	public ReportVideoRestful(){
		this.dao = new ReportVideoDAOjdbc();
		this.dao2 = new VideoDAOjdbc();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<ReportVideoVO> selectAllList(){
		return dao.selectAll();
	}
	public boolean addReportVideo(int reportedVideoId,java.util.Date reportTime,String reportReason){
		ReportVideoVO bean = new ReportVideoVO();
		bean.setReportedVideoId(reportedVideoId);
		bean.setReportTime(reportTime);
		bean.setReportReason(reportReason);
		return dao.insert(bean);
	}
	public boolean deleteVideo(ReportVideoVO bean){
		boolean result1 = dao2.delete(bean.getReportedVideoId());
		boolean result2 = dao.delete(bean.getOrderId());
		if(result1 && result2){
			return true;
		}else{
			return false;
		}	
	}	
}
