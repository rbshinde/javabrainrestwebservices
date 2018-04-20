package org.ram.testwebservice.messengers.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/indectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotation(@MatrixParam("param") String matrixpram,
			                               @HeaderParam("authSessionId") String customHeaderValue,
			                               @CookieParam("JSESSIONID") String cookie){
		
		return "matrix param : "+matrixpram+ " customHeaderValue "+customHeaderValue+" cookie "+cookie;
		
	}
	
	@GET
	@Path("context")
	public String getParamContext(@Context UriInfo uriInfo, @Context HttpHeaders header){
		
		
		
		return "path " + uriInfo.getAbsolutePath().toString() +" cookies "+header.getCookies().toString();
	}

}
