class LRUCache {

    // 1. Use a private inner class for the Node
    // 'static' because it doesn't need access to LRUCache members
    private class Node {
        int key, val;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache; // Map for O(1) access
    private final Node head; // Dummy head (most recently used side)
    private final Node tail; // Dummy tail (least recently used side)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Initialize dummy nodes to avoid null checks
        this.head = new Node(-1, -1); 
        this.tail = new Node(-1, -1);
        
        // Connect head and tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // Check if key exists in the map
        if (!cache.containsKey(key)) {
            return -1;
        }

        // Retrieve the node
        Node node = cache.get(key);
        
        // Move to front (Mark as Recently Used)
        removeNode(node);
        addToHead(node);

        return node.val;
    }

    public void put(int key, int value) {
        // If key exists, update value and move to front
        if (cache.containsKey(key)) {
            Node oldNode = cache.get(key);
            removeNode(oldNode);
        }

        // Create new node and add to head (Most Recently Used)
        Node newNode = new Node(key, value);
        addToHead(newNode);
        cache.put(key, newNode);

        // If over capacity, remove the Least Recently Used (LRU) item
        if (cache.size() > capacity) {
            Node lruNode = tail.prev; // The node right before the dummy tail
            removeNode(lruNode);      // Remove from LinkedList
            cache.remove(lruNode.key); // Remove from Map using the key stored in Node
        }
    }

    // --- Helper Methods ---

    // Helper: Adds a node right after the dummy head
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        
        head.next.prev = node;
        head.next = node;
    }

    // Helper: Removes a node from its current position
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}