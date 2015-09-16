package util;

import java.time.ZonedDateTime;
import java.util.List;

import model.dao.ArticleDAO;
import model.dao.LoginDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.dao.jdbc.LoginDAOjdbc;
import model.vo.ArticleVO;
import model.vo.LoginVO;

public class ConvertType {

	public static int convertToInt(String whichYouWantToConvert) {
		int result = Integer.MIN_VALUE;
		try {
			result = Integer.parseInt(whichYouWantToConvert);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static long convertToLong(String whichYouWantToConvert) {
		long result = Long.MIN_VALUE;
		try {
			result = Long.parseLong(whichYouWantToConvert);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static byte convertToByte(String whichYouWantToConvert) {
		byte result = Byte.MIN_VALUE;
		try {
			result = Byte.parseByte(whichYouWantToConvert);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static java.util.Date convertToLocalTime(java.util.Date whichYouWantToConvert) {
		java.util.Date result = null;
		if (whichYouWantToConvert != null) {
			long a = whichYouWantToConvert.getTime();
			long b = ZonedDateTime.now().getOffset().getTotalSeconds()*1000;
			result = new java.util.Date(a + b);
		}
		return result;
	}
	public static java.util.Date convertToUTCTime(java.util.Date whichYouWantToConvert) {
		java.util.Date result = null;
		if (whichYouWantToConvert != null) {
			long a = whichYouWantToConvert.getTime();
			long b = ZonedDateTime.now().getOffset().getTotalSeconds()*1000;
			result = new java.util.Date(a - b);
		}
		return result;
	}

	public static void main(String[] args) {
		// System.out.println(TimeZone.getDefault());
		// String[] temp = TimeZone.getAvailableIDs();
		// for (String a : temp) {
		// System.out.println(a);
		// }
		// Set<String> temp2 = ZoneId.getAvailableZoneIds();
		// for (String b : temp2) {
		// if (b.contains("Asia")) {
		// b = b.substring(4);
		// System.out.println(b);
		// }
		// }
		// String time = ConvertType.convertToLocalTime(new
		// java.util.Date(System.currentTimeMillis()), "Asia/Taipei");
		// System.out.println(time);

		ArticleDAO temp=new ArticleDAOjdbc();
		LoginDAO dao = new LoginDAOjdbc();
		
		List<ArticleVO> beans=temp.selectByInput("", "", "Pikachu","");
		for(ArticleVO b: beans){
			java.util.Date time1 = ConvertType.convertToLocalTime(b.getPublishTime());
			System.out.println(time1);
		}
		
		/*List<LoginVO> bean = dao.selectAll("Pikachu");
		for (LoginVO a : bean) {
			java.util.Date time2 = ConvertType.convertToLocalTime(a.getLoginTime());
			System.out.println(time2);
		}*/
		// System.out.println(ZonedDateTime.now().getOffset().getId());
		// System.out.println(ZonedDateTime.now().getOffset().getTotalSeconds());
		// System.out.println(ZonedDateTime.now().getOffset().getRules());
		// System.out.println(ZonedDateTime.now().getOffset().toString());
		// System.out.println(60*60*8);
	}
}
