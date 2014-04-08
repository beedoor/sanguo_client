package com.game.sanguo.base.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.sanguo.base.domain.LoginByEmailInfo;
import com.game.sanguo.base.domain.Pair;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.GameUtil;

public abstract class GameTask implements Runnable {

	UserBean userBean = null;
	protected static Logger logger = LoggerFactory.getLogger(GameTask.class);

	protected static Lock loginLock = new ReentrantReadWriteLock().writeLock();
	static HttpClient httpClient = new HttpClient();

	public GameTask() {
		super();
	}

	public void run() {
		boolean lockFlag = false;
		try {
			lockFlag = loginLock.tryLock(10, TimeUnit.SECONDS);
			if (lockFlag) {
				doAction();
			} else {
				logger.info("暂时没有登录，无法执行该请求，等待登录");
			}
		} catch (Throwable e) {
			logger.error("任务异常", e);
		} finally {
			if (lockFlag) {
				loginLock.unlock();
			}
		}
	}

	protected abstract void doAction();

	protected void doRequest(PostMethod postMethod) {
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + postMethod.getStatusLine());
			}
			printResponseInfo(postMethod);
		} catch (Throwable e) {
			logger.error(postMethod.getPath(), e);
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}

	protected void sleep(long unit, TimeUnit tu) {
		try {
			tu.sleep(unit);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void sleep(int unit) {
		sleep(unit, TimeUnit.SECONDS);
	}

	private String getParamValue(PostMethod method, String key) {
		NameValuePair nv = method.getParameter(key);
		if (nv == null) {
			return "";
		}
		return nv.getValue();
	}

	protected void printResponseInfo(PostMethod postMethod) throws Exception {
		logger.debug(String.format("response for 【%s】 【%s】【%s】 【%s】【%s】 【%s】【%s】  ", getParamValue(postMethod, "c0-scriptName"), getParamValue(postMethod, "c0-methodName"),
				getParamValue(postMethod, "c0-e2"), getParamValue(postMethod, "c0-e3"), userBean.getSessionId(), userBean.getNumberIdNoIncrement(), userBean.getBatchIdNoIncrement()));
		printHeader(postMethod);
		printBody(postMethod);
		logger.debug("response end ");
	}

	private void printBody(PostMethod postMethod) throws Exception {
		String responseStr = GameUtil.parseUnicode(postMethod.getResponseBodyAsString());
		logger.debug(responseStr);
		if (responseStr.indexOf("java.lang.Throwable") != -1) {
			// 会话失效，重新登录吧
			logger.info(String.format("会话失效，[%s]分钟后重新登录", userBean.getReLoginTime()));
			sleep(userBean.getReLoginTime(), TimeUnit.MINUTES);
			new LoginTask(userBean).doAction();
		}
	}

	private void printHeader(PostMethod postMethod) throws Exception {
		for (Header header : postMethod.getResponseHeaders()) {
			logger.debug(GameUtil.parseUnicode(header.toString()));
		}
	}

	private static String getResponseContent(String responseContent) {
		if (responseContent == null || responseContent.equals("")) {
			return null;
		}
		int startIdx = responseContent.indexOf("{");
		int endIdx = responseContent.lastIndexOf("}");
		String subStr = responseContent.substring(startIdx + 1, endIdx);
		return subStr;
	}

	protected <T> T initBeanInfo(Class<T> classType, InputStream inputStream, String prex) {
		T classInstance = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String s1 = null;
			while ((s1 = br.readLine()) != null) {
				if (s1.startsWith(prex)) {
					break;
				}
			}
			classInstance = initBeanInfo(classType, s1);
		} catch (Throwable e) {
			logger.error("解析报文异常", e);
		}
		return classInstance;
	}

	protected static <T> T initBeanInfo(Class<T> classType, String content) {
		content = getResponseContent(content);
		T classInstance = initBeanInfo(classType, content, ',', ':');
		return classInstance;
	}

	protected static <T> T initBeanInfo(Class<T> classType, String content, char wordSplit, char valueSplit) {
		T classInstance = null;
		try {
			if (null == content || content.equals("")) {
				return null;
			}
			classInstance = classType.newInstance();
			List<Pair<String, String>> filedInfoArray = split(content, wordSplit, valueSplit);
			for (Pair<String, String> filedInfo : filedInfoArray) {
				setDateField(classType, classInstance, filedInfo);
			}
		} catch (Throwable e) {
		}
		return classInstance;
	}

	private static <T> void setDateField(Class<T> classType, T classInstance, Pair<String, String> filedInfo) {
		try {
			String methodName = String.format("set%s%s", filedInfo.first.substring(0, 1).toUpperCase(), filedInfo.first.substring(1));
			Method m = getMethod(classType, methodName, new Class[] { String.class });
			if (m != null) {
				if (!m.isAccessible()) {
					m.setAccessible(true);
				}
				m.invoke(classInstance, filedInfo.second);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static <T> Method getMethod(Class<T> classType, String methodName, Class<?>... paramTypes) {
		try {
			Method m = classType.getDeclaredMethod(methodName, new Class[] { String.class });
			return m;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<Pair<String, String>> split(String s, char wordSplit, char valueSplit) {
		if (s.charAt(s.length() - 1) != wordSplit) {
			s += String.valueOf(wordSplit);
		}
		List<Pair<String, String>> splitResult = new ArrayList<Pair<String, String>>();
		StringBuilder sBuffer = new StringBuilder();
		String key = null, value = null;
		int endC = 0;
		boolean Eflag = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == valueSplit && endC == 0 && !Eflag) {
				key = sBuffer.toString();
				sBuffer.setLength(0);
			} else if (c == wordSplit && endC == 0 && !Eflag) {
				value = GameUtil.parseUnicode(sBuffer.toString());
				if (value.startsWith("\"") && value.endsWith("\"")) {
					value = value.substring(1, value.length() - 1);
				}
				sBuffer.setLength(0);
				splitResult.add(Pair.makePair(key, value));
				key = null;
				value = null;
			} else if (c == '{') {
				endC--;
				sBuffer.append(c);
			} else if (c == '}' && endC < 0) {
				endC++;
				sBuffer.append(c);
			} else if (c == '"') {
				Eflag = !Eflag;
				sBuffer.append(c);
			} else {
				sBuffer.append(c);
			}
		}

		return splitResult;
	}

	public static void main(String args[]) {
		try {
			String s1 = "{checkId:\"2014-03-10 13:51:52\",desktopType:\"game\",desktopUrl:null,desktopVersion:0,disuseEndTime:null,logOnServers:s0,name:\"1439814\",resultCode:0,serverId:\"8\",sex:1,type:0,userId:1439814}";
			LoginByEmailInfo beanInfo = initBeanInfo(LoginByEmailInfo.class, s1);
			System.out.println(beanInfo);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
