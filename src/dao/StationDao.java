package dao;
/**
 * 用来才从数据库中查询站点信息并封装
 * @author feihb
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import pojo.StationInfo;
import util.MySQLUtils;

public class StationDao {
	//从自定义的类中获取连接对象
	private Connection conn=MySQLUtils.getConn();
	/*
	 * 获取数据库中所有站点信息
	 */
	@Test
	public List<StationInfo> getStation() {
		//创建保存站点信息的集合
		List<StationInfo> list=new ArrayList<>();
		
		String sql = "select * from station_info ";
		//创建传输对象
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			
			ps = conn.prepareStatement(sql);
			//执行sql语句返回结果集对象
			rs = ps.executeQuery();
			while(rs.next()){//通常将从数据库中得到的数据封装到对象中
				//将数据封装到对象中去
				StationInfo s=new StationInfo();
				s.setStn(rs.getString("stn"));
				s.setWban(rs.getString("wban"));
				s.setLat(rs.getDouble("latitude"));
				s.setLon(rs.getDouble("longitude"));
				list.add(s);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//关闭资源
			MySQLUtils.close(rs, ps);
		}

		return list;
	}
}
