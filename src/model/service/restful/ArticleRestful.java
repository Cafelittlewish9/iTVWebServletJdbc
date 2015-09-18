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

import model.dao.ArticleDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.vo.ArticleVO;

/**
 * @author iTV小組成員
 *
 */
@Path("/article")
public class ArticleRestful {
	private ArticleDAO dao;

	/**
	 * 初始化ArticleDAO
	 */
	public ArticleRestful() {
		this.dao = new ArticleDAOjdbc();
	}
	
	/**
	 * 增加一篇文章
	 * 
	 * @param bean
	 *            必須包含 <b>memberId</b>、<b>subclassNoarticleTitle</b> 以及
	 *            <b>articleContent</b>
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(int, String, String, String)
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addArticle(ArticleVO bean) {
		boolean result = false;
		bean.getMemberId();
		bean.getSubclassNo();
		bean.getArticleTitle();
		bean.getArticleContent();
		if (bean != null) {
			result = dao.insert(bean);
		}
		return result;
	}
	/**
	 * 查詢所有文章
	 * 
	 * @return Collection<ArticleVO>
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<ArticleVO> allArticle() {
		return dao.selectAll();
	}

	/**
	 * 依照文章子類別代碼來查詢文章
	 * 
	 * @param subclassNo
	 *            文章的子類別代碼
	 * @return Collection<ArticleVO>
	 */
	@GET
	@Path("/list/{keyword}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<ArticleVO> searchByInput(@PathParam("keyword")String keyword) {
		if (keyword.indexOf(" ")!=-1){
			keyword.split(" ");			
		}		
		Collection<ArticleVO> list = dao.selectByInput(keyword,keyword,keyword,keyword);
		return list;
	}
	/**
	 * 修改文章內容
	 * 
	 * @param bean
	 *            必須包含 <b>articleContent</b> 與 <b>articleId</b>
	 * @return true 修改成功; false 修改失敗
	 * @see #modifyArticle(String, int)
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean modifyArticle(ArticleVO bean) {
		if (bean != null) {
			return dao.update(bean);
		}
		return false;
	}
	/**
	 * 刪除文章
	 * 
	 * @param articleId
	 *            要刪除的文章ID
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(ArticleVO)
	 */
	@DELETE
	@Path("/{articleId}")
	public boolean deleteArticle(@PathParam("articleId")int articleId) {
		boolean result = false;
		if (dao.delete(articleId)) {
			return true;
		}
		return result;
	}
	


	

	/**
	 * 增加一篇文章
	 * 
	 * @param memberId
	 *            會員ID
	 * @param subclassNo
	 *            文章的類別代碼
	 * @param articleTitle
	 *            文章的標題
	 * @param articleContent
	 *            文章的內容
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(ArticleVO)
	 */
	public boolean addArticle(int memberId, String subclassNo, String articleTitle, String articleContent) {
		boolean result = false;
		ArticleVO bean = new ArticleVO();
		bean.setMemberId(memberId);
		bean.setSubclassNo(subclassNo);
		bean.setArticleTitle(articleTitle);
		bean.setArticleContent(articleContent);
		if (result == true) {
			return dao.insert(bean);
		}
		return result;
	}

	/**
	 * 修改文章內容
	 * 
	 * @param articleContent
	 *            修改後的內容
	 * @param articleId
	 *            要修改的文章ID
	 * @return true 新增成功; false 新增失敗
	 */
	public boolean modifyArticle(String articleContent, int articleId) {
		ArticleVO bean = new ArticleVO();
		bean.setArticleContent(articleContent);
		bean.setArticleId(articleId);
		return dao.update(bean);
	}


	
	

	/**
	 * 刪除文章
	 * 
	 * @param bean
	 *            必須包含 <b>articleId</b>
	 * @return true 新增成功; false 新增失敗
	 * @see #deleteArticle(int)
	 */
	public boolean deleteArticle(ArticleVO bean) {
		boolean result = false;
		if (dao.delete(bean.getArticleId())) {
			return true;
		}
		return result;
	}
	
}
