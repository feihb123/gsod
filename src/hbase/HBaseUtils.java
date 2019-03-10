package hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;

import util.TableUtils;

public class HBaseUtils {
    static Configuration conf = null;
    static Connection conn = null;
    static TableName tbName = null;
  //用来创建、删除、修改数据表
    static Admin admin = null;
    static Table table = null;
    static {
    	try {
    		conf = HBaseConfiguration.create();
    		conf.set("dfs.replication", "1");
    		conf.set("hbase.zookeeper.quorum", "hadoop");
			conn = ConnectionFactory.createConnection(conf);
			tbName = TableName.valueOf(TableUtils.tableName);
			admin = conn.getAdmin();
			table = conn.getTable(tbName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
