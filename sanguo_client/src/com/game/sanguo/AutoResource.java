package com.game.sanguo;

import java.util.ArrayList;
import java.util.List;
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
import com.game.sanguo.base.task.GemDonateTask;
import com.game.sanguo.base.task.GetTimeZoneTask;
import com.game.sanguo.base.task.LoginTask;
import com.game.sanguo.base.task.TaskUnit;
import com.game.sanguo.base.task.WorldCupTask;
import com.game.sanguo.base.util.AutoFightConfig;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;
import com.game.sanguo.base.util.ResourceConfig;
import com.game.sanguo.base.util.UserConfig;
import com.ibm.icu.util.Calendar;

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

		logger.info("自动采集任务开启");

		for (FightItem fightItem : autoFightConfig.getFightUserConfig()) {
			UserBean userBean = new UserBean();
//			userBean.setAreaId(8L);
			userBean.setUserName(fightItem.getName());
			userBean.setPassword(fightItem.getPassword());
			userBean.setItemConfig(itemConfig);
			userBean.setAreaName(fightItem.getArea());
			userBean.setReLoginTime(60L);
			userBean.setVipLv(fightItem.getVipLv());
			userBean.setNotDonate(fightItem.getNotDonate());
			
			List<TaskUnit> pipleLineTask = new ArrayList<TaskUnit>();
			pipleLineTask.add(new TaskUnit(new GameNotifyTask(userBean), "0/10 * * * * ?"));
			pipleLineTask.add(new TaskUnit(new CitySearchAndGoldTask(userBean, itemConfig), "0 0/10 * * * ?"));
		
			TaskUnit loginTask = new TaskUnit(new LoginTask(userBean));
			loginTask.setPipleLineTask(pipleLineTask);
			GameHelper.submitTask(loginTask);
		}
	}
}
