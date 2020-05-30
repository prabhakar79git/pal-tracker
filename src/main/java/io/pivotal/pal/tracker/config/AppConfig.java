package io.pivotal.pal.tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pivotal.pal.tracker.service.InMemoryTimeEntryRepository;

@Configuration
public class AppConfig {
	
	/*/
	@Autowired
	InMemoryTimeEntryRepository imeEntryRepository;
	
	@Bean
	public InMemoryTimeEntryRepository  getTimeEntryRepository() {
		
		return imeEntryRepository;
	}
*/
}
