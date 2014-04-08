package com.game.sanguo.base.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.sanguo.base.domain.UserBean;

public class XmlUtil {
	protected static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	private static Document document;
	static DocumentBuilderFactory dbf;
	static DocumentBuilder db;
	static {
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> List<T> loadConfig(Class<T> classType, String fileName, String pattern) {
		try {
			List<T> resultList = parserXml(classType, fileName, pattern);
			return resultList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static Long parseLong(String s) {
		try {
			return Long.parseLong(s);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}

	private static Integer parseInteger(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private static Boolean parseBoolean(String s) {
		try {
			return Boolean.valueOf(s);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private static <T> void setBeanData(Class<T> classType, T beanEntity, String nodeName, String nodeContent) {
		try {
			Field m = getBeanField(classType, nodeName);
			if (m != null) {
				if (!m.isAccessible()) {
					m.setAccessible(true);
				}
				if (m.getType() == Long.class) {
					m.setLong(classType, parseLong(nodeContent));
				}
				if (m.getType() == Boolean.class) {
					m.setBoolean(classType, parseBoolean(nodeContent));
				}
				if (m.getType() == Integer.class) {
					m.setInt(classType, parseInteger(nodeContent));
				} else {
					m.set(classType, nodeContent);
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static <T> Field getBeanField(Class<T> classType, String nodeName) {
		try {
			Field m = classType.getDeclaredField(nodeName);
			return m;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static <T> T createBeanEntity(Class<T> classType, Node nodeInfo) {
		T beanEntity = null;
		try {
			// 是元素类性，进行递归
			if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
				System.out.print(nodeInfo);
			}// 是文本类性，打印出来
			else if (nodeInfo.getNodeType() == Node.TEXT_NODE) {
				System.out.print(nodeInfo.getNodeValue());
			}
			beanEntity = classType.newInstance();
			NamedNodeMap attributeMap = nodeInfo.getAttributes();
			if (attributeMap == null) {
				return null;
			}
			// for (int i = 0; i < attributeMap.getLength(); i++) {
			// Node childNode = attributeMap.item(i);
			// String nodeName = childNode.getNodeName();
			// String nodeContent = childNode.getTextContent();
			// setBeanData(classType, beanEntity, nodeName, nodeContent);
			// }
			// NodeList childNodes = nodeInfo.getChildNodes();
			// for (int i = 0; i < childNodes.getLength(); i++) {
			// Node childNode = childNodes.item(i);
			// String nodeName = childNode.getNodeName();
			// String nodeContent = childNode.getTextContent();
			// setBeanData(classType, beanEntity, nodeName, nodeContent);
			// }
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beanEntity;
	}

	private static <T> List<T> decodeChild(Class<T> classType, NodeList childNodes, List<String> patternList, int patternIdx) {
		List<T> decodeResult = new ArrayList<T>();
		String paternSubContent = patternList.get(patternIdx);
		if (patternList.size() < (patternIdx + 1)) {
			return null;
		} else if (patternList.size() == (patternIdx + 1)) {
			// 开始解析内容,此处只有attribute 以及子内容
			try {
				for (int i = 0; i < childNodes.getLength(); i++) {
					Node nodeInfo = childNodes.item(i);
					T beanInfo = createBeanEntity(classType, nodeInfo);
					if (null != beanInfo) {
						decodeResult.add(beanInfo);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node nodeInfo = childNodes.item(i);
				if (nodeInfo.getNodeName().equals(paternSubContent)) {
					List<T> subDecodeResult = decodeChild(classType, nodeInfo.getChildNodes(), patternList, patternIdx + 1);
					if (subDecodeResult != null && !subDecodeResult.isEmpty()) {
						decodeResult.addAll(subDecodeResult);
					}
				}
			}
		}
		return decodeResult;
	}

	public static <T> List<T> parserXml(Class<T> classType, String fileName, String pattern) {
		try {
			Document document = db.parse(fileName);
			NodeList childNodes = document.getChildNodes();
			String[] patternArray = pattern.split("/");
			List<String> patternList = Arrays.asList(patternArray);
			List<T> resultList = decodeChild(classType, childNodes, patternList, 0);
			return resultList;
		} catch (Exception e) {
			logger.error("解析xml文件异常", e);
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			loadConfig(UserBean.class, "./config/userconfig.xml", "configuration/users/user");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
