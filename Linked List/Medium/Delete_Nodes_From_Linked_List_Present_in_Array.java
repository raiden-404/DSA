// LeetCode Problem 3217: Delete Nodes From Linked List Present in an Array

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
     * Removes nodes from a linked list whose values are present in a given integer array.
     *
     * This optimal solution uses a HashSet for efficient lookups. First, all numbers
     * from the `nums` array are added to a HashSet, which provides average O(1) time
     * complexity for checking the existence of a number.
     *
     * Then, the linked list is traversed using a two-pointer approach with a dummy node.
     * The dummy node simplifies deletion, especially if the head node needs to be removed.
     *
     * @param nums An array of integers to be deleted from the linked list.
     * @param head The head of the singly-linked list.
     * @return The head of the modified linked list.
     */
    public ListNode modifiedList(int[] nums, ListNode head) {
        // Step 1: Store all numbers to be deleted in a HashSet for O(1) average time lookups.
        // A HashSet is slightly more semantically correct than a HashMap here, as we only need keys.
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Step 2: Use a dummy node to simplify deletion logic, especially for the head.
        // The dummy node's `next` pointer will point to the eventual head of the result list.
        ListNode dummy = new ListNode(0, head);
        
        // `prev` points to the last known "good" node that will be kept.
        ListNode prev = dummy;
        // `curr` is the node we are currently inspecting.
        ListNode curr = head;

        // Step 3: Traverse the list and delete nodes if their value is in the set.
        while (curr != null) {
            // Check if the current node's value is one that needs to be deleted.
            if (set.contains(curr.val)) {
                // If so, bypass the current node by linking `prev` to `curr`'s next node.
                prev.next = curr.next;
                // `prev` does not advance because the new `curr.next` might also need deletion.
            } else {
                // If `curr` is a node to keep, then we advance `prev` to `curr`.
                prev = curr;
            }
            // Always advance `curr` to the next node in the original list to continue the traversal.
            curr = curr.next;
        }

        // The modified list starts at the node following the dummy node.
        return dummy.next;
    }
}