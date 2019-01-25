package cn.freeexchange.concurrent.beauty.ch11.seg6;

import java.util.List;

public class StrategyTwoService implements StrategyService {

	@Override
	public void sendMsg(List<Msg> msgList, List<String> deviceIdList) {
		
		for(Msg msg :msgList) {
			msg.setDataId("TwoService_"+msg.getDataId());
			System.out.println(msg.getDataId()+listToString(deviceIdList));
		}
	}
	
	
	public String listToString(List<String> deviceIdList) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for (int i =0;i <deviceIdList.size();i++) {
			String deviceId = deviceIdList.get(i);
			sb.append(deviceId);
			if(i < deviceIdList.size()-1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
