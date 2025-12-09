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
    public ListNode removeElements(ListNode head, int val) {
        // LeetCode 203. Remove Linked List Elements
        
        // 1. Create a dummy node to handle the case where the head itself needs to be removed.
        // The dummy node points to the original head.
        ListNode dummy = new ListNode(0, head);
        
        // 2. Initialize a temporary pointer 'temp' starting at the dummy node.
        // 'temp' will always be the node *before* the node we are currently checking.
        ListNode temp = dummy;
        
        // 3. Iterate through the list until 'temp' or the node *after* 'temp' (temp.next) is null.
        while(temp != null && temp.next != null) {
            
            // 4. Check if the value of the next node is the target value 'val'.
            if(temp.next.val == val) {
                
                // If it matches, "skip" the node by setting temp.next to temp.next.next.
                // Crucially, we do *not* move 'temp' forward here because the new 'temp.next'
                // might also contain the target value 'val' and needs to be checked immediately.
                temp.next = temp.next.next;
                
            } else {
                
                // If it doesn't match, move 'temp' one step forward to check the next node in the subsequent iteration.
                temp = temp.next;
            }
        }
        
        // 5. The result is the node immediately following the dummy node,
        // which will be the new head of the modified list.
        return dummy.next;
    }
}