package io.pivotal.pal.tracker.service;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import io.pivotal.pal.tracker.api.TimeEntryRepository;
import io.pivotal.pal.tracker.bo.TimeEntry;

@Component
public class JdbcTimeEntryRepository  implements TimeEntryRepository{

	JdbcTemplate  jdbcTemplate ;

	@Autowired
	public  JdbcTimeEntryRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public TimeEntry create(TimeEntry entry) {

		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(Connection ->{ 
			
			  PreparedStatement statement = Connection.prepareStatement(
			  		 " Insert into  time_entries(project_id, user_id, date, hours)"
			  		+ "  VALUES (?, ?, ?, ?) "
			  		,RETURN_GENERATED_KEYS);
			  	statement.setLong(1, entry.getProjectId());
	            statement.setLong(2, entry.getUserId());
	            statement.setDate(3, Date.valueOf(entry.getDate()));
	            statement.setInt(4, entry.getHours());
	            return statement;
		},holder);
		

		
		 return find(holder.getKey().longValue());
	}

	
	@Override
	public void  delete(Long key) {
		
		jdbcTemplate.update("DELETE FROM time_entries WHERE id = ?", key);

	  
	}
	
	@Override
    public List<TimeEntry> list() {
        return jdbcTemplate.query("SELECT id, project_id, user_id, date, hours FROM time_entries", mapper);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        jdbcTemplate.update("UPDATE time_entries " +
                "SET project_id = ?, user_id = ?, date = ?,  hours = ? " +
                "WHERE id = ?",
            timeEntry.getProjectId(),
            timeEntry.getUserId(),
            Date.valueOf(timeEntry.getDate()),
            timeEntry.getHours(),
            id);

        return find(id);
    }
	
	@Override
	public TimeEntry find(Long key) {

		return jdbcTemplate.query("SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?",
            new Object[]{key}, rse);
	}
	
	
	private final RowMapper<TimeEntry> mapper = (rs, rowNum) -> new TimeEntry(
	        rs.getLong("id"),
	        rs.getLong("project_id"),
	        rs.getLong("user_id"),
	        rs.getDate("date").toLocalDate(),
	        rs.getInt("hours")
	    );
	
 	
		
	private final ResultSetExtractor<TimeEntry> rse = (rs) -> rs.next() ?  mapper.mapRow(rs, 1) : null;
		
	
	
	
	
}
