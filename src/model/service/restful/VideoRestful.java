package model.service.restful;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.VideoDAO;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.VideoVO;

@Path("/video")
public class VideoRestful {
	private VideoDAO dao;

	public VideoRestful() {
		this.dao = new VideoDAOjdbc();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean uploadVideo(VideoVO bean) {
		return dao.insert(bean);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<VideoVO> hotVideo() {
		return dao.selectAll();
	}
	@GET
	@Path("/videoTitle/{videoTitle}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<VideoVO> searchVideo(@PathParam("videoTitle")String videoTitle) {
		return dao.selectByVideoTitle(videoTitle);
	}

	@GET
	@Path("/videoClassName/{videoClassName}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Collection<VideoVO> videoClassList(@PathParam("videoClassName") String videoClassName) {
		return dao.selectByVideoClassName(videoClassName);
	}
	@DELETE
	@Path("/{videoId}")
	public boolean removeVideo(@PathParam("videoId")int videoId) {
		return dao.delete(videoId);
	}
	
	
	// 只能改videoDescription，另外兩個參數是應資料庫而要求的
	public boolean updateVideo(String videoDescription, int videoId) {
		return dao.update(videoDescription, videoId);
	}
	// 不知道是否應該要有一個全刪的方法

}
