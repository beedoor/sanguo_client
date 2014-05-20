package com.game.sanguo.base.task;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.EquipmentItem;
import com.game.sanguo.base.domain.EquipmentItemSellInfo;
import com.game.sanguo.base.domain.LoginGameInfo;
import com.game.sanguo.base.domain.PlayerItemsInfo;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;

public class MsgItemSellTask extends GameTask {
	ItemConfig itemConfig;
	public MsgItemSellTask(UserBean userBean, ItemConfig itemConfig,PipleLineTask pipleLineTask) {
		super(pipleLineTask);
		this.userBean = userBean;
		this.itemConfig = itemConfig;
	}

	@Override
	protected boolean doAction() {
		try {
			if (userBean.getConfigure().getSellConfig().getSell() == 0) {
				logger.info("禁止自动贩卖装备");
				return true;
			} 
			logger.info("开始自动贩卖装备");
			// 获取待卖出的物品列表
			LoginGameInfo loginGameInfo = userBean.getLoginGameInfo();
			List<PlayerItemsInfo> playerItemsInfoList = loginGameInfo.getPlayerItemsInfoList();
			if (playerItemsInfoList != null) {
				Iterator<PlayerItemsInfo> ite = playerItemsInfoList.iterator();
				while (ite.hasNext()) {
					PlayerItemsInfo playerItemsInfo = ite.next();
					EquipmentItem equipmentItem = itemConfig.decodeEquipment(playerItemsInfo.getSourceId());
					if (null != equipmentItem) {
						// 判断装备类别,没有装备，并且非粉色装备，并且强化等级为0的卖掉，狗日的
						if(equipmentItem.getQuality() >=5)
						{
							logger.info(String.format("装备[%s]品级大于紫色，不卖", equipmentItem.getName()));
							continue;
						}
						if (equipmentItem.getMax_smith() < 30 && playerItemsInfo.getStrengthenLevel() == 0 ) {
							if (userBean.getConfigure().getSellConfig().getLevel() > equipmentItem.getNeed_level()) {
								logger.info(String.format("准备卖掉[%s]", equipmentItem.getName()));
								EquipmentItemSellInfo beanInfo = msgIdItemSell(playerItemsInfo.getId());
								sleep(1, TimeUnit.SECONDS);
								if (beanInfo.getErrCode() == 0) {
									logger.info(String.format("贩卖装备[%s]成功,当前金币[%s]", equipmentItem.getName(), beanInfo.getGold()));
									//卖掉后清理
									ite.remove();
								} else {
									logger.info(String.format("贩卖装备[%s]失败,异常原因[%s]", equipmentItem.getName(), beanInfo.getErrMsg()));
								}
							} else {
								logger.info(String.format("装备[%s]，等级[%s]，高于设置卖出的最高等级[%s]，不卖", equipmentItem.getName(), equipmentItem.getNeed_level(), userBean.getConfigure().getSellConfig().getLevel()));
							}
						}
					}
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private EquipmentItemSellInfo msgIdItemSell(Long itemId) {
		PostMethod postMethod = new PostMethod(String.format("%s/hero/dwr/call/plaincall/DwrGameWorld.setMsg.dwr;jsessionid=%s;mid=%s", userBean.getUrlPrx(), userBean.getSessionId(),
				userBean.getSessionId()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		// 使用系统提供的默认的恢复策略
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", userBean.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "setMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "" + userBean.getNumberId()));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:" + itemId));
		postMethod.addParameter(new NameValuePair("c0-e2", "string:msgTypeItem"));
		postMethod.addParameter(new NameValuePair("c0-e3", "string:msgIdItemSell"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:"));
		postMethod.addParameter(new NameValuePair("c0-param1", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		InputStream inputStream=doRequest(postMethod);
		try {
			EquipmentItemSellInfo beanInfo = initBeanInfo(EquipmentItemSellInfo.class, inputStream, "dwr");
			return beanInfo;
		} catch (Throwable ex) {
			logger.error(String.format("贩卖装备[%s]异常", itemId), ex);
		}

		return null;
	}
}
