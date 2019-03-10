package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import util.StationUtils;

/**
 * 将站点信息转别为HFile
 * @author 化十
 *
 */
public class HDFSHFileDriver {
	public static void main(String[] args) {
		//指定输入、输出路径
				String inPath = "G:\\station_info.txt";
				//String outPath = "hdfs://hadoop:9000/gsod_hbase/";
				String outPath = "G:\\gsod_station";
				
				//创建配置信息
				Configuration conf = null;
				//指定zookeeper节点的IP地址
				Connection conn = null;
				Table table = null;
				try {
					conf = StationUtils.conf;
					//创建和HBase数据库的连接对象
					conn = StationUtils.conn;
					//创建表名表述器
					TableName tbName = StationUtils.stationName;
					//创建和表的连接对象
					table = StationUtils.station;
					
					//创建Job任务
					Job job = Job.getInstance(conf);
					//设定mapper组件的相关内容
					job.setMapperClass(HDFSHFileMapper.class);
					job.setMapOutputKeyClass(ImmutableBytesWritable.class);
					job.setMapOutputValueClass(Put.class);
					//hadoop运行时指定
					job.setJarByClass(HDFSHFileDriver.class);
					
					//指定输出格式   也只是引用，不复制
					job.setOutputFormatClass(HFileOutputFormat2.class);
					//regionLocator用于查看HBase表的区域信息
					HFileOutputFormat2.configureIncrementalLoad(job, table, 
							conn.getRegionLocator(tbName));
					
					//指定输入、输出路径
					FileInputFormat.setInputPaths(job, new Path(inPath));
					FileOutputFormat.setOutputPath(job, new Path(outPath));
					
					job.waitForCompletion(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	}
}
