package filesjava;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class DirectoryScanByExtension{
	public static void addToMap(Map<String,Integer> map,String extension){
		if(map.containsKey(extension)){
			Integer numberOfFiles =map.get(extension);
			map.put(extension,numberOfFiles+1);
		}
		else{
			map.put(extension,1);
		}
	}
	public static void getExtension(Map<String,Integer> map, Path path)throws IOException{
		Stream<Path>paths=Files.list(path);
		Iterator iterator=paths.iterator();
		while(iterator.hasNext()){
			path=(Path)iterator.next();
			if(Files.isDirectory(path)){
				getExtension(map,path);
			}
			else if(Files.isRegularFile(path)&&path.toString().contains(".")){
				int extensionIndex=path.toString().lastIndexOf('.');
				// if(path.toString().substring(extensionIndex+1).startsWith("git")){
				// 	continue;
				// }
				String extension=path.toString().substring(extensionIndex+1);
				addToMap(map,extension);
			}
		}
	}
	public static void main(String...args)throws IOException{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the directory you want to explore");
		String directory=scanner.nextLine();
		Map<String,Integer> map=new HashMap<String,Integer>();
		Path path=Paths.get("/Users/cb-mohamedsullaiman/"+directory+"/");
		getExtension(map,path);
		map.forEach((k,v)->System.out.println(k+"\t\t"+v));
	}
}