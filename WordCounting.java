import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

/**
 * WordCounting
 */
public class WordCounting {
    public static void main(String[] args) {
        Table<String, Integer> wordCounter = new HashTable<String, Integer>(470000); // number of english words in 1993 eng dict

        // read words
        try (BufferedReader r = new BufferedReader(new FileReader(args[1]))){
            String line = null;
            while((line = r.readLine()) != null){
                // possible error: does this give me empty strings to add too?
                String[] words = line.split("[^A-Za-z']+");
                for (String word : words) {
                    word = word.toLowerCase();
                    Integer numOccurances = wordCounter.get(word);
                    numOccurances = numOccurances == null ? 1 : numOccurances+1;
                    wordCounter.put(word, numOccurances);
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading txt file. Please provide a valid txt.\nUsage: java WordCounting <document file name>");
            return;
        }
        
        // print words
        Iterator<String> it = wordCounter.iterator();
        int totalDistinctWords = 0;
        int totalWords = 0;
        System.out.println("Printing unsorted words:\n\n\n");
        while (it.hasNext()) {
            String word = it.next();
            int count = wordCounter.get(word);
            System.out.println(word + " with count of " + count);
            totalDistinctWords++;
            totalWords += count;
        }

        // print the words along with their counts in alphabetical order
            // reterive each of the keys from the table using the iterator
            // call using the key value to retreive the count for each
            // sort the entries using a sort written by self, better than O(n^2) performance

        // print total number of entries (distinct words)
        // print total number of occurrences for all words
        System.out.println("\n\nTotal Entries: " + totalDistinctWords);
        System.out.println("Total Occurrences for all words: " + totalWords);
        
    }
}
