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
    // LeetCode Question: 143. Reorder List
    public void reorderList(ListNode head) {
        // If the list has 0, 1, or 2 nodes, it is already reordered.
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // --- Step 1: Find the middle of the list ---
        // 'slow' will end up at the last node of the first half.
        // 'fast' is used to traverse the list at double the speed.
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // --- Step 2: Reverse the second half of the list ---
        // 'curr' starts at the head of the second half (the node after 'slow').
        ListNode prev = null;
        ListNode curr = slow.next;
        ListNode next = null;
        while (curr != null) {
            // Standard iterative list reversal logic.
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // At this point, 'prev' is the new head of the reversed second half.
        // We link the first half to this new reversed second half.
        // Example: 1->2->3->4->5 becomes 1->2->3->5->4
        slow.next = prev;

        // --- Step 3: Merge the two halves ---
        // We will now interleave the nodes.
        // 'slow' is reused as the pointer for the first half, starting from the head.
        // 'fast' is reused as the pointer for the end of the first half, which is our separator.
        fast = slow; // 'fast' now points to the last node of the first half (e.g., node 3).
        slow = head; // 'slow' is reset to the beginning (e.g., node 1).
        
        ListNode temp = null;
        ListNode temp2 = null;

        // Loop until the second half is fully merged into the first.
        // The condition 'slow != fast' ensures we stop before the two halves cross.
        while (fast != null && fast.next != null && slow != fast) {
            // 1. Save the original next of the current node in the first half.
            temp = slow.next;
            
            // 2. Link the current first-half node to the head of the second half.
            slow.next = fast.next;
            
            // 3. Save the rest of the second half.
            temp2 = slow.next.next;
            
            // 4. Link the newly attached node back to the rest of the first half.
            slow.next.next = temp;
            
            // 5. Update the second half to point to its remainder.
            fast.next = temp2;
            
            // 6. Move the 'slow' pointer two steps forward to the next node in the original first half.
            slow = slow.next.next;
        }
    }
}