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
    // LeetCode Question: 234. Palindrome Linked List
    public boolean isPalindrome(ListNode head) {
        
        // --- Step 1: Find the end of the first half of the list. ---
        // We use two pointers, a slow one and a fast one.
        ListNode slow = head;
        ListNode fast = head.next; // Starting fast at head.next helps handle both even and odd length lists cleanly.
        
        // When this loop finishes, 'slow' will be at the last node of the first half.
        while (fast != null && fast.next != null) {
            slow = slow.next;      // Moves one step
            fast = fast.next.next; // Moves two steps
        }

        // --- Step 2: Reverse the second half of the list in-place. ---
        // 'curr' will start at the head of the second half.
        ListNode prev = null;
        ListNode curr = slow.next;
        ListNode next = null;
        
        // Standard iterative reversal algorithm.
        while (curr != null) {
            next = curr.next;       // Store the next node
            curr.next = prev;       // Reverse the current node's pointer
            prev = curr;            // Move prev one step forward
            curr = next;            // Move curr one step forward
        }
        
        // At this point, 'prev' is the new head of the reversed second half.
        // We link the first half to this reversed second half.
        slow.next = prev;

        // --- Step 3: Compare the first half with the reversed second half. ---
        // 'curr' now points to the head of the reversed second half.
        curr = slow.next;
        // 'prev' is reused to point to the head of the original list (the first half).
        prev = head;
        
        // Iterate and compare nodes from both halves.
        while (curr != null) {
            // If at any point the values do not match, it's not a palindrome.
            if (curr.val != prev.val) {
                return false;
            }
            // Move both pointers forward.
            curr = curr.next;
            prev = prev.next;
        }
        
        // If the loop completes without finding any mismatches, the list is a palindrome.
        return true;
    }
}