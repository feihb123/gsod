package service;
/**
 * 将从数据库中得到的数据转换为前台要的数据格式
 * @author feihb
 *
 */


import java.util.ArrayList;
import java.util.List;


import pojo.Station;
import pojo.StationInfo;
import util.HBaseUtils;

public class StationService {

	/*
	 * 获取从数据库中得到的数据，转变格式
	 */
	
	static List<Station> stationList=null;
	public List<Station> getStation(){
		if(stationList==null){
			List<StationInfo> list = HBaseUtils.getStation();
			stationList=new ArrayList<>(); 
			for(StationInfo info:list){
				Station s=new Station(); 
				s.setName(info.getStn()+info.getWban());
				String[] value={info.getLon()+"",info.getLat()+""};
				s.setValue(value);
				stationList.add(s);
			}
		}
		return stationList;
		
	}


}
