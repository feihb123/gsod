package service;

import java.util.ArrayList;
import java.util.List;

import pojo.ValueInfo;
import pojo.Visib;


public class VisibService {
	public List<Visib> getVisib(String rowKey){
		List<ValueInfo> list = TempService.map.get(rowKey);
		List<Visib> visibs=new ArrayList<>();
		for(ValueInfo value:list){

			Visib visib=new Visib();
			visib.setStn(value.getStn());
			visib.setWban(value.getWban());
			visib.setYearmoda(value.getYearmoda());
			visib.setVisib(value.getVisib());
			visibs.add(visib);
		}

		return visibs;
	}
}
