package coen.cloud.computing.timelineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class TimelineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimelineServiceApplication.class, args);
	}

}
