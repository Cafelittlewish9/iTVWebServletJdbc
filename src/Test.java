import java.util.List;

import org.json.JSONObject;

import model.service.VideoCommentsService;
import model.vo.VideoCommentsVO;

public class Test {

	public static void main(String[] args) {
		VideoCommentsService service = new VideoCommentsService();
		int videoId = 2;
		List<VideoCommentsVO> list = service.videoCommentsList(videoId);
		System.out.println(list);
		JSONObject jsonObj = new JSONObject(list);
		jsonObj.put("list", list);
		System.err.println(jsonObj);
	}

}
