// LeetCode 349. Intersection of Two Arrays

class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {

        // Map to mark if a number from nums1 is already seen in the result
        Map<Integer, Integer> map = new HashMap<>();

        // List to store unique intersection elements
        ArrayList<Integer> list = new ArrayList<>();

        // Step 1: Put all nums1 elements in the map with flag = 0
        for (int n : nums1) {
            map.put(n, 0);
        }

        // Step 2: Check nums2 elements
        for (int n : nums2) {
            int flag = map.getOrDefault(n, 1);

            // If element exists in nums1 (flag == 0), add to result only once
            if (flag == 0) {
                list.add(n);
                map.put(n, 1); // Mark it as added to avoid duplicates
            }
        }

        // Step 3: Convert ArrayList to int[]
        int[] res = new int[list.size()];
        int i = 0;
        for (int n : list) {
            res[i++] = n;
        }

        return res;
    }
}
