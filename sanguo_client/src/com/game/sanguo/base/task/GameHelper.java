package com.game.sanguo.base.task;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameHelper {
	protected static Logger	logger	= LoggerFactory.getLogger(GameTask.class);
	static SchedulerFactory	sf		= new StdSchedulerFactory();
	static Scheduler		sched	= null;
	static {
		try {
			sched = sf.getScheduler();
			sched.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void submitTask(TaskUnit taskUnit) {
		if (taskUnit.getRunDate() != null) {
			startAtFixTime(taskUnit);
			return;
		}
		if (taskUnit.getCornExpress() == null) {
			startNow(taskUnit);
			return;
		}

		startSchedule(taskUnit);
	}

	private static void startAtFixTime(TaskUnit taskUnit) {
		try {
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("task", taskUnit.getTask());
			JobDetail job = newJob().ofType(TaskUnit.class).setJobData(jobDataMap).build();
			Trigger trigger = newTrigger().startAt(taskUnit.getRunDate()).build();
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private static void startSchedule(TaskUnit taskUnit) {
		try {
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("task", taskUnit.getTask());
			JobDetail job = newJob().ofType(TaskUnit.class).setJobData(jobDataMap).build();
			CronTrigger trigger = newTrigger().withSchedule(cronSchedule(taskUnit.getCornExpress())).build();
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private static void startNow(TaskUnit taskUnit) {
		try {
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("task", taskUnit.getTask());
			JobDetail job = newJob().ofType(TaskUnit.class).setJobData(jobDataMap).build();
			Trigger trigger = newTrigger().startNow().build();
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public static void terminal() {
		try {
			sched.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
