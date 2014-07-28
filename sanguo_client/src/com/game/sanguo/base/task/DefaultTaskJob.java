package com.game.sanguo.base.task;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTaskJob implements Job {

	protected static Logger	logger	= LoggerFactory.getLogger(ClassUtils.getShortClassName(DefaultTaskJob.class));
	
	public DefaultTaskJob()
	{
	}
	
	@Override
	public void execute(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		try {
			GameTask task = (GameTask)(paramJobExecutionContext.getMergedJobDataMap().get("task"));
			List<TaskUnit> 	pipleLineTask=  (List<TaskUnit>)(paramJobExecutionContext.getMergedJobDataMap().get("pipleLineTask"));
			boolean executeResult = task.execute();
			if(pipleLineTask != null && !pipleLineTask.isEmpty())
			{
				Iterator<TaskUnit> ite = pipleLineTask.iterator();
				while(ite.hasNext())
				{
					TaskUnit te = ite.next();
					GameHelper.submitTask(te);
					ite.remove();
				}
			}
		} catch (Throwable e) {
			logger.error("", e);
		}
	}
}
