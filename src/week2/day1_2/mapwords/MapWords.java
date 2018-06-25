package week2.day1_2.mapwords;
import java.util.Map;
import java.util.ArrayList;

abstract class MapWords{
	public MapWords(){

	}
	public void printMapping(Map<String,ArrayList<String>> map){
		for(Map.Entry<String,ArrayList<String>> mapEntry : map.entrySet()){
			System.out.println(mapEntry.getKey()+"\tList of words\t: "+mapEntry.getValue());
			System.out.println("\n");
		}
					
	}
	
	public Map<String,ArrayList<String>> addWordsToMap(String word,String key,Map<String,ArrayList<String>> map){
		ArrayList<String> words=new ArrayList<>();
		if(map.containsKey(key)){
			words=map.get(key);
		}
		words.add(word);
		map.put(key,words);
		return map;					
	}
	abstract String getKey(String key);
	
}