package io.pivotal.pal.tracker.bo;

import java.time.LocalDate;

public class TimeEntry {


	private long id;
	private long projectId;
	private long userId;
	private LocalDate date;
	private int hours;
	private long timeEntryId;
	//private static int 


	
	public TimeEntry() {
		
	}

	public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
		super();
		this.projectId=projectId;
		this.userId=userId;
		this.date=date;
		this.hours=hours;
	}
	
	
	public TimeEntry(long timeEntryId, long projectId, long userId, LocalDate date, int hours ) {
		super();
		this.timeEntryId = timeEntryId;
		this.projectId = projectId;
		this.userId = userId;
		this.date = date;
		this.hours = hours;
		
	}

	
	public long getId() {
		return timeEntryId;
	}
	public void setId(long timeEntryId) {
		this.timeEntryId = timeEntryId;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	

	public long getTimeEntryId() {
		return timeEntryId;
	}


	public void setTimeEntryId(long timeEntryId) {
		this.timeEntryId = timeEntryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + hours;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (projectId ^ (projectId >>> 32));
		result = prime * result + (int) (timeEntryId ^ (timeEntryId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeEntry other = (TimeEntry) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (hours != other.hours)
			return false;
		if (id != other.id)
			return false;
		if (projectId != other.projectId)
			return false;
		if (timeEntryId != other.timeEntryId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	
	
}
