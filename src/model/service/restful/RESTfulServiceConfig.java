 package model.service.restful;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("/RESTful")
public class RESTfulServiceConfig extends ResourceConfig {
	public RESTfulServiceConfig(){
		this.packages("model.service.restful");
	}

}
