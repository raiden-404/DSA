// Problem: 217. Contains Duplicate

class Solution {
    /**
     * Checks if an array contains any duplicate elements.
     *
     * @param nums The input array of integers.
     * @return true if any value appears at least twice in the array, 
     * false if every element is distinct.
     */
    public boolean containsDuplicate(int[] nums) {
        
        // Handle the edge case of an empty or null array.
        // While the loop wouldn't run anyway, this is a clean check.
        if (nums == null || nums.length == 0) {
            return false;
        }

        // Create a HashSet. This data structure provides O(1) average time
        // complexity for add and contains operations.
        // We will use it to store the numbers we have already encountered.
        Set<Integer> set = new HashSet<>();

        // Iterate through each number 'n' in the input array 'nums'.
        for (int n : nums) {
            
            // This is the core logic:
            // 1. We attempt to add the current number 'n' to the set.
            // 2. The set.add() method has a very useful return value:
            //    - It returns 'true' if the element was successfully added (meaning it was NOT already in the set).
            //    - It returns 'false' if the element was NOT added (meaning it WAS already in the set).
            //
            // 3. We check if the return value is 'false' (using the '!' NOT operator).
            //    If !set.add(n) evaluates to true, it means set.add(n) returned false,
            //    which tells us the number 'n' is already in the set.
            if (!set.add(n)) {
                
                // We have found a duplicate. We can stop searching and return true immediately.
                return true;
            }
        }

        // If the loop finishes completely, it means we iterated through the
        // entire array and successfully added every element to the set.
        // This implies all elements were unique, so we return false.
        return false;
    }
}