package mx.com.rc.rest;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/info")
public class InfoRest {
	private Properties properties;
	private final String INFO_PROPERTIES_PATH = "/info/appInfo.properties";
	private static Logger logger = Logger.getLogger(InfoRest.class.getName());
	
	@Context
	private UriInfo context;

	public InfoRest() {
		properties = new Properties();
		
		try {			
			properties.load(getClass().getResourceAsStream(INFO_PROPERTIES_PATH));
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "InfoResource, reading properites:", ex);			
		}
		
	}

	@GET
	@Path("/version")
    @Produces(MediaType.APPLICATION_JSON)
	public String getVersion() {
		return properties.getProperty("app.info.name");
	}


}
