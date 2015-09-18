package model.service.restful;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.ReplyArticleDAO;
import model.dao.ReportReplyArticleDAO;
import model.dao.jdbc.ReplyArticleDAOjdbc;
import model.dao.jdbc.ReportReplyArticleDAOjdbc;
import model.vo.ReportReplyArticleVO;
@Path("/reportReplyArticle")
public class ReportReplyArticleRestful {
	private ReportReplyArticleDAO dao;
	private ReplyArticleDAO dao2;
	
	public ReportReplyArticleRestful() {
		this.dao = new ReportReplyArticleDAOjdbc();
		this.dao2 = new ReplyArticleDAOjdbc();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<ReportReplyArticleVO> selectAllList() {
		return dao.selectAll();
	}
	@DELETE
	@Path("/{bean}")
	public boolean deleteReplyArticle(@PathParam("bean")ReportReplyArticleVO bean) {
		int result1 = dao2.delete(bean.getReportedReplyArticleId());
		boolean result2 = dao.delete(bean.getOrderId());
		if (result1 == 1 && result2) {
			return true;
		} else {
			return false;
		}
	}	
	
	public boolean addReportReplyArticle(int reportedReplyArticleId, String reportReason) {
		ReportReplyArticleVO bean = new ReportReplyArticleVO();
		bean.setReportedReplyArticleId(reportedReplyArticleId);
		bean.setReportReason(reportReason);
		return dao.insert(bean);
	}

}
