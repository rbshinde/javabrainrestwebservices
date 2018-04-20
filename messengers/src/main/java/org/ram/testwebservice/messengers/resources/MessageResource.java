package org.ram.testwebservice.messengers.resources;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.enterprise.inject.ResolutionException;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.Uri;
import org.ram.testwebservice.messengers.beans.MessageFilterBean;
import org.ram.testwebservice.messengers.model.Messages;
import org.ram.testwebservice.messengers.model.Profile;
import org.ram.testwebservice.messengers.services.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messgeServices = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Messages> getJsonMessages(@BeanParam MessageFilterBean filterBean){
		System.out.println("JSON");
		if(filterBean.getYear() > 0 ){
			return messgeServices.getAllMessagesForYear(filterBean.getYear());
		}
		
		if(filterBean.getStart() >= 0 && filterBean.getSize() >= 0){
			return messgeServices.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messgeServices.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Messages> getXmlMessages(@BeanParam MessageFilterBean filterBean){
		System.out.println("XML");
		if(filterBean.getYear() > 0 ){
			return messgeServices.getAllMessagesForYear(filterBean.getYear());
		}
		
		if(filterBean.getStart() >= 0 && filterBean.getSize() >= 0){
			return messgeServices.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messgeServices.getAllMessages();
	}
	
	
	@POST

	public Response addMessage(Messages message, @Context UriInfo uriInfo) throws URISyntaxException{
		//System.out.println(uriInfo.getAbsolutePath());
		
		Messages newMessage = messgeServices.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage)
				.build();
		//return messgeServices.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Messages updateMessages(@PathParam("messageId")  long id, Messages message){
		message.setId(id);
		return messgeServices.updateMessage(message);
	}
	
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessages(@PathParam("messageId")  long id){
		messgeServices.removeMessage(id);
	}
	
	
	@GET
	@Path("/{messageId}")
	public Messages getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		
			Messages messag = messgeServices.getMessage(id);
			
			messag.addLinks(getUriForSelf(uriInfo, messag), "self");
			messag.addLinks(getUriForProfile(uriInfo, messag), "profile");
			messag.addLinks(getUriForComments(uriInfo, messag), "commnets");
			
			return messag;
	}

	private String getUriForComments(UriInfo uriInfo, Messages messag) {
		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", messag.getId())
				.build();
		
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Messages messag) {
		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(ProfileResource.class)
				.path(messag.getAuthor())
				.build();
		
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Messages messag) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(Long.toString(messag.getId()))
		.build()
		.toString();
		return uri;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(@PathParam("messageId") long messageId){
		return new CommentResource();
	}
	
	
	
}
