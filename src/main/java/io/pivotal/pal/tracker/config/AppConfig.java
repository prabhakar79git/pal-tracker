package io.pivotal.pal.tracker.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.cj.jdbc.MysqlDataSource;

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
	
	@Bean 
	public DataSource getDataSource() {
	 MysqlDataSource dataSource = new MysqlDataSource();
     dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));
//	 dataSource.setUrl(" jdbc:mysql://localhost:3306/tracker_dev?user=tracker&useSSL=false&useTimezone=true&serverTimezone=UTC&useLegacyDatetimeCode=false");
     return dataSource;
	}

}
