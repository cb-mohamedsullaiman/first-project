package week2.day1_2.mapwords;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class MapWordsWithLength extends MapWords{
        @Override
	public String getKey(String word){
		return String.valueOf(word.length());
	}
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		Map<String,ArrayList<String>> map=new HashMap<>();
		Integer choice=1;
		MapWordsWithLength mapObject=new MapWordsWithLength();
		do{
			switch(choice){
				case 1:
					System.out.println("Enter an array of words (sentences) without pressing Enter key");
					String[] words=(scanner.nextLine()).split("\\s");
					for(String word : words){
						if(word.length()>0){
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