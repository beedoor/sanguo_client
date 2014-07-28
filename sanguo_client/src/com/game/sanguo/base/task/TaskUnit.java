package com.game.sanguo.base.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.sanguo.base.util.PipleLineTask;

public class TaskUnit {

	private GameTask		task;
	private List<TaskUnit> 	pipleLineTask;
	private String			cornExpress;
	
	private Date runDate;


	public TaskUnit()
	{
		
	}
	

	public List<TaskUnit> getPipleLineTask() {
		return pipleLineTask;
	}

	public void setPipleLineTask(List<TaskUnit> pipleLineTask) {
		this.pipleLineTask = pipleLineTask;
	}

	public TaskUnit(GameTask task, Date runDate) {
		super();
		this.task = task;
		this.runDate = runDate;
	}

	public TaskUnit(GameTask task, String cornExpress) {
		super();
		this.task = task;
		this.cornExpress = cornExpress;
	}

	public TaskUnit(GameTask task) {
		super();
		this.task = task;
	}

	public Date getRunDate() {
		return runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	
	public String getCornExpress() {
		return cornExpress;
	}

	public void setCornExpress(String cornExpress) {
		this.cornExpress = cornExpress;
	}

	

	public GameTask getTask() {
		return task;
	}

	public void setTask(GameTask task) {
		this.task = task;
	}
}
