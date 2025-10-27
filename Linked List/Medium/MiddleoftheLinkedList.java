/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // LeetCode Question: 876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        
        // --- The Tortoise and Hare (Slow and Fast Pointer) Approach ---
        // Initialize two pointers, both starting at the head of the list.
        ListNode slow = head; // 'slow' will move one step at a time.
        ListNode fast = head; // 'fast' will move two steps at a time.

        // Loop until the 'fast' pointer reaches the end of the list.
        // The condition 'fast != null && fast.next != null' is critical:
        // 1. 'fast != null': Handles lists with an even number of nodes.
        // 2. 'fast.next != null': Handles lists with an odd number of nodes and prevents a NullPointerException when accessing 'fast.next.next'.
        while (fast != null && fast.next != null) {
            // Move the slow pointer one step forward.
            slow = slow.next;
            // Move the fast pointer two steps forward.
            fast = fast.next.next;
        }

        // By the time the 'fast' pointer has reached the end, the 'slow' pointer
        // will be positioned at the middle node of the list.
        // - For an odd-length list (e.g., 1->2->3->4->5), fast will land on the last node (5).
        // - For an even-length list (e.g., 1->2->3->4->5->6), fast will become null.
        // In both cases, 'slow' points to the correct middle node (or the second middle node for even lists).
        return slow;
    }
}