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
    // LeetCode Question: 2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Create a dummy head node. This is a common trick to simplify the code,
        // as we don't need a special case for the first node we add to the result.
        ListNode dummyHead = new ListNode(0);
        
        // 'current' will be our pointer to build the new list. It starts at the dummy head.
        ListNode current = dummyHead;
        
        // 'carry' will store the carry-over value from one digit's sum to the next.
        int carry = 0;

        // Loop as long as there are digits in either list OR there is a remaining carry.
        // The 'carry != 0' check is crucial for cases like 99 + 1 = 100,
        // where we need an extra node for the final carry.
        while (l1 != null || l2 != null || carry != 0) {

            // Calculate the sum for the current place value.
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            
            // The new carry for the next iteration is the tens digit of the sum.
            // e.g., if sum is 15, carry becomes 1.
            carry = sum / 10;
            
            // Create the new node and attach it to our result list.
            // The value for our new node is the ones digit of the sum.
            // e.g., if sum is 15, the new node's value is 5.
            current.next = new ListNode(sum % 10);
            
            // Move the 'current' pointer forward to the newly created node.
            current = current.next;

            // Advance l1 and l2 to their next nodes if they are not null.
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        
        // The result list starts after our dummy head, so we return dummyHead.next.
        return dummyHead.next;
    }
}