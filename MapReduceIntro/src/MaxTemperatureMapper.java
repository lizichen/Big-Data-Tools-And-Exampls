
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	private static final int MISSING = 9999;
	
	
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException{
		
		String line = value.toString();
		String year = line.substring(15, 19);
		int airTemperature;
		if(line.charAt(44) == '+') { // parseInt doesn't like leading plus signs
			airTemperature = Integer.parseInt(line.substring(45, 49));
		} else {
			airTemperature = Integer.parseInt(line.substring(44, 49));
		}
		//String quality = line.substring(92, 93);
		if(airTemperature != MISSING) {
			context.write(new Text(year), new IntWritable(airTemperature)) ;
		}
	}
}
/*
004301265099999 1949032418004+ 54321+0500001N9+ 00781+99999999999+54321+
                1            2                4 4  4    
                5			 8				  4 5  8
*/