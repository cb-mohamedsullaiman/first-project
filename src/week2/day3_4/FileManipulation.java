package week2.day3_4;
import java.util.Map;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import org.apache.commons.io.FileUtils;

public abstract class FileManipulation{
	public void writeToFile(Map<String,String> map,File file){
		try{
			Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry mapEntry = iterator.next();
				String lineNumberWithIndices = mapEntry.getKey()+"\t\t"+mapEntry.getValue()+"\n";
				System.out.println(lineNumberWithIndices);
				FileUtils.writeStringToFile(file,lineNumberWithIndices,(String)null,true);
			}
			if(map.isEmpty()){
				System.out.println("No words are matched");
			}
		}
		catch(IOException ioException){
			System.out.println("IO exception thrown");
		}

	}
	public abstract void readFromFile(Map<String,String> map,File file);
}