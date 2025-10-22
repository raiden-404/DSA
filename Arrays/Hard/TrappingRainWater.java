class Solution {
    /**
     * 42. Trapping Rain Water
     *
     * Calculates the total amount of rainwater that can be trapped between bars of varying heights.
     *
     * This solution uses the optimal Two-Pointer approach.
     *
     * The core idea is that the amount of water trapped above any bar is determined by the height
     * of the shorter of the two tallest walls to its left and right.
     * water[i] = min(max_height_to_left[i], max_height_to_right[i]) - height[i]
     *
     * Instead of pre-calculating the max heights for every position (which would take O(n) space),
     * this approach calculates it on the fly in O(1) space.
     *
     * We use two pointers, `left` and `right`, starting at the ends of the array. We also maintain
     * `leftMax` and `rightMax`, which track the tallest bar seen so far from each side.
     *
     * The key insight:
     * When we are at the `left` pointer, if `leftMax < rightMax`, we know for sure that the amount of
     * water trapped at the `left` position is determined by `leftMax`. Why? Because `rightMax` is the
     * tallest bar we've seen from the right, but there could be an even taller bar between `left`
     * and `right`. However, since `leftMax` is smaller than `rightMax`, `leftMax` is the guaranteed
     * bottleneck (the minimum of the two walls) for the current `left` position.
     *
     * A symmetrical logic applies when we are at the `right` pointer and `rightMax <= leftMax`.
     *
     * @param height An array of non-negative integers representing an elevation map.
     * @return The total units of trapped rainwater.
     */
    public int trap(int[] height) {
        // If the array is too short to trap any water, return 0.
        if (height == null || height.length < 3) {
            return 0;
        }

        // Initialize a pointer at the beginning of the array.
        int left = 0;
        // Initialize a pointer at the end of the array.
        int right = height.length - 1;

        // Tracks the maximum height seen so far from the left side. Initialized with the first bar.
        int leftMax = height[left];
        // Tracks the maximum height seen so far from the right side. Initialized with the last bar.
        int rightMax = height[right];

        // This will accumulate the total trapped water.
        int water = 0;

        // The main loop continues as long as the two pointers have not crossed.
        // Once they meet, we have processed the entire array.
        while (left < right) {
            // The core logic: Compare the max heights from both sides.
            // We process the side that has the smaller max height, as it's the limiting factor for trapping water.
            if (leftMax < rightMax) {
                // Since leftMax is the smaller wall, we can confidently calculate the water trapped at the `left` pointer's position.
                // We know that there is a wall on the right (`rightMax`) that is at least as tall as `leftMax`.

                // Move the left pointer one step to the right because we are about to process this new position.
                left++;

                // Update the maximum height seen from the left.
                leftMax = Math.max(leftMax, height[left]);

                // Calculate the trapped water at the current `left` position.
                // The water level is determined by the `leftMax` wall.
                // The amount of trapped water is the water level minus the height of the current bar.
                // If the current bar `height[left]` is the new `leftMax`, this calculation adds 0, which is correct.
                water += leftMax - height[left];

            } else { // This block executes if rightMax <= leftMax
                // Symmetrical logic: Since rightMax is the smaller (or equal) wall, it's the bottleneck.
                // We can confidently calculate the water trapped at the `right` pointer's position.

                // Move the right pointer one step to the left.
                right--;

                // Update the maximum height seen from the right.
                rightMax = Math.max(rightMax, height[right]);

                // Calculate the trapped water at the current `right` position using `rightMax` as the water level.
                water += rightMax - height[right];
            }
        }

        // Return the total accumulated water.
        return water;
    }
}