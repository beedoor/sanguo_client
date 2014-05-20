package com.game.sanguo.ui;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Test {

	public static void main(String[] args) {
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			Scheduler sched = sf.getScheduler();
			sched.start();
			// TODO Auto-generated method stub
			// define the job and tie it to our HelloJob class
			JobDetail job = newJob(SimpleJob.class).build();
//			JobDetail jo2b = newJob(SimpleJob2.class).build();
			// Trigger the job to run now, and then repeat every 40 seconds
			CronTrigger trigger = newTrigger().withSchedule(cronSchedule("0/3 * * * * ?")).build();
			// Tell quartz to schedule the job using our trigger
//			sched.scheduleJob(job, trigger);
//			sched.scheduleJob(jo2b, trigger2);
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
