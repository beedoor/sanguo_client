/**
 * =================================================================
 * 版权所有 2011-2012 泰海网络支付服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；
 * 不允许对程序代码以任何形式任何目的的再发布
 * =================================================================
 */
package com.game.sanguo.base.domain;

import java.io.Serializable;

/**
 * 类说明：<br>
 * 创建一个数组对，用于保存成对信息
 * 
 * <p>
 * 详细描述：<br>
 * 
 * 
 * </p>
 * 修改历史：<pre>
 * 版本                 修改时间                 修改人                 修改内容
 * ---------------------------------------------------------------------
 *
 * </pre>
 * @author 349501 魏帅超
 * @createDate 2012-10-25
 * @version 1.0
 */
public class Pair<T,O> implements Serializable
{
	/**
	* 字段说明:
	*/
	private static final long serialVersionUID = 3978726926942443054L;
	public T first;
	public O second;
	public Pair(T first, O second)
	{
		super();
		this.first = first;
		this.second = second;
	}
	
	public static <T,O> Pair<T,O> makePair(T t,O o)
	{
		return new Pair<T,O>(t,o);
	}

	/**
	 *功能说明：获取first
	 */
	public T getFirst()
	{
		return first;
	}

	/**
	 *功能说明：设置first 
	 *@param first 
	 */
	public void setFirst(T first)
	{
		this.first = first;
	}

	/**
	 *功能说明：获取second
	 */
	public O getSecond()
	{
		return second;
	}

	/**
	 *功能说明：设置second 
	 *@param second 
	 */
	public void setSecond(O second)
	{
		this.second = second;
	}

	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}
	
}
