package coen.cloud.computing.timelineservice.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coen.cloud.computing.timelineservice.model.NewsArticle;
import coen.cloud.computing.timelineservice.repository.TimelineRepo;

@Component
public class TimelineManager {

	@Autowired
	private TimelineRepo _timelineRepo;
	
	 public List<NewsArticle> getTimelineForUser(int userId){
		System.out.println("Manager:"+userId);
		return _timelineRepo.getAllArticlesByUser(userId);
		 
	 }

}
