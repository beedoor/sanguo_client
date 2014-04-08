package com.game.sanguo.base.task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class GameHelper {
	final static ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	
	public static void submit(GameTask gameTask)
	{
		exec.submit(gameTask);
	}
	public static void scheduleAtFixedRate(GameTask gameTask,int interval,TimeUnit tim)
	{
		exec.scheduleAtFixedRate(gameTask, 0, interval, tim);
	}
	
	public static void terminal()
	{
		exec.shutdownNow();
	}
}

