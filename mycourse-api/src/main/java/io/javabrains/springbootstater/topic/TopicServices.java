package io.javabrains.springbootstater.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicServices {

	List<Topic> topic = new ArrayList<>( Arrays.asList(
			new Topic("java", "java framework", "java desc"),
			new Topic("spring", "spring framework", "speing desc")
			
			) );
	
	public List<Topic> getAllTopics(){
		return topic;
	}
	
	public Topic getTopic(String id) {
		return topic.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}

	public void addTopic(Topic topic2) {
		topic.add(topic2);
	}

	public void addUpdate(String id, Topic topic2) {
		for (int i = 0; i < topic.size(); i++) {
			Topic t = topic.get(i);
			if(t.getId().equals(id)) {
				topic.set(i, topic2);
			}
		}
	}

	public void addDelete(String id) {
		topic.removeIf(t -> t.getId().equals(id));
	}
}
