/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    // LeetCode Question: 142. Linked List Cycle II
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        // --- Step 1: Detect a cycle and find the meeting point ---
        // The fast pointer moves twice as fast as the slow pointer.
        // If they meet, it confirms a cycle exists.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Cycle detected! Now we need to find where it starts.
            if (fast == slow) {
                // --- Step 2: Find the start of the cycle ---
                // The key insight: The distance from the head to the cycle's start
                // is the same as the distance from the meeting point to the cycle's start.
                
                // Reset one pointer to the head of the list.
                fast = head;
                
                // Move both pointers one step at a time. The node where they
                // meet again is the starting node of the cycle.
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                // Return the starting node of the cycle.
                return fast;
            }
        }
        
        // If the loop finishes, fast reached the end, so there is no cycle.
        return null;
    }
}