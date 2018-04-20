package org.ram.testwebservice.messengers.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.ram.testwebservice.messengers.database.DataBase;
import org.ram.testwebservice.messengers.exception.DataNotFoundException;
import org.ram.testwebservice.messengers.model.Messages;

public class MessageService {

	private Map <Long, Messages> messages = DataBase.getMessages(); 
	
	public MessageService(){
		
		messages.put(1l, new Messages(1, "Hello World", "Ramnath"));
		messages.put(2l, new Messages(2, "Hello Sir", "Ramnath"));
		
	}
	
	public List<Messages> getAllMessages(){
		return new ArrayList<Messages>(messages.values());
	}
	
	public List<Messages> getAllMessagesForYear(int year){
		
		List<Messages> meesagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		for (Messages messages : messages.values()) {
			if(cal.get(Calendar.YEAR) == year){
				meesagesForYear.add(messages);
			}
		}
		return meesagesForYear;
	}
	
	
	public List<Messages> getAllMessagesPaginated(int start, int size){
		List<Messages> list = new ArrayList<>(messages.values());
		if(start+size > list.size())return new ArrayList<Messages>();
		return list.subList(start, start+size);
	}
	
	
	public Messages getMessage(long id){
		 Messages message = messages.get(id);
		 if(message == null){
			 throw new DataNotFoundException("message with id "+id+" not found.");
		 }
		 return message;
	}
	
	public Messages addMessage(Messages message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Messages updateMessage(Messages message){
		if(message.getId() <= 0)
		{
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Messages removeMessage(long id){
		return  messages.remove(id);
	}
	
}
