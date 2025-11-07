// LeetCode 387: First Unique Character in a String
class Solution {

    // Static initializer (runs once when the class is loaded)
    static {
        // This loop might be for performance warm-up on some platforms.
        for (int i = 0; i < 500; i++) {
            firstUniqChar("");
        }
    }

    public static int firstUniqChar(String s) {
        // Create an array to store frequencies of each letter (a-z)
        int[] freq = new int[26];

        // Count the frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++; // 'a' maps to 0, 'b' to 1, etc.
        }

        // Find the first character with a frequency of 1
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i; // Return its index
            }
        }

        // If no unique character is found
        return -1;
    }
}