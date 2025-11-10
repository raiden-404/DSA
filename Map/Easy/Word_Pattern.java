/*
 * Problem: 290. Word Pattern
 *
 * This solution checks for a bijective (one-to-one) mapping
 * between the characters in 'pattern' and the words in 's'.
 */
class Solution {
    public boolean wordPattern(String pattern, String s) {
        
        // Split the string 's' into an array of words, separated by spaces.
        String str[] = s.split(" ");
        
        // Get the length of the pattern (number of characters).
        int n = pattern.length();

        // A fast-fail check: If the number of characters in the pattern
        // does not equal the number of words in 's', a one-to-one
        // mapping is impossible.
        if(str.length != n) return false;

        // This map stores the forward mapping (char -> word).
        // Example: 'a' -> "dog"
        Map<Character, String> map = new HashMap<>();
        
        // This set stores the *values* (words) that have already been used.
        // This is crucial for ensuring the one-to-one mapping.
        // It prevents two different chars from mapping to the same word
        // (e.g., 'a' -> "dog" AND 'b' -> "dog").
        Set<String> set = new HashSet<>();

        // Iterate through the pattern and the words array simultaneously.
        for(int i=0; i<n; i++) {
            char c = pattern.charAt(i); // Current pattern character
            String currentWord = str[i]; // Current word

            // --- Case 1: We have seen this character 'c' before ---
            if(map.containsKey(c)) {
                // Get the word 'c' is *supposed* to map to.
                String expectedWord = map.get(c);
                
                // If the word we *expect* doesn't match the *current* word,
                // the pattern is broken.
                if(!expectedWord.equals(currentWord)){
                    return false;
                }
            } 
            // --- Case 2: This is a new character 'c' ---
            else {
                // We must ensure the *current word* hasn't been mapped
                // by a *different* character already.
                if(set.contains(currentWord)){
                    // If it has, the one-to-one rule is broken.
                    return false;  
                } 
                
                // This is a valid, new mapping.
                // Add the mapping to the map.
                map.put(c, currentWord);
                // Add the word to the set of used words.
                set.add(currentWord);
            }
        }
        
        // If the loop finishes without finding any mismatches,
        // the pattern is valid.
        return true;
    }
}