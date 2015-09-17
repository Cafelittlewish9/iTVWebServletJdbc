package model.service.restful;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.ArticleDAO;
import model.dao.ReportArticleDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.dao.jdbc.ReportArticleDAOjdbc;
import model.vo.ReportArticleVO;
@Path("/reportArticle")
public class ReportArticleRestful {
	private ReportArticleDAO dao;
	private ArticleDAO dao2;

	public ReportArticleRestful() {
		this.dao = new ReportArticleDAOjdbc();
		this.dao2 = new ArticleDAOjdbc();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<ReportArticleVO> reportArticleList() {
		return dao.selectAll();
	}

	public boolean reportArticle(int reportedArticleId, String reportReason) {
		ReportArticleVO bean = new ReportArticleVO();
		bean.setReportedArticleId(reportedArticleId);
		bean.setReportReason(reportReason);
		return dao.insert(bean);
	}

	public boolean reportArticle(ReportArticleVO bean) {
		return dao.insert(bean);
	}

	public boolean deleteArticle(ReportArticleVO bean) {
		boolean result1 = dao2.delete(bean.getReportedArticleId());
		boolean result2 = dao.delete(bean.getOrderId());
		if (result1 && result2) {
			return true;
		} else {
			return false;
		}
	}
}
