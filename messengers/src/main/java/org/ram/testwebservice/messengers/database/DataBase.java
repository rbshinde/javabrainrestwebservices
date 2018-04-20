package org.ram.testwebservice.messengers.database;

import java.util.HashMap;
import java.util.Map;

import org.ram.testwebservice.messengers.model.Comment;
import org.ram.testwebservice.messengers.model.Messages;
import org.ram.testwebservice.messengers.model.Profile;

public class DataBase {

	private static Map <Long, Messages> messages = new HashMap<>();
	private static Map <String, Profile> profiles = new HashMap<>();
	private static Map <Long, Comment> comments = new HashMap<>();
	
	public static Map<Long, Messages> getMessages(){
		return messages;
	}
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	} 
	
	public static Map <Long, Comment> getComments(){
		return comments;
	} 
	
	
	
}
