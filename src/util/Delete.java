package util;

import java.util.List;

public class Delete {

	public static void main(String[] args) {
		List<String> list=MySQLUtils.getStation();
		for(String value:list){
			HBaseUtils.deleteStation(value);
		}

	}

}
