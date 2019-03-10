package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接工具类
 * 封装了数据库驱动加载
 * 连接对象创建
 * 资源关闭
 * @author feihb
 *
 */
public class MySQLUtils {
	private static Connection conn=null;
	
	static{//在静态代码块中加载驱动、创建连接对象
		//所有对象共享该静态属性
		//静态属性在类加载的时候被创建
		//当该类从内存中移除的时候，静态属性才死亡
		//静态的调用：类名调用、对象调用
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://106.14.12.245:3306/gsod";
			String user="feihb123";
			String password="h2486543..";
			conn=DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		return conn;
	}
	/*
	 * 关闭数据库资源（conn除外）
	 */
	public static void close(ResultSet rs,PreparedStatement ps){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				rs=null;
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ps=null;
			}
		}
		
	}
	
	public static void close(PreparedStatement ps){
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ps=null;
			}
		}
		
	}
	
	public static List<String> getStation(){
		//创建保存站点信息对象的集合
		List<String> list = new ArrayList<String>();
		String sql = "select stn,wban from station_info";
		//创建传输对象
		PreparedStatement ps = null;
		//执行sql，返回结果集对象
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//遍历结果集对象
		try {
			while (rs.next()) {//通常将数据库中得到的数据封装到对象中
				//将数据封装到对象中
				String s = new String();
				s=rs.getString("stn")+"-"+rs.getString("wban");
				//将得到的对象保存在集合中
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

	public static void delete(String str){
		String strs[] =str.split("-");
		String sql="delete  from station_info where stn=? and wban=?";
		String stn=strs[0];
		String wban=strs[1];
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, stn);
			ps.setString(2, wban);
			ps.execute();
			System.out.println("删除"+str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
