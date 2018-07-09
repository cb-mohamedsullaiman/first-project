package week2.day1_2.mapwords;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

abstract class WordMapper {

    private Map<String, ArrayList<String>> map = new HashMap<>();
    
    public Map<String,ArrayList<String>> getMap(){
        return map;
    }
    
    public void mapWordsAgainstKey(String word, String key) {     //to map the word against the given key
        ArrayList<String> words = new ArrayList<>();
        if (map.containsKey(key)) {
            words = map.get(key);
        }
        words.add(word);
        map.put(key, words);
    }

    public void printMapping() {                            // to print the key and value pairs
        for (Map.Entry<String, ArrayList<String>> mapEntry : map.entrySet()) {
            System.out.println(mapEntry.getKey() + "\t\tList of words\t: " + mapEntry.getValue());
            System.out.println("\n");
        }
    }

    abstract String getKey(String key);         //This will be implemented by the child classes

}
