package model.service.restful;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.ReplyArticleDAO;
import model.dao.jdbc.ReplyArticleDAOjdbc;
import model.vo.ReplyArticleVO;
@Path("/replyArticle")
public class ReplyArticleRestful {
	private ReplyArticleDAO dao;

	public ReplyArticleRestful() {
		this.dao = new ReplyArticleDAOjdbc();
	}
	@GET
	@Path("/list/{articleId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<ReplyArticleVO> listReplyArticle(@PathParam("articleId")int articleId) {
		return dao.selectByArticleId(articleId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addReplyArticle(int memberId, int articleId, String replyContent) {
		int temp = dao.insert(memberId, articleId, replyContent);
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean modifyReplyArticle(String replyContent, int replyArticleId) {
		if (replyContent != null && replyContent.trim().length() != 0) {
			int temp = dao.update(replyContent, replyArticleId);
			if (temp == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	@DELETE
	@Path("/{replyArticleId}")
	public boolean deleteReplyArticle(@PathParam("replyArticleId")int replyArticleId) {
		int temp = dao.delete(replyArticleId);
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}
}
