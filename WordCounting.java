import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

/**
 * WordCounting
 */
public class WordCounting {
    public static void main(String[] args) {
        Table<String, Integer> wordCounter = new HashTable<String, Integer>(470000); 
        // 470000 is the number of english words in webster's english dictionary (https://www.merriam-webster.com/help/faq-how-many-english-words)

        // read words
        int totalDistinctWords = 0;
        int totalWords = 0;
        try (BufferedReader r = new BufferedReader(new FileReader(args[0]))){
            String line = null;
            while((line = r.readLine()) != null){
                String[] words = line.split("[^A-Za-z']+");
                for (String word : words) {
                    word = word.toLowerCase();
                    if(!(word.equals("") || word.equals("'"))){
                        totalWords++;
                        Integer numOccurances = wordCounter.get(word);
                        if (numOccurances == null) {
                            totalDistinctWords++;
                            numOccurances = 0;
                        }
                        numOccurances++;
                        wordCounter.put(word, numOccurances);
                    }
                    
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading txt file. Please provide a valid txt.\nUsage: java WordCounting <document file name>");
            return;
        }
        
        // reterive each of the keys from the table using the iterator
        Iterator<String> it = wordCounter.iterator();
        String[] words = new String[totalDistinctWords];
        int c = 0;
        while (it.hasNext()) {
            String word = it.next();
            words[c++] = word;
        }

        // Sort words using Shell Sort
        // written using psuedocode from https://en.wikipedia.org/wiki/Shellsort
        // Using Tokuda's gaps for better performance than Knuth (https://oeis.org/A108870)
        // Knuth's is commented out below if preferred.
        //int[] gaps = {797161, 265720, 88573, 29524, 9841, 3280, 1093, 364, 121, 40, 13, 4, 1};
        int[] gaps = {345152, 153401, 68178, 30301, 13467, 5985, 2660, 1182, 525, 233, 103, 46, 20, 9, 4, 1};
        for (int gap : gaps) {
            for (int i = gap; i < totalDistinctWords; i++) {
                String tmp = words[i];
                int j = i;
                for(; (j >= gap) && (words[j-gap].compareTo(tmp) > 0); j -= gap){
                    words[j] = words[j - gap];
                }
                words[j] = tmp;
            }
        }

        // print words with values
        for (String word : words) {
            System.out.println(word + ": " + wordCounter.get(word));
        }
        
        // print stats
        System.out.println("\n\nTotal Entries: " + totalDistinctWords);
        System.out.println("Total Occurrences for All Words: " + totalWords);
    }
}
