package coen.cloud.computing.timelineservice.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class NewsArticle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonSerialize
	int id;
	@JsonSerialize
	String title;
	@JsonSerialize
	String author;
	@JsonSerialize
	String publishedDate;
	@JsonSerialize
	String content;
	@JsonSerialize
	String webUrl;
	@JsonSerialize
	String imageUrl;
	@JsonSerialize
	List<String> topics;
	
	public NewsArticle(int id, String title, String author, String publishedDate, String content, String webUrl, String imageUrl, List<String> topics) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishedDate = publishedDate;
		this.content = content;
		this.webUrl = webUrl;
		this.imageUrl = imageUrl;
		this.topics = topics;
	}
	
	public NewsArticle updateTopicList(String topic) {
		this.topics.add(topic);
		return this;
	}
	

}
