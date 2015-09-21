package model.service.restful;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("xxx")
public class Test {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String test() {
		return "test";
	}
	
	public static void main(String[] args) throws ParseException{
		java.util.Date a=new java.util.Date();
		java.text.SimpleDateFormat b=new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		a=b.parse("2015-9-18 06:07:35");
		
		System.out.println(a.getTime());
	}
}
