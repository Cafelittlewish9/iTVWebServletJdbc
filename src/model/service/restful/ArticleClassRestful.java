package model.service.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import model.dao.ArticleClassDAO;
import model.dao.jdbc.ArticleClassDAOjdbc;
import model.vo.ArticleClassVO;
/**
 * @author iTV小組成員
 *
 */
@Path("/articleClass")
public class ArticleClassRestful {
	private ArticleClassDAO dao;
	/**
	 * 初始化ArticleClassDAO
	 */
	public ArticleClassRestful() {
		this.dao = new ArticleClassDAOjdbc();
	}
	/**
	 * 增加文章類別
	 * 
	 * @param bean 必須包含 <b>subclassNo</b>、 <b>subclassName</b> 與 <b>className</b>
	 * @return true 增加成功; false 增加失敗
	 */	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addArticleClass(ArticleClassVO bean) {
		boolean result = false;
		bean.getSubclassNo();
		bean.getSubclassName();
		bean.getClassName();
		if (bean != null) {
			result = dao.insert(bean);
		}
		return result;
	}
	/**
	 * 修改文章類別
	 * 
	 * @param bean 必須包含 <b>SubclassNo</b>、<b>SubclassNo</b>、<b>SubclassNo</b>
	 * @return true 修改成功; false 修改失敗
	 */		
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateArticleClass(ArticleClassVO bean) {
		boolean result = false;
		bean.getSubclassNo();
		bean.getSubclassName();
		bean.getClassName();
		if (bean != null) {
			result = dao.update(bean);
		}
		return result;
	}

	/**
	 * 刪除文章類別
	 * 
	 * @param subclassNo 文章子類別編號
	 * @return true 刪除成功; false 刪除失敗
	 */		
	@DELETE
	@Path("/{subclassNo}")
	public boolean deleteArticleClass(@PathParam("subclassNo")String subclassNo) {
		return dao.delete(subclassNo);
	}
	
	/**
	 * 增加文章類別
	 * 
	 * @param subclassNo 文章子分類編號
	 * @param subclassName 文章子分類名稱
	 * @param className 文章類別名稱
	 * @return true 增加成功; false 增加失敗
	 */	
	public boolean addArticleClass(String subclassNo, String subclassName, String className) {
		ArticleClassVO bean = new ArticleClassVO();
		bean.setSubclassNo(subclassNo);
		bean.setSubclassName(subclassName);
		bean.setClassName(className);
		return dao.insert(bean);
	}

	/**
	 * 修改文章類別
	 * 
	 * @param subclassNo 文章子分類編號
	 * @param subclassName 文章子分類名稱
	 * @param className 文章類別名稱
	 * @return true 修改成功; false 修改失敗
	 */		
	public boolean updateArticleClass(String subclassNo, String subclassName, String className) {
		ArticleClassVO bean = new ArticleClassVO();
		bean.setSubclassNo(subclassNo);
		bean.setSubclassName(subclassName);
		bean.setClassName(className);
		return dao.update(bean);
	}


}
