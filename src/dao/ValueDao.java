package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pojo.ValueInfo;
import util.MySQLUtils;

public class ValueDao {

		private Connection conn=MySQLUtils.getConn();
		@Test
		public List<ValueInfo> getValue(String stn,String wban) {
			//创建保存站点信息的集合
			List<ValueInfo> list=new ArrayList<>();
			
			String sql = "select * from value where stn=? and wban=? ";
			//创建传输对象
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, stn);
				ps.setString(2, wban);
				rs = ps.executeQuery();
				while(rs.next()){
					ValueInfo v=new ValueInfo();
					v.setStn(rs.getString("stn"));
					v.setWban(rs.getString("wban"));
					//v.setYearmoda(rs.getDate("yearmoda"));
					v.setYearmoda(rs.getString("yearmoda"));
					v.setTemp(rs.getDouble("temp"));
					v.setDewp(rs.getDouble("dewp"));
					v.setSlp(rs.getDouble("slp"));
					v.setStp(rs.getDouble("stp"));
					v.setVisib(rs.getDouble("visib"));
					v.setWdsp(rs.getDouble("dewp"));
					v.setMxspd(rs.getDouble("mxspd"));
					v.setGust(rs.getDouble("gust"));
					v.setMax(rs.getDouble("max"));
					v.setMin(rs.getDouble("min"));
					v.setPrcp(rs.getDouble("prcp"));
					v.setSndp(rs.getDouble("sndp"));
					v.setFrshtt(rs.getString("frshtt"));
					
					list.add(v);	

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
