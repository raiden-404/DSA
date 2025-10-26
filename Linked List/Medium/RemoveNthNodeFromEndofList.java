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
    // LeetCode Question: 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // --- The Dummy Node Technique ---
        // Create a 'dummy' node that points to the head of the list.
        // This is a powerful trick to simplify edge cases, such as when
        // we need to remove the actual head of the list.
        ListNode dummyNode = new ListNode(0, head);

        // --- Two Pointer Initialization ---
        // 'slow' starts at the dummy node. It will eventually point to the node
        // *before* the one we want to delete.
        ListNode slow = dummyNode;
        // 'fast' starts at the actual head. It will be used to create a gap.
        ListNode fast = head;

        // --- Create a Gap Between Pointers ---
        // Move the 'fast' pointer 'n' steps into the list.
        // This creates a fixed-size gap of 'n' nodes between the 'slow' and 'fast' pointers.
        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }

        // --- Move Pointers to the End ---
        // Now, move both 'fast' and 'slow' pointers one step at a time
        // until the 'fast' pointer reaches the end of the list (null).
        // Because of the initial gap, when 'fast' is at the end, 'slow' will be
        // perfectly positioned at the node just before the nth node from the end.
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // --- Remove the Target Node ---
        // 'slow' is now at the (n+1)th node from the end.
        // To remove the nth node, we bypass it by setting the 'next' of the slow pointer
        // to the node *after* the target node (slow.next.next).
        slow.next = slow.next.next;

        // Return the new head of the list, which is the node after our dummy node.
        return dummyNode.next;
    }
}