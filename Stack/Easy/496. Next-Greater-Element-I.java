// 496. Next Greater Element I

class Solution {
    /**
     * Finds the next greater element for each element of nums1 in nums2.
     * * The approach uses a **monotonic decreasing stack** and a **hash map**.
     * A monotonic stack is a stack whose elements are always in a sorted order 
     * (either increasing or decreasing).
     *
     * ### Algorithm Breakdown:
     * 1.  We iterate through `nums2` from left to right. We use a stack to keep track of elements 
     * for which we haven't yet found a "next greater element". The stack will always 
     * maintain elements in a decreasing order from bottom to top.
     *
     * 2.  For each number `num` from `nums2`, we check the top of the stack. If `num` is 
     * greater than the element at the top of the stack, it means `num` is the "next greater element"
     * for the element at the top. We pop the smaller element and store this pair (`popped_element -> num`) 
     * in a hash map. We repeat this until the stack is empty or the top element is larger than `num`.
     *
     * 3.  After the check, we push the current `num` onto the stack. This maintains the decreasing order.
     *
     * 4.  After iterating through all of `nums2`, any elements left in the stack do not have a 
     * next greater element in the array. We pop them and map them to -1.
     *
     * 5.  Finally, we iterate through `nums1`. For each element, we look up its next greater 
     * element in our pre-computed map and build the result array.
     *
     * ### Complexity:
     * -   **Time Complexity:** $O(m + n)$, where `n` is the length of `nums2` and `m` is the length of `nums1`.
     * Each element in `nums2` is pushed and popped from the stack at most once, making the first part $O(n)$.
     * The final loop to build the result is $O(m)$.
     * -   **Space Complexity:** $O(n)$ to store the hash map and the stack in the worst-case scenario.
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // The stack will store numbers from nums2 for which we are looking for a next greater element.
        // It maintains a monotonic decreasing property (top element is the smallest).
        Deque<Integer> stack = new ArrayDeque<>();

        // The map stores the mapping from an element to its next greater element.
        // Key: number from nums2, Value: its next greater element.
        Map<Integer, Integer> map = new HashMap<>();

        int m = nums1.length;
        int n = nums2.length;
        
        // Loop through each element in the second array (nums2) to compute next greater elements.
        for(int i=0; i<n; i++) {
            // While the stack is not empty AND the current element nums2[i] is greater
            // than the element at the top of the stack...
            while(!stack.isEmpty() && stack.peek() < nums2[i]) {
                // ...it means we've found the next greater element (nums2[i]) for the element at the top of the stack.
                // So, we pop the element from the stack and record this relationship in our map.
                map.put(stack.pop(), nums2[i]);
            }
            // Push the current element onto the stack. It will now wait for its
            // own next greater element to be found in subsequent iterations.
            stack.push(nums2[i]);
        }

        // After iterating through nums2, any elements remaining in the stack do not have
        // a next greater element to their right in the array.
        while(!stack.isEmpty()) {
            // For these remaining elements, map them to -1.
            map.put(stack.pop(), -1);
        }

        // Prepare the result array with the same length as nums1.
        int[] res = new int[m];
        
        // Iterate through nums1 to construct the result array using our pre-computed map.
        for(int i=0; i<m; i++) {
            // For each element in nums1, find its corresponding next greater element from the map.
            res[i] = map.get(nums1[i]);
        }

        // Return the final result array.
        return res;
    }
}