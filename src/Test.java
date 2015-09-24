import java.util.LinkedHashSet;
import java.util.Set;

import org.json.JSONObject;

import model.service.VideoCommentsService;

public class Test {

	public static void main(String[] args) {
		VideoCommentsService service = new VideoCommentsService();
		int videoId = 2;
		Set temp = new LinkedHashSet();
		temp.add("XXX");
		temp.add("888");
		temp.add("ooo");
		temp.add("ppp");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("lg", temp);
		System.out.println(jsonObj);
	}

}
