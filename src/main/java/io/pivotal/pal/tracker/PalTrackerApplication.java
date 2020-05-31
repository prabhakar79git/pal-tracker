package io.pivotal.pal.tracker;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.pivotal.pal.tracker.api.TimeEntryRepository;
import io.pivotal.pal.tracker.service.JdbcTimeEntryRepository;

@SpringBootApplication
public class PalTrackerApplication {
	
	public static void main(String[] args) {
		 SpringApplication.run(PalTrackerApplication.class, args);
	}
	
	
	

}
