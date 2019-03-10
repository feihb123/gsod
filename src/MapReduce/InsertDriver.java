package MapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class InsertDriver {
	public static void main(String[] args) throws Exception {
		//获取Hadoop配置信息对象
		Configuration conf=new Configuration();
		//创建任务对象
		Job job=Job.getInstance(conf);
		
		//执行mapper组件的相关内容
		job.setMapperClass(InsertMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		//指定reduce组件的相关内容
		job.setReducerClass(InsertReducer.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(NullWritable.class);
		
		
		FileInputFormat.setInputPaths(job, 
				new Path("G:\\gsod.txt"));
		FileOutputFormat.setOutputPath(job, 
				new Path("G:\\testDemo"));
		
		//提交Job任务
		job.waitForCompletion(true);
	}
}
