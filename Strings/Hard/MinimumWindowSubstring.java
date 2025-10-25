package Strings.Hard;

/**
 * LeetCode Problem: 76. Minimum Window Substring
 *
 * Approach:
 * This solution uses the "Sliding Window with Match Counter" technique, which is
 * the optimal approach for this problem.
 *
 * 1.  Frequency Map for T: First, we build a frequency map (`targetCharCounts`)
 * for the target string `t`. We also count the number of *unique* characters
 * in `t` (`uniqueCharsInTarget`).
 *
 * 2.  Sliding Window: We use two pointers, `left` and `right`, to define a "window"
 * in the string `s`. The `right` pointer expands the window, and the `left`
 * pointer contracts it.
 *
 * 3.  Frequency Map for Window: We maintain a second frequency map
 * (`windowCharCounts`) that tracks the character counts *inside* our current window.
 *
 * 4.  Match Counters:
 * - `uniqueCharsInTarget`: The number of unique characters we need to find (e.g., for "AABC", this is 3).
 * - `charsFormedInWindow`: Tracks how many of those unique characters we have
 * "satisfied" (i.e., our window has at least as many of them as `t` does).
 *
 * 5.  Expand & Contract:
 * - We expand the window (move `right`) until `charsFormedInWindow == uniqueCharsInTarget`.
 * - Once we have a valid window, we check if it's a new minimum.
 * - We then contract the window (move `left`) until the window is no longer
 * valid, checking for a new minimum at each step.
 */
class Solution {
    public String minWindow(String s, String t) {
        
        // --- Step 1: Handle Edge Cases ---
        if (s.length() < t.length()) {
            return "";
        }

        // --- Step 2: Setup Frequency Maps and Counters ---

        // Use arrays of size 128 to cover the full ASCII character set.
        int[] windowCharCounts = new int[128];
        int[] targetCharCounts = new int[128];

        // The number of unique characters in 't' that we need to match.
        int uniqueCharsInTarget = 0;
        
        // Populate the frequency map for the target string 't'.
        for (char c : t.toCharArray()) {
            if (targetCharCounts[c] == 0) {
                uniqueCharsInTarget++; // Found a new unique character
            }
            targetCharCounts[c]++;
        }

        // `charsFormedInWindow` tracks how many unique characters in our window
        // have met the required count from `targetCharCounts`.
        int charsFormedInWindow = 0;
        
        // `left` pointer for the start of the window.
        int left = 0;

        // --- Step 3: Result Tracking Variables ---
        // These store the length and start index of the smallest valid window found so far.
        int minWindowLength = Integer.MAX_VALUE;
        int minWindowStart = 0;

        // --- Step 4: Start the Sliding Window ---
        
        // The `right` pointer expands the window one character at a time.
        for (int right = 0; right < s.length(); right++) {
            
            // --- Part A: Expand the Window ---
            char charRight = s.charAt(right);
            windowCharCounts[charRight]++;

            // This is the "form" check. We check if this character is one we need
            // AND if we have just found the required number of them.
            if (targetCharCounts[charRight] > 0 && 
                windowCharCounts[charRight] == targetCharCounts[charRight]) {
                charsFormedInWindow++;
            }

            // --- Part B: Contract the Window ---
            // Once `charsFormedInWindow == uniqueCharsInTarget`, we have a valid window.
            // Now we must try to shrink it from the left to find the smallest possible window.
            while (left <= right && charsFormedInWindow == uniqueCharsInTarget) {
                
                // Check if this new window is the smallest one we've seen.
                int currentWindowLength = right - left + 1;
                if (currentWindowLength < minWindowLength) {
                    minWindowLength = currentWindowLength;
                    minWindowStart = left;
                }

                // Remove the leftmost character from the window to shrink it.
                char charLeft = s.charAt(left);
                windowCharCounts[charLeft]--;

                // This is the "break" check. We check if removing this character
                // just broke our match for that specific character.
                if (targetCharCounts[charLeft] > 0 && 
                    windowCharCounts[charLeft] < targetCharCounts[charLeft]) {
                    charsFormedInWindow--; // We no longer satisfy this character
                }

                // Move the left pointer to the right.
                left++;
            }
        }

        // --- Step 5: Return the Final Result ---
        // If `minWindowLength` was never updated, it means we never found a valid window.
        // Otherwise, return the smallest window we found.
        return minWindowLength == Integer.MAX_VALUE ? "" : s.substring(minWindowStart, minWindowStart + minWindowLength);
    }
}