package cn.freeexchange.concurrent.beauty.ch11.seg6;

import java.util.List;

public interface StrategyService {
	
	public void sendMsg(List<Msg> msgList,List<String> deviceIdList);

}
