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
    /**
     * LeetCode Question: 25. Reverse Nodes in k-Group
     *
     * Approach:
     * The problem is solved by iterating through the linked list and reversing chunks of size 'k'.
     * The key is to manage the connections between the already processed part of the list,
     * the group being reversed, and the rest of the list.
     *
     * 1.  **Dummy Head**: A dummy node is used at the start to simplify edge cases,
     * especially when reversing the very first group.
     * 2.  **Group Identification**: Before reversing, we first check if there are at least 'k'
     * nodes remaining. We do this by moving a pointer 'k' steps forward.
     * 3.  **In-Place Reversal**: If a full group is found, we reverse it in-place using
     * standard iterative reversal logic.
     * 4.  **Re-linking**: After a group is reversed, we carefully re-link the surrounding
     * nodes:
     * - The tail of the *previous* group must point to the *new head* of the current group.
     * - The *new tail* of the current group must point to the *start* of the next group.
     * 5.  **Iteration**: We then move our main pointer to the end of the just-processed
     * group and repeat the process.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // Edge Case: No need to reverse if the list is empty or k is 1.
        if (head == null || k == 1) {
            return head;
        }

        // The dummy node helps manage the head of the list easily.
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 'groupPrev' is the last node of the section *before* the group we are about to reverse.
        // It acts as our anchor for re-linking.
        ListNode groupPrev = dummyHead;

        while (true) {
            
            // --- Step 1: Find the k-th node to identify the end of the group ---
            ListNode kthNode = groupPrev;
            for (int i = 0; i < k; i++) {
                kthNode = kthNode.next;
                // If we find null, it means we have fewer than k nodes left.
                // The remaining part should not be reversed, so we are done.
                if (kthNode == null) {
                    return dummyHead.next;
                }
            }
            
            // --- Step 2: Reverse the k-group ---
            // 'groupStart' is the first node of our current group. After reversal,
            // it will become the tail of this group.
            ListNode groupStart = groupPrev.next;
            // 'nextGroupStart' is the node right after our current group. The reversed
            // group's new tail will point here.
            ListNode nextGroupStart = kthNode.next;

            // Standard iterative reversal for the k nodes in the group.
            ListNode prevNode = null;
            ListNode curr = groupStart;
            for (int i = 0; i < k; i++) {
                ListNode nextNode = curr.next;
                curr.next = prevNode;
                prevNode = curr;
                curr = nextNode;
            }

            // --- Step 3: Re-link the surrounding parts of the list ---
            // 'groupPrev' (from the previous section) now connects to the new head of our group.
            // The new head is 'kthNode' (which was the tail before reversal).
            groupPrev.next = kthNode;
            
            // The new tail of our group ('groupStart') now connects to the start of the next section.
            groupStart.next = nextGroupStart;

            // --- Step 4: Prepare for the next iteration ---
            // The tail of the group we just processed ('groupStart') becomes the 'groupPrev'
            // for the next potential group.
            groupPrev = groupStart;
        }
    }
}