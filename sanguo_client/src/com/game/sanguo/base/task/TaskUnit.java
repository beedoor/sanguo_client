package com.game.sanguo.base.task;

import java.util.concurrent.TimeUnit;

public class TaskUnit {

	private GameTask task;
	private long delay;
	private long period;
	private TimeUnit timeUnit;
	
	public TaskUnit(GameTask task, long delay, long period, TimeUnit timeUnit) {
		super();
		this.task = task;
		this.delay = delay;
		this.period = period;
		this.timeUnit = timeUnit;
	}
	public GameTask getTask() {
		return task;
	}
	public void setTask(GameTask task) {
		this.task = task;
	}
	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	public long getPeriod() {
		return period;
	}
	public void setPeriod(long period) {
		this.period = period;
	}
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	@Override
	public String toString() {
		return "TaskUnit [task=" + task + ", delay=" + delay + ", period=" + period + ", timeUnit=" + timeUnit + "]";
	}
	
	
}
