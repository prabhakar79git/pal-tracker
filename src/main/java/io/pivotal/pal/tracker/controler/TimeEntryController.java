package io.pivotal.pal.tracker.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.api.TimeEntryRepository;
import io.pivotal.pal.tracker.bo.TimeEntry;

@RestController
public class TimeEntryController {
    
	private TimeEntryRepository repository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;
    
    public TimeEntryController(
            TimeEntryRepository timeEntriesRepo,
            MeterRegistry meterRegistry
    ) {
        this.repository = timeEntriesRepo;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @GetMapping("/")
    public String sayHello(@Value("${welcome.message}")String message ) {
        return message;
    }
    @GetMapping("/env")
    public Map<String, String> getEnv() {
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("PORTS", "20");
    	map.put("MEMORY_LIMIT","30");
    	map.put("CF_INSTANCE_INDEX", "40");
    	map.put("CF_INSTANCE_ADDR", "50");
    	
        return map;
    }

	@PostMapping(value = "/time-entries")
	public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry) {

		TimeEntry enttry = this.repository.create(entry);
		actionCounter.increment();
		 timeEntrySummary.record(repository.list().size());
		return ResponseEntity.status(HttpStatus.CREATED).body(enttry);
	}

	@GetMapping(value = "/time-entries")
	public ResponseEntity<List<TimeEntry>> list() {
		actionCounter.increment();
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
			actionCounter.increment();
			return ResponseEntity.status(HttpStatus.OK).body(entry); 
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
	}
	

	@PutMapping(value = "/time-entries/{id}")
	public ResponseEntity<TimeEntry> update(@PathVariable("id") Long id,@RequestBody TimeEntry entry) {
		
		System.out.println(" Read Method in contorler and param is " +id);

		TimeEntry  timeEntry = this.repository.update(id, entry);
		
		if(timeEntry !=null) {
			  actionCounter.increment();
			return ResponseEntity.status(HttpStatus.OK).body(timeEntry);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping(value = "/time-entries/{id}" )
	public ResponseEntity delete(@PathVariable("id") Long id) {
		
		this.repository.delete(id);
		 actionCounter.increment();
	        timeEntrySummary.record(repository.list().size());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	}
