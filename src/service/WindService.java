package service;

import java.util.ArrayList;
import java.util.List;


import pojo.ValueInfo;
import pojo.Wind;


public class WindService {
	public List<Wind> getWind(String rowKey){
		List<ValueInfo> list=TempService.map.get(rowKey);
		List<Wind> winds=new ArrayList<>();
		for(ValueInfo value:list){
			Wind wind=new Wind();
			wind.setStn(value.getStn());
			wind.setWban(value.getWban());
			wind.setYearmoda(value.getYearmoda());
			wind.setWdsp(value.getWdsp());
			wind.setMxspd(value.getMxspd());
			wind.setGust(value.getGust());
			winds.add(wind);

		}
		
		
		return winds;
	}
}
