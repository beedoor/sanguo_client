package com.game.sanguo;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.sanguo.base.domain.FightItem;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.AutoChoujiangTask;
import com.game.sanguo.base.task.AutoLqSearchAndGoldTaskTest;
import com.game.sanguo.base.task.CitySearchAndGoldTask;
import com.game.sanguo.base.task.ContinuousLoginDaysRewardTask;
import com.game.sanguo.base.task.GameHelper;
import com.game.sanguo.base.task.GameNotifyTask;
import com.game.sanguo.base.task.GetTimeZoneTask;
import com.game.sanguo.base.task.LoginTask;
import com.game.sanguo.base.task.TaskUnit;
import com.game.sanguo.base.task.WorldCupTask;
import com.game.sanguo.base.util.AutoFightConfig;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;
import com.game.sanguo.base.util.ResourceConfig;
import com.game.sanguo.base.util.UserConfig;

public class AutoResource {

	protected static Logger	logger	= LoggerFactory.getLogger(AutoResource.class);

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

		// PipleLineTask pipleLineTask = new PipleLineTask();
		// UserBean userBean = new UserBean();
		// userBean.setAreaId(8L);
		// GameHelper.submit(new TaskUnit(new
		// LoginOtherPlatformTask(userBean,pipleLineTask),0L,0L,TimeUnit.HOURS));

		for (FightItem fightItem : autoFightConfig.getFightUserConfig()) {
			UserBean userBean = new UserBean();
			userBean.setAreaId(8L);
			userBean.setUserName(fightItem.getName());
			userBean.setPassword(fightItem.getPassword());
			userBean.setItemConfig(itemConfig);
			userBean.setReLoginTime(60L);

			PipleLineTask pipleLineTask = new PipleLineTask();
			pipleLineTask.add(new TaskUnit(new GameNotifyTask(userBean, pipleLineTask), "0/10 * * * * ?"));
			pipleLineTask.add(new TaskUnit(new CitySearchAndGoldTask(userBean, itemConfig, pipleLineTask), "0 0/10 * * * ?"));
			//pipleLineTask.add(new TaskUnit(new ContinuousLoginDaysRewardTask(userBean, pipleLineTask), "0 0 5 * * ?"));
			pipleLineTask.add(new TaskUnit(new AutoChoujiangTask(userBean, pipleLineTask), "0 0 0/1 * * ?"));

			
			// 维持会话的获取通知信息惹任务
			// 此任务立刻执行
			GameHelper.submitTask(new TaskUnit(new LoginTask(userBean, pipleLineTask)));
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
