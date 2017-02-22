import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;


public class PageRankReducer extends Reducer<Text, Text, Text, Text>{

	protected static final String PR_VALUE_SPECIAL_TOKEN = "#";
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
		
		Float PR = 0.0f;
		String outlinks = "";
		
		for(Text oneInput: values){
			if( oneInput.toString().contains(PR_VALUE_SPECIAL_TOKEN)){
				PR = PR + Float.parseFloat(oneInput.toString().split(PR_VALUE_SPECIAL_TOKEN)[1]);
			}else{
				outlinks = oneInput.toString();
			}
		}
		context.write(key, new Text(outlinks+" "+PR.toString()));
		
	}
}
