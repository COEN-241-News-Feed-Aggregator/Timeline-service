package coen.cloud.computing.timelineservice.controller;

import org.springframework.web.bind.annotation.RestController;

import coen.cloud.computing.timelineservice.manager.TimelineManager;
import coen.cloud.computing.timelineservice.model.NewsArticle;
import coen.cloud.computing.timelineservice.repository.TimelineRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/timeline", produces = {"application/json", "text/xml"})
public class TimelineController {
	
	@Autowired
	private TimelineManager _timelineManager;
	
	@GetMapping("/get/{id}")
    public ResponseEntity<List<NewsArticle>> findTimeline(@PathVariable int id) {
		System.out.println("Controller:"+id);
        List<NewsArticle> newsArticles = _timelineManager.getTimelineForUser(id);
        return new ResponseEntity<List<NewsArticle>>(newsArticles, HttpStatus.OK);
    }


}
