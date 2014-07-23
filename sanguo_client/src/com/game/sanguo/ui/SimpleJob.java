package com.game.sanguo.ui;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.game.sanguo.base.task.TaskUnit;

public class SimpleJob implements Job {

	public SimpleJob() {

	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("SimpleJob says: " + jobKey);
	}

	public static void main(String args[])throws Exception
	{
		SchedulerFactory	sf		= new StdSchedulerFactory();
		Scheduler		sched	= sf.getScheduler();
		JobDataMap jobDataMap = new JobDataMap();

		JobDetail job = newJob().ofType(SimpleJob.class).setJobData(jobDataMap).build();
		CronTrigger trigger = newTrigger().withSchedule(cronSchedule("* * 7-21 ? * FRI")).build();
		sched.scheduleJob(job, trigger);
		sched.start();
	}
}
