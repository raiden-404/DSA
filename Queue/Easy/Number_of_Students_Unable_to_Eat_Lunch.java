// LeetCode Problem 1700: Number of Students Unable to Eat Lunch

class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        /**
         * Optimal Approach: Frequency Counting
         * ------------------------------------
         * The key insight is that the order of students in the line only matters
         * until the point where no student wants the top sandwich. After that,
         * they will cycle indefinitely.
         *
         * Instead of simulating the queue, we can simply count the number of students
         * who prefer circular sandwiches (0) and square sandwiches (1). Then, we
         * iterate through the sandwiches. If the top sandwich is available and a
         * student wants it, we decrement the count. If no student wants the top
         * sandwich, we know the remaining students can't eat.
         *
         * Time Complexity: O(N) - We iterate through both arrays once.
         * Space Complexity: O(1) - We only use a few extra variables for counts.
         */

        // Step 1: Count the initial preferences for each type of sandwich.
        int zeros = 0; // Count of students who want circular sandwiches (0)
        int ones = 0;  // Count of students who want square sandwiches (1)
        for(int student : students) {
            if(student == 0) {
                zeros++;
            } else {
                ones++;
            }
        }

        // Step 2: Iterate through the sandwiches and "serve" them.
        for(int sandwich : sandwiches) {
            // If the top sandwich is circular (0)
            if(sandwich == 0) {
                // If no students are left who want a circular sandwich, the rest are stuck.
                if(zeros == 0) {
                    break;
                }
                // Otherwise, a student takes it.
                zeros--;
            } 
            // If the top sandwich is square (1)
            else {
                // If no students are left who want a square sandwich, the rest are stuck.
                if(ones == 0) {
                    break;
                }
                // Otherwise, a student takes it.
                ones--;
            }
        }
        
        // The remaining counts represent the students who were unable to get their preferred sandwich.
        return zeros + ones;
    }
}

/*
 * Explanation of the Commented-Out Queue Method (Simulation Approach)
 * --------------------------------------------------------------------
 * The commented-out code is a direct simulation of the problem, which is intuitive
 * but less efficient.
 *
 * How it works:
 * 1.  A `Deque` (acting as a queue) is created to represent the line of students.
 * 2.  The outer `for` loop iterates through each sandwich in the stack.
 * 3.  The inner `while` loop checks the student at the front of the line. If that
 * student does NOT want the current top sandwich, they are moved to the back
 * of the queue (`queue.poll()` followed by `queue.offer()`).
 * 4.  This rotation continues until a student who wants the sandwich is at the front,
 * OR an entire rotation of the remaining students occurs without anyone taking
 * the sandwich. The `j > n` condition checks for this deadlock.
 * 5.  If a student takes the sandwich, they are removed (`queue.poll()`) and the
 * count of remaining students (`n`) is decremented.
 *
 * Why it's less optimal:
 * In the worst-case scenario (e.g., students=[0,0,1], sandwiches=[1,1,1]), the
 * inner `while` loop may have to rotate through almost all N students for each
 * sandwich. This results in a time complexity of O(N^2), which is much slower
 * than the O(N) counting method.
 */