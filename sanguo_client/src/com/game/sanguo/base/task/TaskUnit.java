package com.game.sanguo.base.task;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskUnit implements Job {

	protected static Logger	logger	= LoggerFactory.getLogger(TaskUnit.class);
	private GameTask		task;

	private String			cornExpress;
	
	private Date runDate;
	public TaskUnit() {
		super();
	}

	public Date getRunDate() {
		return runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
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

	public String getCornExpress() {
		return cornExpress;
	}

	public void setCornExpress(String cornExpress) {
		this.cornExpress = cornExpress;
	}

	public TaskUnit(GameTask task) {
		super();
		this.task = task;
	}

	public GameTask getTask() {
		return task;
	}

	public void setTask(GameTask task) {
		this.task = task;
	}

	@Override
	public void execute(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		try {
			task.execute();
		} catch (Throwable e) {
			logger.error("", e);
		}
	}
}
