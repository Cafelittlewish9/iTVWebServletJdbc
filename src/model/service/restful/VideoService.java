package model.service.restful;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.VideoDAO;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.VideoVO;

@Path("/video")
public class VideoService {
	private VideoDAO dao;

	public VideoService() {
		this.dao = new VideoDAOjdbc();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<VideoVO> hotVideo() {
		return dao.selectAll();
	}

	public Collection<VideoVO> searchVideo(String videoTitle) {
		return dao.selectByVideoTitle(videoTitle);
	}

	public Collection<VideoVO> videoClassList(String videoClassName) {
		return dao.selectByVideoClassName(videoClassName);
	}

	public boolean uploadVideo(VideoVO bean) {
		return dao.insert(bean);
	}

	// 只能改videoDescription，另外兩個參數是應資料庫而要求的
	public boolean updateVideo(String videoDescription, int videoId) {
		return dao.update(videoDescription, videoId);
	}

	public boolean removeVideo(int videoId) {
		return dao.delete(videoId);
	}

	// 不知道是否應該要有一個全刪的方法
	public static void main(String[] args) {
		VideoService service = new VideoService();
		System.out.println(service.hotVideo());
	}

}
