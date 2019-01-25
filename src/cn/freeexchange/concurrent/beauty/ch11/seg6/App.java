package cn.freeexchange.concurrent.beauty.ch11.seg6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class App {
	
	
	static Map<Integer,StrategyService> serviceMap = 
			new HashMap<Integer,StrategyService>();
	
	
	static {
		serviceMap.put(111, new StrategyOneService());
		serviceMap.put(222, new StrategyTwoService());
	}
	
	public static void main(String[] args) {
		Map<Integer,List<String>> appKeyMap = new HashMap<Integer,List<String>>();
		
		//创建appkey=111的设备列表
		List<String> oneList = new ArrayList<String>();
		oneList.add("device_id1");
		appKeyMap.put(111, oneList);
		
		
		List<String> twoList = new ArrayList<>();
		twoList.add("device_id2");
		appKeyMap.put(222, twoList);
		
		List<Msg> msgList = new ArrayList<>();
		Msg msg = new Msg();
		msg.setDataId("abc");
		msg.setBody("hello");
		msgList.add(msg);
		
		Iterator<Integer> appKeyItr = appKeyMap.keySet().iterator();
		Map<Integer,List<Msg>> appKeyMsgMap = new HashMap<Integer,List<Msg>>();
		
		while(appKeyItr.hasNext()) {
			List<Msg> tempList = new ArrayList<>();
			Iterator<Msg> itrMsg = msgList.iterator();
			while(itrMsg.hasNext()) {
				Msg tmpMsg = null;
				try {
					tmpMsg = (Msg) BeanUtils.cloneBean(itrMsg.next());
				} catch(Exception e) {
					e.printStackTrace();
				}
				if(null != tmpMsg) {
					tempList.add(tmpMsg);
				}
			}
			appKeyMsgMap.put(appKeyItr.next(), tempList);
		}
		
		
		
		appKeyItr = appKeyMap.keySet().iterator();
		while(appKeyItr.hasNext()) {
			Integer appKey = appKeyItr.next();
			StrategyService strategyService = serviceMap.get(appKey);
			if(null != strategyService) {
				strategyService.sendMsg(appKeyMsgMap.get(appKey), appKeyMap.get(appKey));
			} else {
				System.out.println(String.format("appKey:%s,is not registerd serviceKey",
						appKey));
			}
		}
		
	}
	
	
	

}
