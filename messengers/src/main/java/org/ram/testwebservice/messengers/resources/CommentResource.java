package org.ram.testwebservice.messengers.resources;

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

import org.ram.testwebservice.messengers.model.Comment;
import org.ram.testwebservice.messengers.services.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService = new CommentService(); 
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId){
		return commentService.getAllComments(messageId);
	}

	@POST
	public Comment addMessages(@PathParam("messageId") long msgId , Comment commnet){
		return commentService.addComment(msgId, commnet);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateMessage(@PathParam("messageId") long msgId , @PathParam("commentId") long commentId , Comment commnet){
		
		commnet.setId(commentId);
		return commentService.updateComment(msgId, commnet);
		
	}
	
	@DELETE
	@Path("/{commentId}")
	public void delteCommnet(@PathParam("messageId") long msgId , @PathParam("commentId") long commentId){
		commentService.removeComment(msgId, commentId);
	}

	@GET
	@Path("/{commentId}")
	public Comment getMessages(@PathParam("messageId") long msgId , @PathParam("commentId") long commentId){
		return commentService.getComment(msgId, commentId);
	}
}
