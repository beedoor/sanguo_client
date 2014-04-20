package com.game.sanguo;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.sanguo.base.domain.FightItem;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.ContinuousLoginDaysRewardTask;
import com.game.sanguo.base.task.GameHelper;
import com.game.sanguo.base.task.GameNotifyTask;
import com.game.sanguo.base.task.LoginTask;
import com.game.sanguo.base.task.MsgTypeWorldPvpFightTask;
import com.game.sanguo.base.task.TaskUnit;
import com.game.sanguo.base.util.AutoFightConfig;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;
import com.game.sanguo.base.util.ResourceConfig;
import com.game.sanguo.base.util.UserConfig;

public class AutoDefine {

	protected static Logger logger = LoggerFactory.getLogger(AutoDefine.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserConfig userConfig = new UserConfig();
		ResourceConfig resourceConfig = new ResourceConfig();
		ItemConfig itemConfig = new ItemConfig();
		AutoFightConfig autoFightConfig = new AutoFightConfig();

		userConfig.loadUserConfig();
		resourceConfig.loadResourceConfig();
		itemConfig.loadUserConfig();
		autoFightConfig.loadUserConfig();

		for(FightItem fightItem:autoFightConfig.getFightUserConfig())
		{
			UserBean userBean = new UserBean();
			userBean.setAreaId(8L);
			userBean.setUserName(fightItem.getName());
			userBean.setPassword(fightItem.getPassword());
			userBean.setItemConfig(itemConfig);
			userBean.setReLoginTime(30L);
			
			PipleLineTask pipleLineTask = new PipleLineTask();
			pipleLineTask.add(new TaskUnit(new GameNotifyTask(userBean,pipleLineTask),0,10L,TimeUnit.SECONDS));
			pipleLineTask.add(new TaskUnit(new ContinuousLoginDaysRewardTask(userBean,pipleLineTask),0,24,TimeUnit.HOURS));
			// 维持会话的获取通知信息惹任务
			
			long delay = getDelayTime();
//			long delay = 0;
			logger.info(String.format("预计【%s】 秒后【%s】开始对【%s】进行保护", delay,fightItem.getName(),fightItem.getTarget()));
			pipleLineTask.add(new TaskUnit(new MsgTypeWorldPvpFightTask(userBean,fightItem,pipleLineTask),delay,3600L,TimeUnit.SECONDS));
			//此任务立刻执行
			
			GameHelper.submit(new TaskUnit(new LoginTask(userBean,pipleLineTask),0L,0L,TimeUnit.HOURS));
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static long getDelayTime() {
		Calendar cal = Calendar.getInstance();
		long delay =0;
		if(cal.get(Calendar.MINUTE) > 10)
		{
			delay = ((60-cal.get(Calendar.MINUTE))*60)-cal.get(Calendar.SECOND);
		}
		return delay;
	}
}
