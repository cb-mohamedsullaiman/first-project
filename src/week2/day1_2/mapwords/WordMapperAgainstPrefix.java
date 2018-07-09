package week2.day1_2.mapwords;

import java.util.Scanner;

public class WordMapperAgainstPrefix extends WordMapper {

    @Override
    public String getKey(String word) {
                return word.substring(0, 3);
    }

    public void mapWordsAgainstPrefix(String[] words) {

        for (String word : words) {
            if (word.length() >= 3) {
                mapWordsAgainstKey(word, getKey(word));
            }
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Integer choice = 1;
        WordMapperAgainstPrefix wordMapper = new WordMapperAgainstPrefix();
        do {
            switch (choice) {
                case 1:
                    System.out.println("Enter an array of words separated by white space (sentence) without pressing Enter key");
                    String[] words = (scanner.nextLine()).split("\\s");
                    wordMapper.mapWordsAgainstPrefix(words);
                    if (!wordMapper.getMap().isEmpty()) {
                        System.out.println("\n\"Prefix\"\t\"List of words with that prefix\"");
                        System.out.println("___________________________________________");
                        wordMapper.printMapping();
                        wordMapper.getMap().clear();
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("1.Create a new map\n2.Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
        } while (choice != 2);
    }
}
