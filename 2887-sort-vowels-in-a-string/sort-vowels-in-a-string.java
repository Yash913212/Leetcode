import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    /**
     * Sorts the vowels within a string while keeping consonants in place.
     *
     * @param s The input string.
     * @return A new string with sorted vowels.
     */
    public String sortVowels(String s) {
        // A string containing all possible vowel characters for easy lookup.
        final String VOWEL_CHARS = "AEIOUaeiou";
        
        // Step 1: Collect all vowels from the input string.
        List<Character> vowels = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (VOWEL_CHARS.indexOf(c) != -1) {
                vowels.add(c);
            }
        }
        
        // If there are no vowels, the string remains unchanged.
        if (vowels.isEmpty()) {
            return s;
        }

        // Step 2: Sort the collected vowels by their ASCII values.
        Collections.sort(vowels);

        // Step 3: Build the result by replacing vowels in the original string
        // with the sorted vowels.
        char[] resultChars = s.toCharArray();
        int sortedVowelIndex = 0;
        for (int i = 0; i < resultChars.length; i++) {
            if (VOWEL_CHARS.indexOf(resultChars[i]) != -1) {
                resultChars[i] = vowels.get(sortedVowelIndex++);
            }
        }
        
        return new String(resultChars);
    }
}