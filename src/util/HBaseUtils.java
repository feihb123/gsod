package util;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import pojo.StationInfo;
import pojo.ValueInfo;
/**
 * 向HBase数据库指定表插入、获取数据的工具类
 * @author 化十
 *
 */
public class HBaseUtils {
	static Configuration conf=null;
	static Connection conn=null;
	//获取表名描述其  <-- 表名
	TableName tbName=null;
	//获取表连接（实现数据增删改查） <--表名描述器
	static Table table=null;
	static Table StationTable=null;
    static Admin admin = null;
	static{
		try {
			//获取HBase配置信息
			conf=HBaseConfiguration.create();
			conf.set("dfs.replication", "1");
			//指定zookeeper所在节点
			//hadoop若使用ip地址可能会无法访问获得数据
			conf.set("hbase.zookeeper.quorum", "hadoop");
			//获取连接对象
			conn=ConnectionFactory.createConnection(conf);
			
			//获取表名描述其  <-- 表名
			TableName tbName=TableName.valueOf(TableUtils.tableName);
			TableName StationName=TableName.valueOf(TableUtils.stationName);
			
			admin = conn.getAdmin();
			//获取表连接（实现数据增删改查） <--表名描述器
			table=conn.getTable(tbName);
			StationTable=conn.getTable(StationName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 从HBase数据库中指定表查询数据
	 */
	public static void show() throws Exception{		
		//全表扫描的方式获取数据  ==创建Scan对象
		Scan scan =new Scan();
		//查询数据，返回结果集
		ResultScanner rs=table.getScanner(scan);
		//遍历结果集，获取每条数据并输出
		//从HBase中获取得到的数据都是字节数组
		for(Result value:rs){
			//获取行键
			byte[] row=value.getRow();
			//在HBase中，提供了一个Bytes类
			//用来将从Hbase中得到的数据转变为对应Java类型
			String rowKey=Bytes.toString(row);
			System.out.println(rowKey);
				
			byte[] name=value.getValue("info".getBytes(), "name".getBytes());
			String StrName=Bytes.toString(name);
			System.out.println(StrName);
			
			
			
		}
		//关闭资源
		rs.close();
		table.close();
		conn.close();
		
	}

	/**
	 * 向HBase数据库指定表插入数据
	 * @throws Exception 
	 */
	public static void put(Put put) throws Exception{

		//表连接对象执行上传数据
		table.put(put);


	}
	/*
	 * 获取每年气象信息
	 */
	public static List<ValueInfo> get(String rowKey) {
		List<ValueInfo> list=new ArrayList<>();
		Scan scan=new Scan(Bytes.toBytes(rowKey+"201601"),
				Bytes.toBytes(rowKey+"20161232"));
		//System.out.println(get);
		ResultScanner rs = null;
		
		try {
			rs = table.getScanner(scan);
			//遍历结果集，获取每条数据并输出
			for(Result value:rs){
				//获取行键
				byte[] row=value.getRow();
				String Key=Bytes.toString(row);
				System.out.println(Key);
			/*	byte[] stn=value.getValue("info".getBytes(), "stn".getBytes());
				byte[] wban=value.getValue("info".getBytes(), "wban".getBytes());
				byte[] year=value.getValue("info".getBytes(), "year".getBytes());*/
				byte[] temp=value.getValue("data".getBytes(), "temp".getBytes());
				byte[] dewp=value.getValue("data".getBytes(), "dewp".getBytes());
				byte[] slp=value.getValue("data".getBytes(), "slp".getBytes());
				byte[] stp=value.getValue("data".getBytes(), "stp".getBytes());
				byte[] visib=value.getValue("data".getBytes(), "visib".getBytes());
				byte[] wdsp=value.getValue("data".getBytes(), "wdsp".getBytes());
				byte[] mxspd=value.getValue("data".getBytes(), "mxspd".getBytes());
				byte[] gust=value.getValue("data".getBytes(), "gust".getBytes());
				byte[] max=value.getValue("data".getBytes(), "max".getBytes());
				byte[] min=value.getValue("data".getBytes(), "min".getBytes());
				byte[] prcp=value.getValue("data".getBytes(), "prcp".getBytes());
				byte[] sndp=value.getValue("data".getBytes(), "sndp".getBytes());
				byte[] frshtt=value.getValue("data".getBytes(), "frshtt".getBytes());
				ValueInfo data=new ValueInfo();
				String stnH = Key.substring(0, 6);
				String wbanH = Key.substring(6, 11);
				String dateH = Key.substring(11);
				data.setStn(stnH);
				data.setWban(wbanH);
				data.setYearmoda(dateH);
				data.setTemp(Double.parseDouble(Bytes.toString(temp)));
				data.setDewp(Double.parseDouble(Bytes.toString(dewp)));
				data.setSlp(Double.parseDouble(Bytes.toString(slp)));
				data.setStp(Double.parseDouble(Bytes.toString(stp)));
				data.setVisib(Double.parseDouble(Bytes.toString(visib)));
				data.setWdsp(Double.parseDouble(Bytes.toString(wdsp)));
				data.setMxspd(Double.parseDouble(Bytes.toString(mxspd)));
				data.setGust(Double.parseDouble(Bytes.toString(gust)));
				data.setMax(Double.parseDouble(Bytes.toString(max)));
				data.setMin(Double.parseDouble(Bytes.toString(min)));
				data.setPrcp(Double.parseDouble(Bytes.toString(prcp)));
				data.setSndp(Double.parseDouble(Bytes.toString(sndp)));
				data.setFrshtt(Bytes.toString(frshtt));
				list.add(data);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			try {
				if(rs!=null){
					rs.close();
				}
				if(table!=null){
					table.close();
				}
				
				if(conn==null){
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return list;

	}
	/*
	 * 获取站点
	 */
	public static List<StationInfo> getStation() {
		List<StationInfo> list=new ArrayList<>();
		Scan scan=new Scan();
		//System.out.println(get);
		ResultScanner rs = null;
		
		try {
			rs = StationTable.getScanner(scan);
			//遍历结果集，获取每条数据并输出
			for(Result value:rs){
				//获取行键
				byte[] row=value.getRow();
				String Key=Bytes.toString(row);
				//System.out.println(Key);
			
				byte[] lati=value.getValue("data".getBytes(), "latitude".getBytes());
				byte[] longi=value.getValue("data".getBytes(), "longitude".getBytes());
			
				StationInfo data=new StationInfo();
				String stnH = Key.substring(0, 6);
				String wbanH = Key.substring(6);
				data.setStn(stnH);
				data.setWban(wbanH);

				data.setLat(Double.parseDouble(Bytes.toString(lati)));
				data.setLon(Double.parseDouble(Bytes.toString(longi)));

				list.add(data);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			try {
				if(rs!=null){
					rs.close();
				}
				if(table!=null){
					table.close();
				}
				
				if(conn==null){
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return list;

	}
	
	public static void deleteStation(String rowKey){
		Scan scan=new Scan(Bytes.toBytes(rowKey+"-201601"),
				Bytes.toBytes(rowKey+"-20161232"));
		ResultScanner rs = null;
		
		try {
			rs = table.getScanner(scan);
			//遍历结果集，获取每条数据并输出
			if(rs.next()==null){
				MySQLUtils.delete(rowKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			try {
				if(rs!=null){
					rs.close();
				}
				if(table!=null){
					table.close();
				}
				
				if(conn==null){
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	
	public static void main(String[] args) throws IOException {
		//get("998281-99999");
		deleteStation("283120-99999");
		
	}
}
