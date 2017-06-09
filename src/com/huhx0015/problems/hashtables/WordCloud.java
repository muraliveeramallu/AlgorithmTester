package com.huhx0015.problems.hashtables;

import java.util.HashMap;
import java.util.Map;

/**
 * Interview Cake:
 *
 * You want to build a word cloud, an infographic where the size of a word corresponds to how often it appears in the
 * body of text.
 *
 * To do this, you'll need data. Write code that takes a long string and builds its word cloud data in a hash map ↴ ,
 * where the keys are words and the values are the number of times the words occurred.
 *
 * Think about capitalized words. For example, look at these sentences:
 *
 * "After beating the eggs, Dana read the next step:"
 * "Add milk and eggs, then add flour and sugar."
 *
 * What do we want to do with "After", "Dana", and "add"? In this example, your final hash map should include one "Add"
 * or "add" with a value of 2. Make reasonable (not necessarily perfect) decisions about cases like "After" and "Dana".
 *
 * Assume the input will only contain words and standard punctuation.
 *
 * Created by Michael Yoon Huh on 5/24/2017.
 */
public class WordCloud {

    public static void main(String[] args) {

        String words = "After beating the eggs, Dana read the next step: Add milk and eggs, then add flour and sugar.";
        String words2 = "We came, we saw, we conquered...then we ate Bill's (Mille-Feuille) cake. The bill came to five dollars.";

        HashMap<String, Integer> wordMap = wordCloud(words2);

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {

            String word = entry.getKey();
            int count = entry.getValue();

            System.out.println("Word: " + word + " | Count: " + count);
        }
    }

    // Run-Time: O(n) [n = number of characters in string input], Space-Complexity: O(k) [k = number of words]
    private static HashMap<String, Integer> wordCloud(String words) {

        if (words == null) {
            return null;
        }

        HashMap<String, Integer> wordMap = new HashMap<>();

        // Converts words input into char array. Analyzes each character in the input to find beginning of a word and
        // end of a word, while ignoring any special characters.
        StringBuilder wordBuilder = new StringBuilder();
        for (char c : words.toCharArray()) {

            // Checks if character is alphabetical character.
            if (isCharacter(c)) {
                wordBuilder.append(c);
            }

            // If a non-character has been encountered and a word exists in wordBuilder, we've reached the end of the
            // word and add it to the HashMap.
            else {

                if (wordBuilder.length() > 0) {

                    // Sets word to lower case so that capitalization doesn't matter.
                    String wordToLowerCase = wordBuilder.toString().toLowerCase();

                    // If this word already exists in the Hashmap, we need to get the count value and then increment it.
                    if (wordMap.containsKey(wordToLowerCase)) {
                        int wordCount = wordMap.get(wordToLowerCase); // Current count of the word.
                        wordMap.put(wordToLowerCase, wordCount + 1); // Increment the count of this word.
                    } else {
                        wordMap.put(wordToLowerCase, 1); // Otherwise, just add the word with count starting at 1.
                    }
                }

                // Resets the StringBuilder.
                wordBuilder = new StringBuilder();
            }
        }

        return wordMap;
    }

    // Helper method to determine if char is a non-alphabetical character.
    // ASCII Table: https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
    private static boolean isCharacter(char c) {

        int asciiValue = c;

        // Alphabetical characters only exist between 65-90, 97-122.
        if ( (asciiValue > 64 && asciiValue < 91) || (asciiValue > 96 && asciiValue < 123)) {
            return true;
        } else {
            return false;
        }
    }


    // Big(O): Runtime is O(n), with space complexity of O(k), k being number of words in the hashmap.
    private static HashMap<String, Integer> wordCloudUsingSplit(String words) {

        if (words == null) {
            System.out.println("wordCloudUsingSplit(): ERROR: Input words was null!");
            return null;
        }

        String[] wordList = words.split("\\W+"); // Splits the words input into an array of strings, using a regex pattern that ignores punctuations.

        HashMap<String, Integer> wordMap = new HashMap<>(); // Stores the strings and frequency of the words encountered.

        for (String word : wordList) {

            String wordToLowerCase = word.toLowerCase(); // Sets word to lower case so that capitalization doesn't matter.

            // If this word already exists in the Hashmap, we need to get the count value and then increment it.
            if (wordMap.containsKey(wordToLowerCase)) {
                int wordCount = wordMap.get(wordToLowerCase); // Current count of the word.
                wordMap.put(wordToLowerCase, wordCount + 1); // Increment the count of this word.
            } else {
                wordMap.put(wordToLowerCase, 1); // Otherwise, just add the word with count starting at 1.
            }
        }

        return wordMap;
    }
}
