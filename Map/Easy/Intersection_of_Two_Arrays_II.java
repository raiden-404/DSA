class Solution {
    /**
     * 350. Intersection of Two Arrays II
     * Finds the intersection of two integer arrays, where each element in the result
     * must appear as many times as it shows in both arrays.
     * This solution uses a HashMap to store the frequency of elements in the first array,
     * allowing for O(1) average time complexity for lookups and updates.
     *
     * Time Complexity: O(N + M), where N is the length of nums1 and M is the length of nums2.
     * Space Complexity: O(min(N, M)), as we only store frequencies for the smaller array (ideally).
     *
     * @param nums1 The first integer array.
     * @param nums2 The second integer array.
     * @return An integer array containing the intersection elements with their correct multiplicities.
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        
        // Optimization: For better space efficiency, always iterate over the smaller array
        // first and store its counts in the map. Assuming nums1 is already the smaller array
        // or we choose to use nums1 as the basis.
        
        // Step 1: Count the frequency of each element in the first array (nums1).
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums1) {
            // Get the current count of 'n' (or 0 if not present) and increment it by 1.
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        // Step 2: Iterate through the second array (nums2) to find common elements.
        List<Integer> list = new ArrayList<>();
        for(int n : nums2) {
            // Retrieve the count of the current number 'n' from the map.
            int c = map.getOrDefault(n, 0);
            
            // Check if 'n' is present in nums1 AND its frequency count is still greater than 0.
            if(c > 0) {
                // If it is, 'n' is part of the intersection. Add it to the result list.
                list.add(n);
                
                // Decrement the count in the map. This ensures that we only include 
                // the element as many times as it appears in *both* arrays.
                // map.getOrDefault(n, 0) - 1 is redundant since we know c > 0, 
                // a simpler map.put(n, c - 1); would also work.
                map.put(n, c - 1); 
            }
        }
        
        // Step 3: Convert the result List<Integer> to a primitive int[] array.
        // This is a necessary final step as the method signature requires an int[].
        int[] res = new int[list.size()];
        for(int i=0; i<res.length; i++) {
            // The Integer object in the list is automatically unboxed to a primitive int.
            res[i] = list.get(i);
        }
        
        return res;
    }
}