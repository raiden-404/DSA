/*
 * LeetCode Problem 841: Keys and Rooms
 *
 * Problem Description:
 * There are N rooms and you start in room 0. Each room has a list of keys,
 * and each key rooms[i][j] = v opens room v.
 * Return true if and only if you can visit every room.
 *
 * Approach:
 * This problem can be modeled as a graph traversal. Each room is a node,
 * and the keys in a room represent directed edges to other nodes (rooms).
 *
 * We need to determine if all nodes are reachable from the starting node (room 0).
 * This is a classic connectivity problem.
 *
 * We can use either Breadth-First Search (BFS) or Depth-First Search (DFS).
 * This solution implements a BFS approach, which is optimal with
 * Time Complexity: O(N + E) - We visit each room (N) and check each key (E) once.
 * Space Complexity: O(N) - In the worst case, the queue and set will hold N rooms.
 */
class Solution {

    /**
     * Determines if all rooms can be visited starting from room 0 using BFS.
     *
     * @param rooms A list of lists, where rooms[i] is a list of keys (room indices)
     * found in room i.
     * @return true if all rooms are reachable, false otherwise.
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        // --- 1. Initialization ---

        // A Set to efficiently track which rooms we have already visited.
        // Using a Set provides O(1) average time complexity for 'add' and 'contains'.
        // This prevents us from re-visiting rooms or getting into infinite loops.
        Set<Integer> visitedRooms = new HashSet<>();

        // A Queue to manage the order of rooms to visit (standard for BFS).
        // It stores the rooms we have keys for but haven't explored yet.
        Queue<Integer> queue = new ArrayDeque<>();

        // A counter to track the total number of unique rooms we have entered.
        int visitedRoomsCount = 0;

        // --- 2. Start Traversal ---

        // We always start at room 0, as it's the only unlocked room.
        visitedRooms.add(0);
        queue.offer(0); // Add room 0 to the queue to be processed.

        // --- 3. BFS Loop ---

        // Continue processing as long as there are rooms in our queue to visit.
        while (!queue.isEmpty()) {

            // Get the next room to visit from the front of the queue.
            int currentRoomIndex = queue.poll();

            // Increment our count of unique visited rooms.
            visitedRoomsCount++;

            // Get the list of keys (which are just indices for other rooms)
            // available in the current room.
            List<Integer> keysInCurrentRoom = rooms.get(currentRoomIndex);

            // Process all the keys found in this room.
            for (int nextRoomIndex : keysInCurrentRoom) {

                // Check if the key opens a room we have *not* visited yet.
                if (!visitedRooms.contains(nextRoomIndex)) {

                    // If it's a new room:
                    // a. Mark it as visited so we don't process it again.
                    visitedRooms.add(nextRoomIndex);

                    // b. Add it to the queue to visit and get keys from it later.
                    queue.offer(nextRoomIndex);
                }
            }
        }

        // --- 4. Final Check ---

        // After the traversal is complete, we check our result.
        // If the number of unique rooms we visited is equal to the
        // total number of rooms, it means we successfully reached every room.
        return rooms.size() == visitedRoomsCount;
    }
}