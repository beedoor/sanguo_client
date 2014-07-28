package com.game.sanguo.base.task;

import java.io.InputStream;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.GameUtil;
import com.game.sanguo.base.util.PipleLineTask;

public class AutoChoujiangTask extends GameTask {

	public AutoChoujiangTask(UserBean userBean) {
		this.userBean = userBean;
	}

	public boolean doAction() {
		try {
			doChoujiang();
		} catch (Throwable e) {
			logger.error("AutoChoujiangTask error");
		}
		return true;
	}

	private void doChoujiang() {
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
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod.addParameter(new NameValuePair("c0-e2", "string:msgTypeItem"));
		postMethod.addParameter(new NameValuePair("c0-e3", "string:msgIdLoginDrawUse"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:0"));
		postMethod.addParameter(new NameValuePair("c0-param1", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		InputStream inputStream=doRequest(postMethod);
		
		logger.info(userBean.getUserName()+"\t"+GameUtil.parseUnicode(inputStream));
		
	}

}
