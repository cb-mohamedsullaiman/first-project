package week2.day1_2.mapwords;

import java.util.Scanner;

public class WordMapperAgainstLength extends WordMapper {

    @Override
    public String getKey(String word) {
        return String.valueOf(word.length());
    }

    public void mapWordsAgainstLength(String[] words) {
        for (String word : words) {
            if (word.length() > 0) {
                mapWordsAgainstKey(word, getKey(word));
            }
        }

    }

    public static void main(String args[]) {
        Integer choice = 1;
        WordMapperAgainstLength wordMapper = new WordMapperAgainstLength();
        do {
            Scanner scanner = new Scanner(System.in);
            switch (choice) {
                case 1:
                    System.out.println("Enter an array of words separated by white space (sentences) without pressing Enter key");
                    String[] words = (scanner.nextLine()).split("\\s");
                    wordMapper.mapWordsAgainstLength(words);
                    if (!wordMapper.getMap().isEmpty()) {
                        System.out.println("\n\"Length\"\t\"List of words with that length\"");
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
