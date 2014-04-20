package com.game.sanguo.base.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameHelper {
	protected static Logger logger = LoggerFactory.getLogger(GameTask.class);
	final static ScheduledExecutorService exec = Executors.newScheduledThreadPool(100);

	public static void submit(TaskUnit taskUnit) {
		if (taskUnit.getPeriod() == 0) {
			if (taskUnit.getDelay() == 0) {
				logger.info("1\t"+taskUnit.toString());;
				exec.submit(taskUnit.getTask());
			} else {
				logger.info("2\t"+taskUnit.toString());;
				exec.schedule(taskUnit.getTask(), taskUnit.getDelay(), taskUnit.getTimeUnit());
			}
		} else {
			logger.info("3\t"+taskUnit.toString());;
			exec.scheduleAtFixedRate(taskUnit.getTask(), taskUnit.getDelay(), taskUnit.getPeriod(), taskUnit.getTimeUnit());
		}
	}

	public static void terminal() {
		exec.shutdownNow();
	}
}
