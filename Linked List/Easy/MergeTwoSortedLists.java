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
    // LeetCode Question: 21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        // --- Base Cases for Recursion ---
        // If the first list is empty, there's nothing to merge.
        // The result is simply the second list.
        if (list1 == null) {
            return list2;
        }
        // If the second list is empty, the result is the first list.
        if (list2 == null) {
            return list1;
        }

        // --- Recursive Step ---
        // Compare the values of the current nodes from both lists.
        if (list1.val < list2.val) {
            // If list1's current node has a smaller value, it should come first
            // in the merged list.
            
            // We keep the current list1 node and recursively call mergeTwoLists
            // to figure out what its 'next' pointer should point to.
            // The next node will be the result of merging the rest of list1 (list1.next)
            // with all of list2.
            list1.next = mergeTwoLists(list1.next, list2);
            
            // Return the current list1 node, which is the head of this merged sub-problem.
            return list1;
        } else {
            // If list2's current node has a smaller or equal value, it should come first.
            
            // We keep the current list2 node and recursively determine its 'next' pointer.
            // The next node will be the result of merging all of list1
            // with the rest of list2 (list2.next).
            list2.next = mergeTwoLists(list1, list2.next);
            
            // Return the current list2 node as the head of this merged sub-problem.
            return list2;
        }
    }
}