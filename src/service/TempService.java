package service;
/**
 * 将从数据库中拿到的数据格式转变为前台所需的数据格式
 * @author 化十
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import pojo.Temp;
import pojo.ValueInfo;
import util.HBaseUtils;

public class TempService {
	
	//预先读入内存
	//static List<ValueInfo> list;
	 static Map<String, List<ValueInfo>> map = null;
	
	public void getMap(String stn, String wban) {
		
		if(map == null || !map.containsKey(stn+wban)) {
			String rowKey=stn+wban;
			List<ValueInfo> list =HBaseUtils.get(rowKey);
			if(map == null) {
				map = new HashMap<String, List<ValueInfo>>();
			}
			map.put(stn+wban, list);
		}
	}	
	public List<Temp> getTemp(String stn,String wban){
		
		getMap(stn, wban);
		List<ValueInfo> list = map.get(stn+wban);	
		List<Temp> tempList=new ArrayList<>();
		for(ValueInfo info:list){
			Temp temp=new Temp();
			temp.setStn(info.getStn());
			temp.setWban(info.getWban());
			temp.setYearmoda(info.getYearmoda().toString());
			temp.setTemp(info.getTemp());
			temp.setDewp(info.getDewp());
			temp.setMax(info.getMax());
			temp.setMin(info.getMin());
			tempList.add(temp);
		}
		
	
		return tempList;

	}
	

}
