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

		// PipleLineTask pipleLineTask = new PipleLineTask();

		logger.info("自动采集任务开启");
		Calendar cal = Calendar.getInstance();
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
			
			PipleLineTask pipleLineTask = new PipleLineTask();
			logger.info("开始处理:"+fightItem.getName());
			if(fightItem.getIsOnlyLogin() == 0)
			{		
				logger.info(fightItem.getName()+" 登陆需保持的任务");
				pipleLineTask.add(new TaskUnit(new GameNotifyTask(userBean, pipleLineTask), "0/10 * * * * ?"));
				pipleLineTask.add(new TaskUnit(new CitySearchAndGoldTask(userBean, itemConfig, pipleLineTask), "0 0/10 * * * ?"));
//				if(fightItem.getIsAutoLqLogin() == 1)
//				{
//					logger.info("添加自动领取登陆奖励:"+fightItem.getName());
//					pipleLineTask.add(new TaskUnit(new ContinuousLoginDaysRewardTask(userBean, pipleLineTask)));
//				}

				if(fightItem.getNotDonate() == 0)
				{
					logger.info("添加自动捐献:"+fightItem.getName());
					pipleLineTask.add(new TaskUnit(new GemDonateTask(userBean,itemConfig, pipleLineTask)));
				}
				GameHelper.submitTask(new TaskUnit(new LoginTask(userBean, pipleLineTask)));
			}else
			{
				logger.info("登陆无需保持的任务");
				cal.add(Calendar.MINUTE, 1);
//				if(fightItem.getIsAutoLqLogin() == 1)
//				{
//					logger.info("添加自动领取登陆奖励:"+fightItem.getName());
//					pipleLineTask.add(new TaskUnit(new ContinuousLoginDaysRewardTask(userBean, pipleLineTask)));
//				}
				// 维持会话的获取通知信息惹任务
				// 此任务立刻执行
				if(fightItem.getNotDonate() == 0)
				{
					logger.info("添加自动捐献:"+fightItem.getName());
					pipleLineTask.add(new TaskUnit(new GemDonateTask(userBean,itemConfig, pipleLineTask)));
				}
				GameHelper.submitTask(new TaskUnit(new LoginTask(userBean, pipleLineTask),cal.getTime()));
			}
//			pipleLineTask.add(new TaskUnit(new AutoChoujiangTask(userBean, pipleLineTask)));
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
