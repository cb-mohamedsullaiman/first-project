package collectionsjava;
import java.util.Scanner;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class MapWordsWithPrefix extends MapWords{
	public String getKey(String word){
		return word.substring(0,3);
	}
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		Map<String,ArrayList<String>> map=new TreeMap<String,ArrayList<String>>();
		Integer choice=1;
		MapWordsWithPrefix mapObject=new MapWordsWithPrefix();
		do{
			switch(choice){
				case 1:
					System.out.println("Enter an array of words (sentences) without pressing Enter key");
					String[] words=(scanner.nextLine()).split("\\s");
					for(String word : words){
						if(word.length()>=3){
							map=mapObject.addWordsToMap(word,mapObject.getKey(word),map);	
						}
					}
					mapObject.printMapping(map);
					map.clear();
					break;
				case 2:
					break;
				default:
					System.out.println("Invalid choice");
			}
			System.out.println("1.Create a new map\n2.Exit");
			choice=scanner.nextInt();
			scanner.nextLine();
		}while(choice!=2);
	}
}