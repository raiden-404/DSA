/*
 * Problem: 3740. Minimum Distance Between Three Equal Elements I
 *
 * This is an optimal O(N) time and O(N) space solution.
 *
 * Logic:
 * 1. We iterate through the array once, storing the indices of each
 * number in a HashMap. (Key: number, Value: List of indices).
 *
 * 2. The distance formula abs(i - j) + abs(j - k) + abs(k - i)
 * is given. If we assume the indices are sorted i < j < k,
 * this simplifies to (j - i) + (k - j) + (k - i) = 2k - 2i = 2 * (k - i).
 *
 * 3. To find the *minimum* distance, we just need to find the
 * minimum "span" (k - i) for any three-element window of indices
 * for a given number.
 *
 * 4. This code cleverly checks this minimum span in one pass. Every time
 * a new index is added, it checks the distance between the newest index
 * (size - 1) and the index two positions back (size - 3). This
 * creates a "sliding window" (e.g., [idx0, idx1, idx2],
 * then [idx1, idx2, idx3], etc.) and finds the minimum span.
 */
class Solution {
    public int minimumDistance(int[] nums) {
        // Map to store a list of indices for each number.
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        // Initialize minimum distance to -1 (as required if no good tuple exists).
        int dis = -1;

        // Single pass through the array.
        for(int i=0; i<nums.length; i++) {
            
            // Check if we've seen this number before.
            if(map.containsKey(nums[i])) {
                // Get the existing list of indices.
                ArrayList<Integer> temp = map.get(nums[i]);
                // Add the new (current) index.
                temp.add(i);
                // (No need to put, as 'temp' is a reference)
                
                // --- Check for a new minimum distance ---
                // We only calculate if we have at least 3 indices.
                if(temp.size() >= 3) {
                    int size = temp.size();
                    
                    // Get the 'i' of the most recent 3-element window.
                    // This is the index at 'size - 3'.
                    int first = (int) temp.get(size - 3);
                    
                    // Get the 'k' of the most recent 3-element window.
                    // This is the newest index at 'size - 1'.
                    int third = (int) temp.get(size - 1);
                    
                    // Calculate distance using the simplified formula: 2 * (k - i)
                    // We use Math.abs just in case, though 'third' > 'first' is guaranteed.
                    int d = 2 * Math.abs(first - third);
                    
                    // If this is the first distance we've found OR
                    // if this new distance 'd' is smaller than the current 'dis'.
                    if(dis == -1 || dis > d) {
                        dis = d; // Update the minimum distance.
                    }
                }
            } else {
                // First time seeing this number.
                // Create a new list, add the current index 'i', and put it in the map.
                map.put(nums[i], new ArrayList<>(Arrays.asList(i)));
            }
        }
        
        // Return the minimum distance found, or -1 if no 3-element tuple was ever found.
        return dis;
    }
}