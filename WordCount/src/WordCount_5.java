import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WordCount_5 {

	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		String filename = "/home/cloudera/workspace/WordCount/src/input.txt";
		
		//construct key words hash table
		Hashtable<String, Integer> keywordsTable = new Hashtable<String, Integer>();
		keywordsTable.put("hackathon", 0);
		keywordsTable.put("Dec", 0);
		keywordsTable.put("Chicago", 0);
		keywordsTable.put("Java", 0);
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String line;
			while( (line = br.readLine()) != null ){
				line = line.toLowerCase();
				for(String key : keywordsTable.keySet()){
					String tmp = key.toLowerCase();
					if(line.matches(".*\\b"+tmp+"\\b.*")){
						int rep = keywordsTable.get(key);
						keywordsTable.put(key, rep+1);
					}
				}
			}
		}
		
		 Iterator it = keywordsTable.entrySet().iterator();
		 while(it.hasNext()){
		     Map.Entry pair = (Map.Entry)it.next();
		     System.out.println(pair.getKey() + ":" + pair.getValue());
		     it.remove();
		 }
	}
}
