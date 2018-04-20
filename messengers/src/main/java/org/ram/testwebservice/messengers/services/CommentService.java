package org.ram.testwebservice.messengers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.ram.testwebservice.messengers.database.DataBase;
import org.ram.testwebservice.messengers.model.Comment;
import org.ram.testwebservice.messengers.model.ErrorMessage;
import org.ram.testwebservice.messengers.model.Messages;

public class CommentService {
	
	private static Map <Long, Messages> messages  = DataBase.getMessages();
	
	public List<Comment> getAllComments(long messagesId){
		Map<Long, Comment> commnts = messages.get(messagesId).getComments();
		
		return new ArrayList<Comment>(commnts.values());
	}
	
	public Comment getComment(long msgId, long comment){
		ErrorMessage errorMessage = new ErrorMessage("Not Foound", 404, "http://javatpoint.com");
		Response response =  Response.status(Status.NOT_FOUND).
				entity(errorMessage).
				build();
		Messages message = messages.get(msgId);
		if( message == null){
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> commnts = messages.get(msgId).getComments();
		Comment comment2 = commnts.get(comment);
		if( comment2 == null){
			throw new NotFoundException(response);
		}
		return comment2;
	}
	
	public Comment addComment(long msgId, Comment comment){
		
		Map<Long, Comment> commnts = messages.get(msgId).getComments();
		
		comment.setId(commnts.size() + 1);
		commnts.put(comment.getId(), comment);
		
		return comment;
	}
	

	public Comment updateComment(long msgId, Comment comment){
		Map<Long, Comment> commnts = messages.get(msgId).getComments();
		if(comment.getId() <= 0)
		{
			return null;
		}
		commnts.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long msgId, long comment){
		Map<Long, Comment> commnts = messages.get(msgId).getComments();
		return  commnts.remove(comment);
	}
}
