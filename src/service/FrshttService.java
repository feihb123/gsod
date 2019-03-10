package service;

import java.util.List;

import pojo.ValueInfo;

public class FrshttService {
	public int[] getFrshtt(String keyRow){
		int[] count={0,0,0,0,0,0};
		List<ValueInfo> list=TempService.map.get(keyRow);
		for(ValueInfo value:list){
			String str=value.getFrshtt();
			char[] num=str.toCharArray();
			for(int i=0;i<6;i++){
				if(num[i]=='1'){
					count[i]++;
				}
			}
		}
		
		return count;
	}
}
