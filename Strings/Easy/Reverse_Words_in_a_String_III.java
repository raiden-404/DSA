/*
 * Question Name: Reverse Words in a String III
 * Question No:   557
 * Platform:      LeetCode
 *
 * Complexity Analysis:
 * Time:  O(N) - We iterate over every character in the string (splitting, reversing, joining).
 * Space: O(N) - We create an array of words and a character array for swapping.
 */

class Solution {
    public String reverseWords(String s) {
        // 1. Split the string into words based on whitespace
        // split("\\s+") handles multiple spaces if they existed (though LC 557 usually has single spaces)
        String[] arr = s.trim().split("\\s+");

        // 2. Iterate through each word
        for(int i = 0; i < arr.length; i++) {
            
            // Convert the current word to a char array to perform the swap
            char[] c = arr[i].toCharArray();
            int n = c.length - 1;
            
            // 3. Reverse the characters within the current word (Two Pointer approach)
            for(int j = 0; j <= n / 2; j++) {
                char temp = c[j];
                c[j] = c[n - j];
                c[n - j] = temp;
            }
            
            // Convert the char array back to a String and update the array
            arr[i] = new String(c);
        }

        // 4. Join the reversed words back into a single string with spaces
        return String.join(" ", arr);
    }
}