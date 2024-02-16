package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This program realises file text reader and word counter
 *
 * @author FellGast
 */

public class App 
{
    public static void main( String[] args )
    {

        File file = new File("input.txt");

        Map<String, Integer> wordCountMap = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                String[] words = line.split("[^\\p{L}]+");
                for (String word : words) {
                    word = word.toLowerCase();
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> words = new ArrayList<>(wordCountMap.keySet());
        Collections.sort(words);

        System.out.println("Статистика слов:");

        int maxCount = 0;
        String wordWithMaxCount = "";

        for (String word : words) {
            int count = wordCountMap.get(word);
            System.out.println(word + ": " + count + " раз");
            if (count > maxCount) {
                maxCount = count;
                wordWithMaxCount = word;
            }
        }

        System.out.println("Слово с максимальным количеством повторений: " + wordWithMaxCount + ", " + maxCount + " раз");
    }
}
