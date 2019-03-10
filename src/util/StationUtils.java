package util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
/**
 * 向HBase数据库station表插入、获取数据的工具类
 * @author 化十
 *
 */
public class StationUtils {
    public static Configuration conf = null;
    public static Connection conn = null;
    public static TableName stationName = null;
  //用来创建、删除、修改数据表
    static Admin admin = null;
    public static Table station = null;
    static {
    	try {
    		conf = HBaseConfiguration.create();
    		conf.set("dfs.replication", "1");
    		conf.set("hbase.zookeeper.quorum", "hadoop");
			conn = ConnectionFactory.createConnection(conf);
			stationName = TableName.valueOf(TableUtils.stationName);
			admin = conn.getAdmin();
			station = conn.getTable(stationName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
