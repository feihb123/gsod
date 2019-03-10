package hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import util.TableUtils;

/**
 * 将HDFS中gsod.txt文档中的数据转变为Hbase中的HFile文件
 * 
 * 	ImmutableBytesWritable  可用作键或值的字节序列。创建该类实例的时候，不会复制底层的字节数组，而是引用(节省空间)
 * 		 
 */
public class HDFS2HFileMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put>{
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, ImmutableBytesWritable, Put>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] strs = line.split("/");
		//指定行键
		String row = strs[0] + strs[1] + strs[2];
		//String row = new StringBuffer().append(strs[0]).append(strs[1]).append(strs[2]).toString();
		
		//创建字节数组  ImmutableBytesWritable 对象,当做行键
		//就是一个byte数组
		//rowKey = row.getBytes();
		ImmutableBytesWritable rowKey = new ImmutableBytesWritable(row.getBytes());
		
		//创建Put对象，用于封装每条数据到HBase中
		Put put = new Put(row.getBytes());
		
		//strs的前三个数当做了行键，其余数据从下表3开始
		for(int i=3; i<strs.length; i++) {
			//将strs中的数据封装到Put实例中
			put.addColumn(TableUtils.family, TableUtils.cols[i-3], 
					Bytes.toBytes(strs[i]));
		}
		
		//put.addColumn(TableUtils.familys, TableUtils.GSOD_TEMP, strs[3].getBytes());
		
		
		//将行键当做key，put对象当做value输出
		context.write(rowKey, put);
	}
}
