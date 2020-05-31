package io.pivotal.pal.tracker.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.pal.tracker.api.TimeEntryRepository;
import io.pivotal.pal.tracker.bo.TimeEntry;
import io.pivotal.pal.tracker.service.JdbcTimeEntryRepository;

@RestController
public class TimeEntryController {
    
	
	TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.repository=timeEntryRepository;}



	@PostMapping(value = "/time-entries")
	public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry) {

		return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.create(entry));
	}

	@GetMapping(value = "/time-entries")
	public ResponseEntity<List<TimeEntry>> list() {

		return ResponseEntity.status(HttpStatus.OK).body(this.repository.list());
	}

	@GetMapping(value = "/time-entries/{id}")
	public ResponseEntity<TimeEntry> read(@PathVariable("id") Long id) {
		
		
		TimeEntry entry = this.repository.find(id);  
		
		/*
		if (this.repository.find(id)!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(this.repository.find(id)); 
		}
*/
		
		if(entry!=null) {
			
			return ResponseEntity.status(HttpStatus.OK).body(entry); 
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
	}
	

	@PutMapping(value = "/time-entries/{id}")
	public ResponseEntity<TimeEntry> update(@PathVariable("id") Long id,@RequestBody TimeEntry entry) {
		
		System.out.println(" Read Method in contorler and param is " +id);

		TimeEntry  timeEntry = this.repository.update(id, entry);
		
		if(timeEntry !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(timeEntry);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping(value = "/time-entries/{id}" )
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	}
