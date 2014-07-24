package com.game.sanguo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.sanguo.base.domain.FightItem;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.CitySearchAndGoldTask;
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

	protected static Logger	logger	= LoggerFactory.getLogger(AutoDefine.class);

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
			userBean.setReLoginTime(30L);

			List<TaskUnit> pipleLineTask = new ArrayList<TaskUnit>();
			pipleLineTask.add(new TaskUnit(new GameNotifyTask(userBean), "0/10 * * * * ?"));
			// 维持会话的获取通知信息惹任务

			pipleLineTask.add(new TaskUnit(new MsgTypeWorldPvpFightTask(userBean, fightItem), "0 1 * * * ?"));
			// 此任务立刻执行
			TaskUnit loginTask = new TaskUnit(new LoginTask(userBean));
			loginTask.setPipleLineTask(pipleLineTask);
			GameHelper.submitTask(loginTask);
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
