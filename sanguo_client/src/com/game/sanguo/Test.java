package com.game.sanguo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pattern itemsPattern = Pattern.compile("s[\\d]{1,}\\.");
		String s1 = "s2.badge=0;s2.cityName=null;s2.citySrc=9;s2.color=0;s2.heroCount=1;s2.id=18554;s2.leagueId=0;s2.lv=0;s2.occupierHeroCount=0;s2.occupierId=0;s2.occupierName=null;s2.occupyTime=null;s2.playerId=0;s2.protectMsLeft=0;s2.typeAsInt=6;s2.unionName=null;";
		Matcher m = itemsPattern.matcher(s1);
		if(m.find())
		{
		 String itemName = m.group(1);
         String contentStr = m.replaceAll("");
         System.out.println(contentStr);
		}
		
	}

}
