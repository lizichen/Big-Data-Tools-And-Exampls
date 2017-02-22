import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class PageRankMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	protected static final String PR_VALUE_SPECIAL_TOKEN = "#";
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		
		String inputLine = value.toString();
		
		String[] inputArr = inputLine.split(" ");
		int length = inputArr.length;
		Float PR = 0.0f;
		String outlinks = "";
		
		String from = inputArr[0];
		for(int i = 1 ; i < length-1 ; i++){														// A C F 0.166667 - loop from C to F
			String to = inputArr[i];
			PR = Float.parseFloat(inputArr[length-1]) / (length-2); 								// number of outlink destinations
			context.write(new Text(to), new Text(from + PR_VALUE_SPECIAL_TOKEN + PR.toString()));	// produce key-value map pair: {C, A#0.833335}
			//outlinks.concat(to+" ");
			outlinks += (to+" ");
		}
		context.write(new Text(from), new Text(outlinks.trim()));									// produce key-value map pair: {A, C F}
		// logging
		//System.out.println("key="+from+" value="+outlinks);
		
		
	}
}
