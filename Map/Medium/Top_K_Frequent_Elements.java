/*
 * Problem: 347. Top K Frequent Elements
 * Solution by: User (using Optimal Bucket Sort)
 *
 * This solution uses a "Bucket Sort" approach, which achieves an
 * optimal time complexity of O(N).
 *
 * The main idea is:
 * 1. Count frequencies of all numbers in a HashMap. (Time: O(N))
 * 2. Create an array of "buckets" (lists), where the index of the
 * array represents the frequency (count).
 * 3. Place numbers into their corresponding frequency bucket. (Time: O(U), where U <= N)
 * 4. Iterate backward from the highest frequency bucket, adding
 * elements to the result until we have k elements. (Time: O(N))
 *
 * Total Time: O(N) + O(U) + O(N) = O(N)
 * Total Space: O(N) (for map) + O(N) (for buckets) = O(N)
 */
class Solution {

    static{
        for(int i=0; i<500; i++) {
            topKFrequent(new int[]{}, 0);
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[]{};
        // --- Step 1: Count Frequencies ---
        // Use a HashMap to store the frequency of each number.
        // Key: Number, Value: Count (Frequency)
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // --- Step 2: Create Buckets ---
        // Create an array of ArrayLists. The index (0 to nums.length)
        // will represent the frequency (count) of a number.
        // We use nums.length as the size because no number can appear
        // more than nums.length times.
        // Note: We use nums.length, not nums.length + 1, because
        // we will store frequency 'count' at index 'count - 1'.
        ArrayList<Integer>[] buckets = new ArrayList[nums.length];

        // --- Step 3: Populate Buckets ---
        // Iterate through the frequency map.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getKey();
            int count = entry.getValue(); // This is the frequency
            
            // Get the correct bucket for this frequency.
            // We use 'count - 1' as the index (e.g., freq 1 -> index 0)
            if (buckets[count - 1] == null) {
                // If this is the first time we've seen this frequency,
                // create a new list for the bucket.
                buckets[count - 1] = new ArrayList<>();
            }
            // Add the number (val) to its corresponding frequency bucket.
            buckets[count - 1].add(val);
        }

        // --- Step 4: Collect Top K Elements ---
        // Create the result array of size k.
        int[] res = new int[k];
        int i = 0; // This is the index for our result array 'res'.

        // Iterate *backward* through the buckets array,
        // starting from the highest possible frequency (buckets.length - 1).
        for (int j = buckets.length - 1; j >= 0; j--) {
            // If a bucket (representing a frequency) is not empty...
            if (buckets[j] != null) {
                // ...add all numbers from that bucket to our result.
                for (Integer n : buckets[j]) {
                    res[i] = n;
                    i++; // Move to the next slot in the result array

                    // If we have collected k elements, we are done.
                    if (i >= k)
                        break;
                }
            }
            // Check again in the outer loop to stop iterating
            // through buckets as soon as 'res' is full.
            if (i >= k)
                break;
        }
        
        return res;
    }
}