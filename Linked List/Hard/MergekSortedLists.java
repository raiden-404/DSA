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
    // LeetCode Question: 23. Merge k Sorted Lists
    public ListNode mergeKLists(ListNode[] lists) {
        // If the input array of lists is empty, there's nothing to merge, so return null.
        if (lists.length == 0) return null;
        
        // Create a dummy head node. This is a common trick to make handling the start of the list simpler.
        // The dummy head's 'next' pointer is initially set to the first list in the array.
        ListNode head = new ListNode(0, lists[0]);
        
        // Declare pointers that will be used inside the loop for the merging process.
        ListNode l1 = null;      // This will point to the current accumulated result list.
        ListNode l2 = null;      // This will point to the next list from the array that needs to be merged.
        ListNode point = null;   // This will act as the "tail" of the result list, used to append the next smallest node.
        
        // This loop starts from the second list (index 1) and merges each one into the accumulated result.
        for (int i = 1; i < lists.length; i++) {
            // Before each new merge, reset the 'point' to the dummy head.
            point = head;
            // 'l1' is set to our current partially-merged list.
            l1 = head.next;
            // 'l2' is set to the next list from the array that we are about to merge.
            l2 = lists[i];
            
            // This 'while' loop performs the actual merge of two lists (l1 and l2).
            // It continues as long as neither list has run out of nodes.
            while (l1 != null && l2 != null) {
                // Compare the values of the current nodes in l1 and l2.
                if (l1.val <= l2.val) {
                    // If the node from l1 is smaller or equal, attach it to our result list.
                    point.next = l1;
                    // Move the l1 pointer to its next node.
                    l1 = l1.next;
                } else {
                    // If the node from l2 is smaller, attach it to our result list instead.
                    point.next = l2;
                    // Move the l2 pointer to its next node.
                    l2 = l2.next;
                }
                // Move the tail pointer ('point') forward to the node that was just added.
                point = point.next;
            }
            
            // After the loop, one of the lists might still have nodes left.
            // If l1 is the one with remaining nodes, append the entire rest of l1 to the result.
            if (l1 != null) {
                point.next = l1;
            }
            // If l2 is the one with remaining nodes, append the entire rest of l2 to the result.
            if (l2 != null) {
                point.next = l2;
            }
            // At the end of this 'for' loop iteration, 'head.next' now points to the new, longer merged list.
        }

        // After all lists have been merged, return the final result, which starts right after our dummy head.
        return head.next;
    }
    
    /*
     * --- Inefficiency and Optimal Technique ---
     *
     * The main problem with this code is that the merged list (`l1`) gets longer and longer.
     * With each new list we merge, we have to traverse this increasingly long `l1` again. This repeated
     * work is slow. The best way to solve this is with a **Min-Heap (PriorityQueue in Java)**,
     * which can always give you the absolute smallest node from all `k` lists in O(log k) time.
     */
}