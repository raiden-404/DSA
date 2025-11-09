// Problem: 169. Majority Element

class Solution {
    /**
     * Finds the majority element in an array.
     * The majority element is the element that appears more than ⌊n / 2⌋ times.
     * This solution uses a HashMap to track the frequency of each element.
     *
     * @param nums The input array of integers.
     * @return The majority element.
     */
    public int majorityElement(int[] nums) {
        
        // Handle the edge case of an empty array.
        // (Note: The problem statement guarantees a majority element exists,
        // which implies the array is non-empty, but this is good practice).
        if (nums.length == 0) return -1;

        // Create a HashMap to store the frequency of each number.
        // Key: The number from the array.
        // Value: The number of times it has appeared so far.
        Map<Integer, Integer> map = new HashMap<>();

        // Iterate through each number 'n' in the input array 'nums'.
        for (int n : nums) {
            
            // Check if we have already seen this number.
            if (map.containsKey(n)) {
                
                // If yes, get its current count.
                int count = map.get(n);
                
                // Check if the *new* count (count + 1) will be greater than n/2.
                // If it is, we have found the majority element and can stop early.
                if (count + 1 > nums.length / 2) {
                    return n;
                }
                
                // If it's not the majority element yet, just update its count in the map.
                map.put(n, count + 1);

            } else {
                
                // If this is the first time seeing this number, add it to the map
                // with a count of 1.
                map.put(n, 1);
            }
        }
        
        // This return statement handles the specific edge case of a single-element
        // array (e.g., [1]).
        // In that case, the loop will run, put (1, 1) in the map, and exit.
        // '1' is the correct majority element (1 > 1/2 = 0).
        //
        // For any array with length > 1, the 'if(count + 1 > ...)' check
        // inside the loop is *guaranteed* to trigger and return the answer
        // *before* this line is ever reached, due to the problem's constraints.
        return nums[0];
    }
}