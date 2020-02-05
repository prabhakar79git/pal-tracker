package io.pivotal.pal.tracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.pivotal.pal.tracker.api.TimeEntryRepository;
import io.pivotal.pal.tracker.bo.TimeEntry;

//@Component
//public class InMemoryTimeEntryRepository  implements TimeEntryRepository{
	public class InMemoryTimeEntryRepository  {

	private static Long uniqID = 1L;

	private  Map<Long,TimeEntry> repository = new HashMap<Long,TimeEntry>();


	public TimeEntry create(TimeEntry entry) {

		System.out.println("unique id  id " +uniqID);
		
		repository.put(
				uniqID, new TimeEntry(uniqID.longValue(),
						entry.getProjectId(), 
						entry.getUserId(),
						entry.getDate(), entry.getHours()));

		
		return repository.get(uniqID++);
	}

	public List<TimeEntry> list() {

		return  repository.values().stream().collect(Collectors.toList());
	}

	public TimeEntry update(Long key, TimeEntry entry) {
		
		System.out.println(" Update Called and Key is " +key +" database data is "+ this.repository.toString());

		if(repository.containsKey(key)) {

			repository.remove(key);
			repository.put(
					key, new TimeEntry(key.longValue(),
							entry.getProjectId(), 
							entry.getUserId(),
							entry.getDate(), entry.getHours()));

			return repository.get(key);
		}

		return null;
	}
	
	public boolean delete(Long key) {

		System.out.println(" Update Called and Key is " +key +" database data is "+ this.repository.toString());
		
		if(repository.containsKey(key)) {
			repository.remove(key);
		}
		return false;
	}
	
	public TimeEntry find(Long key) {

		System.out.println(" read is Called and Key is " +key +" database data is "+ this.repository.toString());
		
		if(repository.containsKey(key)) {
			return repository.get(key);
		}
		return null;
	}
	
	public static void setIdToDefault() {
		uniqID = 1L;
	}
}
