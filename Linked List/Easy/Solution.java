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
    // LeetCode Question: 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        // --- Edge Case ---
        // If the list is empty, it cannot have a cycle.
        if (head == null) {
            return false;
        }

        // --- Floyd's Tortoise and Hare Algorithm ---
        // Initialize two pointers. Think of one as a slow-moving tortoise
        // and the other as a fast-moving hare.
        ListNode slow = head; // The "tortoise" moves one step at a time.
        ListNode fast = head; // The "hare" moves two steps at a time.

        // Loop as long as the fast pointer and the node ahead of it are not null.
        // We must check fast.next because we will try to access fast.next.next.
        // If fast.next is null, fast.next.next would cause a NullPointerException.
        while (fast != null && fast.next != null) {
            // Move the slow pointer one step forward.
            slow = slow.next;
            // Move the fast pointer two steps forward.
            fast = fast.next.next;

            // --- Cycle Detection ---
            // If the slow and fast pointers ever meet at the same node,
            // it proves that there is a cycle in the list.
            // The fast pointer has "lapped" the slow pointer.
            if (slow == fast) {
                return true;
            }
        }

        // If the loop completes, it means the fast pointer reached the end of the list (null).
        // Therefore, there is no cycle.
        return false;
    }
}