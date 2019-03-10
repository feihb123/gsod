package service;

import java.util.ArrayList;
import java.util.List;

import pojo.Rain;
import pojo.ValueInfo;

public class RainService {
	public List<Rain> getRain(String rowKey){
		List<ValueInfo> list=TempService.map.get(rowKey);
		List<Rain> rains=new ArrayList<>();
		for(ValueInfo value:list){
			Rain rain=new Rain();
			rain.setStn(value.getStn());
			rain.setWban(value.getWban());
			rain.setYearmoda(value.getYearmoda());
			rain.setPrcp(value.getPrcp());
			rains.add(rain);
			
		}

		return rains;
	}
}
