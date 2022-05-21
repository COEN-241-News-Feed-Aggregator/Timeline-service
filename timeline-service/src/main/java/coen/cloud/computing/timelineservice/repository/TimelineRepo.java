package coen.cloud.computing.timelineservice.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import coen.cloud.computing.timelineservice.model.NewsArticle;

@Component
public class TimelineRepo {

	@Value("${spring.datasource.username}")
	private String userName;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String serverName;
	
	public List<NewsArticle> getAllArticlesByUser(int userId){
		System.out.println("Repo entry:");
		Map <Integer, NewsArticle> newsArticles = new HashMap<Integer, NewsArticle>();
		
		try {
			String query = "select  a.id, a.title, a.author, a.publish_date, a.description, a.web_url, t.name\n"
					+ "from articles a join topic_article_mapping tam on a.id=tam.article_id \n"
					+ "join topics t on tam.topic_id = t.id\n"
					+ "join user_topic_mapping utm on t.id = utm.id\n"
					+ "where utm.user_id =" + userId;
			System.out.println("Query: "+query);
			ResultSet rs = this.executeSelectQuery(getConnection(), query);
			while (rs.next()) {
				List<String> topics = new ArrayList<String>();
				if(!newsArticles.isEmpty()) {
					if(newsArticles.get(rs.getInt("id")) != null) {
						NewsArticle article = newsArticles.get(rs.getInt("id"));
						article.updateTopicList(rs.getString("name"));
						newsArticles.put(rs.getInt("id"), article);
					}
					else {
						topics.add(rs.getString("name"));
						NewsArticle newsArticle = new NewsArticle(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("publish_date"), rs.getString("description"), 
								rs.getString("web_url"), "", topics);
						
						newsArticles.put(rs.getInt("id"), newsArticle);
					}
				}
				
				else {
					topics.add(rs.getString("name"));
					NewsArticle newsArticle = new NewsArticle(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("publish_date"), rs.getString("description"), 
							rs.getString("web_url"), "", topics);
					
					newsArticles.put(rs.getInt("id"), newsArticle);
				}
				
								
			}
			return new ArrayList(newsArticles.values());
		}
		catch (SQLException e) {
			return null;
		}
		
	}
	
	public ResultSet executeSelectQuery(Connection conn, String command) throws SQLException {
	    Statement stmt = conn.createStatement();
	    stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(command);
        if (stmt.execute(command)) {
            rs = stmt.getResultSet();
        }
        return rs;
	}
	
	public Connection getConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		try {
			return DriverManager.getConnection(this.serverName,connectionProps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
