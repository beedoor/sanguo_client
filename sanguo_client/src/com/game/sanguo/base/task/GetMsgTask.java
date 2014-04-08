package com.game.sanguo.base.task;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.UserBean;

public class GetMsgTask extends GameTask {

	public GetMsgTask(UserBean userBean) {
		super();
		this.userBean = userBean;
	}

	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}

	public void doAction() {
		try {
			msgIdGetNotify();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void msgIdGetNotify() {
		PostMethod postMethod = new PostMethod(String.format("%s/ChatServer/dwr/call/plaincall/DwrChat.getChat.dwr;jsessionid%s;mid=%s",userBean.getUrlPrx(),userBean.getSessionId(),userBean.getSessionId()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", userBean.getChatSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrChat"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "getChat"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:" + userBean.getNumberId()));
		postMethod.addParameter(new NameValuePair("c0-e2", "number:2"));
		postMethod.addParameter(new NameValuePair("c0-e3", "number:4"));
		postMethod.addParameter(new NameValuePair("c0-e4", "number:8"));
		postMethod.addParameter(new NameValuePair("c0-param0", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));

		doRequest(postMethod);
	}
}
