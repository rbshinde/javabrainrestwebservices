package io.javabrains.springbootstater.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicServices topicservice;
	
	@RequestMapping("/topic")
	public List<Topic> topic() {
		return topicservice.getAllTopics();
	}
	
	@RequestMapping("/topic/{id}")
	public Topic getTopic(@PathVariable String id) {
	 
	 return topicservice.getTopic(id);
	 
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topic")
	public void addtopic(@RequestBody Topic topic) {
		topicservice.addTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topic/{id}")
	public void addUpdate(@RequestBody Topic topic, @PathVariable String id) {
		topicservice.addUpdate(id,topic);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topic/{id}")
	public void addDelete(@PathVariable String id) {
		topicservice.addDelete(id);
	}
	
}
