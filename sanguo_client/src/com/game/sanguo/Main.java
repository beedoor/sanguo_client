package com.game.sanguo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.AutoLqSearchAndGoldTaskTest;
import com.game.sanguo.base.task.CitySearchAndGoldTask;
import com.game.sanguo.base.task.GameHelper;
import com.game.sanguo.base.task.GameNotifyTask;
import com.game.sanguo.base.task.GetTimeZoneTask;
import com.game.sanguo.base.task.LoginTask;
import com.game.sanguo.base.task.TaskUnit;
import com.game.sanguo.base.task.WorldCupTask;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;
import com.game.sanguo.base.util.ResourceConfig;
import com.game.sanguo.base.util.UserConfig;

public class Main {

	protected static Logger	logger	= LoggerFactory.getLogger(Main.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserConfig userConfig = new UserConfig();
		ResourceConfig resourceConfig = new ResourceConfig();
		ItemConfig itemConfig = new ItemConfig();

		userConfig.loadUserConfig();
		resourceConfig.loadResourceConfig();

		itemConfig.loadUserConfig();

		UserBean userBean = userConfig.getUserConfig(null);
		// 加载资源配置
		userBean.setItemConfig(itemConfig);

		PipleLineTask pipleLineTask = new PipleLineTask();
		pipleLineTask.add(new TaskUnit(new GameNotifyTask(userBean, pipleLineTask), "0/10 * * * * ?"));
		pipleLineTask.add(new TaskUnit(new CitySearchAndGoldTask(userBean, itemConfig, pipleLineTask), "0 0/10 * * * ?"));
//		pipleLineTask.add(new TaskUnit(new AutoLqSearchAndGoldTaskTest(userBean, null, pipleLineTask)));
//		pipleLineTask.add(new TaskUnit(new WorldCupTask(userBean,pipleLineTask)));
		// 此任务立刻执行
		GameHelper.submitTask(new TaskUnit(new LoginTask(userBean, pipleLineTask)));
	}
}
