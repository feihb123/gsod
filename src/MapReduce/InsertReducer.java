package MapReduce;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import util.HBaseUtils;
import util.TableUtils;

public class InsertReducer extends Reducer<Text, Text, NullWritable, NullWritable>{
	@Override
	protected void reduce(Text key, Iterable<Text> value, Reducer<Text, Text, NullWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		for(Text values:value){
			String line=values.toString();
			String[] strs=line.split("/");
			String keyRow=strs[0]+"-"+strs[1]+"-"+strs[2];//行键
			Put put=new Put(Bytes.toBytes(keyRow));

			for(int j=0;j<16;j++){
				if(j<=2){
				put.addColumn(TableUtils.family, 
						TableUtils.cols[j], Bytes.toBytes(strs[j]));
				}else{
					put.addColumn(TableUtils.family, 
							TableUtils.cols[j], Bytes.toBytes(strs[j]));
				}

			}	
			try {
				HBaseUtils.put(put);
				System.out.println("success"+keyRow);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
}
