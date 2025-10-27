/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    // LeetCode Question: 138. Copy List with Random Pointer
    public Node copyRandomList(Node head) {
        // Handle the edge case where the original list is empty.
        if (head == null) {
            return null;
        }

        // Use a HashMap to store the mapping from an original node to its new, copied node.
        // This acts as a "directory" to find the copy of any given node.
        Map<Node, Node> map = new HashMap<>();
        
        // --- Pass 1: Create all the new nodes and populate the map ---
        // 'curr' is our pointer to traverse the original list.
        Node curr = head;
        while (curr != null) {
            // Create a new node that is a copy of the current original node.
            Node copy = new Node(curr.val);
            // Put the original node and its new copy into the map.
            map.put(curr, copy);
            // Move to the next node in the original list.
            curr = curr.next;
        }

        // --- Pass 2: Connect the 'next' and 'random' pointers of the new nodes ---
        // Reset 'curr' to the head of the original list for the second traversal.
        curr = head;
        while (curr != null) {
            // Get the copied node corresponding to the current original node.
            Node copy = map.get(curr);
            
            // Set the 'next' pointer for the copied node.
            // We find the copy of the original 'next' node using our map.
            copy.next = map.get(curr.next);
            
            // Set the 'random' pointer for the copied node.
            // We find the copy of the original 'random' node using our map.
            copy.random = map.get(curr.random);
            
            // Move to the next node in the original list.
            curr = curr.next;
        }

        // The head of the deep copy is the value associated with the original head key.
        return map.get(head);
    }
}