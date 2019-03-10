package MapReduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class InsertMapper extends Mapper<LongWritable, Text, Text, 	Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		System.out.println(line);
		String[] strs=line.split("/");

		context.write(new Text(strs[0]+"-"+strs[1]), new Text(line));
	}
	
}
