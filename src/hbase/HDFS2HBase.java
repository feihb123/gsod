package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;
/**
 * HDFS连接HBASE
 */
public class HDFS2HBase {
	public static void main(String[] args) throws Exception {
		String path = "hdfs://hadoop:9000/gsod_hbase/";
		
        Configuration conf = HBaseUtils.conf;
        Connection connection = HBaseUtils.conn;
        TableName tbName = HBaseUtils.tbName;
        Admin admin = HBaseUtils.admin;
        Table table = HBaseUtils.table;
        LoadIncrementalHFiles load = new LoadIncrementalHFiles(conf);
        load.doBulkLoad(new Path(path), admin,table,connection.getRegionLocator(tbName));		
	}
}
