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
    // LeetCode Question: 206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        // Handle the edge case where the list is empty.
        if (head == null) {
            return null;
        }

        // Initialize three pointers to perform the reversal.
        // 'prev' will store the previous node, which will eventually be the new head.
        ListNode prev = null;
        // 'curr' is the current node we are processing, starting from the head.
        ListNode curr = head;
        // 'next' will temporarily store the next node in the original list.
        ListNode next = null;

        // Iterate through the list until we have processed every node.
        while (curr != null) {
            // 1. Save the next node before we change any pointers.
            //    If we don't do this, we'll lose the rest of the list.
            next = curr.next;

            // 2. Reverse the pointer of the current node.
            //    Make it point to the 'prev' node.
            curr.next = prev;

            // 3. Move the 'prev' pointer one step forward for the next iteration.
            //    'prev' now becomes the current node.
            prev = curr;

            // 4. Move the 'curr' pointer one step forward for the next iteration.
            //    'curr' now becomes the 'next' node we saved in step 1.
            curr = next;
        }

        // When the loop ends, 'curr' will be null, and 'prev' will be at the
        // last node of the original list, which is the new head of the reversed list.
        return prev;
    }
}