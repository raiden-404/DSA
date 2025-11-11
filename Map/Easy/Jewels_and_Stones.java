class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        
        // Create an integer array to act as a frequency map (or "hash map").
        // This array will store the counts of each character (stone).
        //
        // Why size 58?
        // The problem states characters are English letters (both 'a'-'z' and 'A'-'Z').
        // We can map each character to a unique index in this array.
        // We use 'A' as the base (ASCII value 65).
        // Index for 'A' = 'A' - 'A' = 65 - 65 = 0
        // Index for 'Z' = 'Z' - 'A' = 90 - 65 = 25
        // ... (gap in ASCII table)
        // Index for 'a' = 'a' - 'A' = 97 - 65 = 32
        // Index for 'z' = 'z' - 'A' = 122 - 65 = 57
        //
        // To cover the index 57, we need an array of size 58 (indices 0 to 57).
        int[] arr = new int[58];
        
        // 'res' will store our final result, the total count of stones that are jewels.
        int res = 0;
        
        // --- First Pass: Count all the stones ---
        // Loop through every character 'c' in the 'stones' string.
        for(char c : stones.toCharArray()) {
            // Calculate the unique index for this character.
            // Then, increment the count at that index.
            // For example, if c = 'a', its index is 32. We do arr[32]++.
            // After this loop, arr[32] will hold the total number of 'a's
            // found in the 'stones' string.
            arr[c - 'A']++;
        }
        
        // --- Second Pass: Match jewels to the stone counts ---
        // Loop through every character 'c' in the 'jewels' string.
        for(char c : jewels.toCharArray()) {
            // For each jewel, find its corresponding index in our frequency map.
            // This is the *same* calculation as before (c - 'A').
            //
            // Add the count stored at that index to our result.
            // Example: If the jewel is 'a' (index 32), and we found 5 'a' stones
            // (meaning arr[32] is 5), this will add 5 to 'res'.
            // If the jewel is 'X' (index 23), and there were no 'X' stones
            // (meaning arr[23] is 0), this will correctly add 0 to 'res'.
            res += arr[c - 'A'];
        }
        
        // Return the final total count.
        return res;
    }
}