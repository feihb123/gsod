package service;

import java.util.ArrayList;
import java.util.List;

import pojo.Air;
import pojo.ValueInfo;

public class AirService {
	public List<Air> getAir(String rowKey){
		List<ValueInfo> list=TempService.map.get(rowKey);
		List<Air> airs=new ArrayList<>();
		for(ValueInfo value:list){

			Air air=new Air();
			air.setStn(value.getStn());
			air.setWban(value.getWban());
			air.setYearmoda(value.getYearmoda());
			air.setSlp(value.getSlp());
			air.setStp(value.getStp());
			airs.add(air);
		}

		return airs;
	}
}
