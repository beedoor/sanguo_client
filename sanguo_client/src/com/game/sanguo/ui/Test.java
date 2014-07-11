package com.game.sanguo.ui;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import com.game.sanguo.base.domain.*;
import com.game.sanguo.base.task.GameTask;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.ResourceConfig;
import com.game.sanguo.base.util.UserConfig;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    static UserBean userBean;
    static UserConfig userConfig;
    static ResourceConfig resourceConfig;
    static ItemConfig itemConfig;

    public static void main(String[] args) {
//        sss1();
//        sss2();
        System.out.println("asdf13${now}".replaceAll("\\$\\{now\\}","aa"));
    }

    private static void sss2() {
        try {
            FileInputStream fos = new FileInputStream("/home/beedoor/t2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fos, "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//              if(line.indexOf("连弩") != -1 || line.indexOf("火牛") != -1 ||line.indexOf("天地") != -1 ||line.indexOf("鬼哭") != -1 ||line.indexOf("天地") != -1 ||line.indexOf("伏兵") != -1 ||line.indexOf("三圣") != -1 ||line.indexOf("五岳") != -1)

                if (new String(line.getBytes(),"utf-8").indexOf(new String("连弩".getBytes("utf-8"),"utf-8")) >= 0) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sss1() {
        try {
            userConfig = new UserConfig();
            resourceConfig = new ResourceConfig();
            itemConfig = new ItemConfig();

            userConfig.loadUserConfig();
            resourceConfig.loadResourceConfig();

            itemConfig.loadUserConfig();

            userBean = userConfig.getUserConfig(null);

            userBean.setItemConfig(itemConfig);

            FileInputStream fis = new FileInputStream(new File("/home/beedoor/myWork/bd_debug.logtmp"));
            LoginGameInfo los = decoeLoginGameInfo(fis);
            for (PlayerHerosInfo pi : los.getPlayerHerosInfoList()) {
                List<PlayerItemsInfo> cvs = los.getPlayerItemsInfoList();
                String ss = "";
                PlayerItemsInfo c1 = null;
                PlayerItemsInfo c2 = null;
                PlayerItemsInfo c3 = null;
                PlayerItemsInfo c4 = null;
                for (PlayerItemsInfo cv : cvs) {
                    if (cv.getHeroUseId().equals(pi.getId())) {
                        if (cv.getHeroPos() == 1) {
                            c1 = cv;
                        }
                        if (cv.getHeroPos() == 2) {
                            c2 = cv;
                        }
                        if (cv.getHeroPos() == 3) {
                            c3 = cv;
                        }
                        if (cv.getHeroPos() == 4) {
                            c4 = cv;
                        }
                    }
                }
                ss += String.format("%s:%s#", c1 == null ? "" : itemConfig.decodeEquipment(c1.getSourceId()).getName(), c1 == null ? "" : c1.getStrengthenLevel());
                ss += String.format("%s:%s#", c2 == null ? "" : itemConfig.decodeEquipment(c2.getSourceId()).getName(), c2 == null ? "" : c2.getStrengthenLevel());
                ss += String.format("%s:%s#", c3 == null ? "" : itemConfig.decodeEquipment(c3.getSourceId()).getName(), c3 == null ? "" : c3.getStrengthenLevel());
                ss += String.format("%s:%s#", c4 == null ? "" : itemConfig.decodeEquipment(c4.getSourceId()).getName(), c4 == null ? "" : c4.getStrengthenLevel());
                System.out.println(pi.getSourceId() + "\t" + pi.getLevel() + "\t" + itemConfig.decodeHero(pi.getSourceId()).getName() + "\t" + pi.getMaxHP() + "\t" + pi.getMaxMP() + "\t" + pi.getSoldierType() + "" +
                        "\t" + pi.getSoldierNum() + "\t" + ss);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static LoginGameInfo decoeLoginGameInfo(InputStream inputStream) {
        LoginGameInfo loginGameInfo = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    inputStream));
            List<PlayerCitysInfo> playerCitysInfoList = new ArrayList();
            List<PlayerHerosInfo> playerHerosInfoList = new ArrayList();
            List<PlayerItemsInfo> playerItemsInfoList = new ArrayList();
            Pattern itemsPattern = Pattern
                    .compile("(s[\\d]{1,})\\[[\\d]{1,}\\]=");
            Pattern itemsContentPattern = Pattern.compile("(s[\\d]{1,})[.]");
            Map<String, List<String>> itemChildMap = new HashMap();
            Map<String, String> itemContentMap = new HashMap();
            for (String s1 = null; (s1 = br.readLine()) != null; ) {
                if (s1.startsWith("dwr")) {
                    loginGameInfo = (LoginGameInfo) GameTask.initBeanInfo(
                            LoginGameInfo.class, s1);
                } else {
                    Matcher m = itemsContentPattern.matcher(s1);
                    if (m.find()) {
                        String itemName = m.group(1);
                        String contentStr = m.replaceAll("");
                        itemContentMap.put(itemName, contentStr);
                    }
                    m = itemsPattern.matcher(s1);
                    if (m.find()) {
                        String keyName = m.group(1);
                        List<String> sList = new ArrayList();
                        itemChildMap.put(keyName, sList);
                        String tempStr = m.replaceAll("");
                        String[] strArray = tempStr.split(";");
                        String[] as;
                        int j = (as = strArray).length;
                        for (int i = 0; i < j; i++) {
                            String s = as[i];
                            sList.add(s);
                        }
                    }
                }
            }
            if (itemChildMap.containsKey(loginGameInfo.getPlayerCitys())) {
                Iterator<String> iterator = ((List) itemChildMap
                        .get(loginGameInfo.getPlayerCitys())).iterator();
                String content;
                for (; iterator.hasNext(); playerCitysInfoList
                        .add((PlayerCitysInfo) GameTask.initBeanInfo(
                                PlayerCitysInfo.class, content, ';', '='))) {
                    String itemName = (String) iterator.next();
                    content = (String) itemContentMap.get(itemName);
                }
            }
            if (itemChildMap.containsKey(loginGameInfo.getPlayerHeros())) {
                Iterator<String> iterator1 = ((List) itemChildMap
                        .get(loginGameInfo.getPlayerHeros())).iterator();
                String content;
                for (; iterator1.hasNext(); playerHerosInfoList
                        .add((PlayerHerosInfo) GameTask.initBeanInfo(
                                PlayerHerosInfo.class, content, ';', '='))) {
                    String itemName = (String) iterator1.next();
                    content = (String) itemContentMap.get(itemName);
                }
            }
            if (itemChildMap.containsKey(loginGameInfo.getPlayerItems())) {
                Iterator<String> iterator2 = ((List) itemChildMap
                        .get(loginGameInfo.getPlayerItems())).iterator();
                String content;
                for (; iterator2.hasNext(); playerItemsInfoList
                        .add((PlayerItemsInfo) GameTask.initBeanInfo(
                                PlayerItemsInfo.class, content, ';', '='))) {
                    String itemName = (String) iterator2.next();
                    content = (String) itemContentMap.get(itemName);
                }
            }
            if (loginGameInfo != null) {
                loginGameInfo.setPlayerCitysInfoList(playerCitysInfoList);
                loginGameInfo.setPlayerHerosInfoList(playerHerosInfoList);
                loginGameInfo.setPlayerItemsInfoList(playerItemsInfoList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginGameInfo;
    }

    private static void addOtherInfo(List<PlayerCitysInfo> playerCitysInfoList,
                                     List<PlayerHerosInfo> playerHerosInfoList,
                                     List<PlayerItemsInfo> playerItemsInfoList) {
        for (PlayerCitysInfo playerCitysInfo : playerCitysInfoList) {
            CityItem cityItem = userBean.getItemConfig().decodeCity(
                    playerCitysInfo.getSourceId());
            if (cityItem != null) {
                playerCitysInfo.setCityName(cityItem.getCity_name());
            }
        }
        for (PlayerHerosInfo playerHerosInfo : playerHerosInfoList) {
            userBean.putHeroIdToSrcId(playerHerosInfo.getId(),
                    playerHerosInfo.getSourceId());
            CityItem cityItem = userBean.getItemConfig().decodeCity(
                    playerHerosInfo.getCityId());
            HeroItem heroItem = userBean.getItemConfig().decodeHero(
                    playerHerosInfo.getSourceId());
            if (cityItem != null) {
                playerHerosInfo.setCityName(cityItem.getCity_name());
            }
            if (heroItem != null) {
                playerHerosInfo.setHeroName(heroItem.getName());
            }
        }
        for (PlayerItemsInfo playerItemsInfo : playerItemsInfoList) {
            userBean.putItemIdToSrcId(playerItemsInfo.getId(),
                    playerItemsInfo.getSourceId());
            EquipmentItem equipmentItem = userBean.getItemConfig()
                    .decodeEquipment(playerItemsInfo.getSourceId());
            HeroItem heroItem = userBean.getItemConfig().decodeHero(
                    userBean.decodeHeroSrcIdByUseId(playerItemsInfo
                            .getHeroUseId())
            );
            if (equipmentItem != null) {
                playerItemsInfo.setItemName(equipmentItem.getName());
            } else {
                PackItem packItem = userBean.getItemConfig().decodePack(
                        playerItemsInfo.getSourceId());
                if (packItem != null) {
                    playerItemsInfo.setItemName(packItem.getProps_name());
                }
            }
            if (heroItem != null) {
                playerItemsInfo.setHeroName(heroItem.getName());
            }
        }
    }

}
