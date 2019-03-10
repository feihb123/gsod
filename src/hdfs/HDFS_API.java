package hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 上传文件至HDFS
 * @author 化十
 *
 */
public class HDFS_API {
	static Configuration conf=null;
	static FileSystem fs=null;
	static {
		 try {
			 //获取配置信息
			 conf=new Configuration();
			 //指定副本数量
			 conf.set("dfs.replication", "1");
			 //获取HDFS文件系统（hadoop中有多个文件系统）
			 URI uri=new URI("hdfs://106.14.12.245:9000");
			 fs=FileSystem.get(uri,conf,"root");
			 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


	
	public static void main(String[] args) throws Exception {
		testPut();
	}
	
	
	
	/*
	 * 文件上传：本地文件->HDFS
	 */
	public static void testPut() throws Exception{
		
		//创建输入流
		InputStream in =new FileInputStream(new File("G:\\gsod.txt"));
		//创建输出流  --hdfs路径
		OutputStream out=fs.create(new Path("/gsod/gsod.txt"));
		//通过IOUtils工具类来实现输入流和输出流的复制
		IOUtils.copy(in, out);
		
		in.close();
		out.close();
		fs.close();
	}

	
	
}
