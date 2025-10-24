package Strings.Hard;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode Problem: 68. Text Justification
 *
 * Problem Description:
 * Given an array of strings `words` and a width `maxWidth`, format the text such that
 * each line has exactly `maxWidth` characters and is fully (left and right) justified.
 * The last line of text should be left-justified, and words should not be split.
 *
 * Example:
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 */
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        // This list will store the final formatted lines of text.
        List<String> result = new ArrayList<>();
        // `lineStartIndex` marks the beginning of the words for the current line we are building.
        int lineStartIndex = 0;

        // Loop through the words, processing one line at a time.
        while (lineStartIndex < words.length) {

            // --- Step 1: Find the words that fit on the current line ---
            // `lineEndIndex` will mark the end of the words for the current line.
            // `charLengthOnLine` tracks the length of only the characters, without spaces.
            int lineEndIndex = lineStartIndex;
            int charLengthOnLine = 0;

            // Greedily add words to the line as long as they fit.
            // The condition checks:
            // 1. `charLengthOnLine`: Length of words already on the line.
            // 2. `words[lineEndIndex].length()`: Length of the new word we're considering.
            // 3. `(lineEndIndex - lineStartIndex)`: Number of minimum required spaces (one between each word).
            while (lineEndIndex < words.length &&
                   (charLengthOnLine + words[lineEndIndex].length() + (lineEndIndex - lineStartIndex)) <= maxWidth) {
                charLengthOnLine += words[lineEndIndex].length();
                lineEndIndex++;
            }

            StringBuilder lineBuilder = new StringBuilder();
            int numWordsOnLine = lineEndIndex - lineStartIndex;

            // --- Step 2: Handle Special Cases (Last Line or Single-Word Line) ---
            // Both of these cases are left-justified, which is a simpler logic.
            boolean isLastLine = (lineEndIndex == words.length);
            boolean isOneWordLine = (numWordsOnLine == 1);

            if (isLastLine || isOneWordLine) {
                // Append words with a single space in between.
                for (int i = lineStartIndex; i < lineEndIndex; i++) {
                    lineBuilder.append(words[i]);
                    if (i < lineEndIndex - 1) { // Add space only if it's not the last word
                        lineBuilder.append(" ");
                    }
                }
                // Add all remaining spaces to the end to reach maxWidth.
                int padding = maxWidth - lineBuilder.length();
                lineBuilder.append(" ".repeat(padding));

            } else {
                // --- Step 3: Handle the General Case (Full Justification) ---
                int totalSpacesToDistribute = maxWidth - charLengthOnLine;
                int numGaps = numWordsOnLine - 1;

                // Calculate the base number of spaces for each gap and any extra spaces.
                int baseSpacesPerGap = totalSpacesToDistribute / numGaps;
                int extraSpacesToDistribute = totalSpacesToDistribute % numGaps;

                // Build the justified line.
                for (int i = lineStartIndex; i < lineEndIndex; i++) {
                    lineBuilder.append(words[i]);
                    
                    // Only add spaces if it's not the last word on the line.
                    if (i < lineEndIndex - 1) {
                        // Append the base number of spaces.
                        lineBuilder.append(" ".repeat(baseSpacesPerGap));
                        
                        // Distribute the extra spaces one by one from the left.
                        if (extraSpacesToDistribute > 0) {
                            lineBuilder.append(" ");
                            extraSpacesToDistribute--;
                        }
                    }
                }
            }
            
            // Add the fully formatted line to our final result list.
            result.add(lineBuilder.toString());
            // Move the start index to the beginning of the next line.
            lineStartIndex = lineEndIndex;
        }
        
        return result;
    }
}