class Solution {
    public int longestConsecutive(int[] nums) {
        // 1. Create a set for O(1) average-time lookups.
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // 2. Iterate through each number in the set.
        //    (Iterating the set is slightly better than the array
        //     to avoid re-checking duplicate numbers.)
        for (int num : numSet) {
            
            // 3. THE KEY: Only start counting if 'num' is the
            //    start of a sequence. We know it's a start if
            //    'num - 1' is NOT in the set.
            if (!numSet.contains(num - 1)) {
                
                // This 'num' is a sequence-starter, so let's count.
                int currentNum = num;
                int currentStreak = 1;

                // 4. Look forward (num + 1, num + 2, ...) to see
                //    how long this sequence goes.
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // 5. Update the global maximum after the streak ends.
                longestStreak = Math.max(longestStreak, currentStreak);
            }
            // 6. If 'num - 1' *was* in the set, we do nothing.
            //    Why? Because 'num' is part of a sequence that
            //    was already counted (or will be) by its
            //    starting number.
        }

        return longestStreak;
    }
}