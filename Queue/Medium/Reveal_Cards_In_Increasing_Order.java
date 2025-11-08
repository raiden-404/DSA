/**
 * Author: User
 * LeetCode Problem: 950. Reveal Cards In Increasing Order
 *
 * Solution Explanation:
 * * The problem asks us to find the initial ordering of a deck of cards
 * so that when we reveal them one by one (revealing the top, moving the
 * next top card to the bottom), they are revealed in increasing order.
 *
 * This solution simulates the process in reverse.
 * 1.  We know the cards must be revealed in sorted order (e.g., 2, 3, 5, 7).
 * 2.  The smallest card (2) must be the first one we reveal, so it must be at the top (index 0)
 * of our final, ordered deck.
 * 3.  We use a Queue to keep track of the *indices* (positions) in our result array.
 * This queue simulates the "reveal top, move next to bottom" process.
 * 4.  We sort the deck (`deck`) to know the order of reveal (e.g., [2, 3, 5, 7]).
 * 5.  We place the smallest card (`deck[0]`) at `res[0]`.
 * 6.  We populate a queue with the remaining indices (`1, 2, 3, ...`).
 * 7.  Then, for each subsequent card in our sorted deck (3, 5, 7...):
 * a. We simulate the "move to bottom" step: `queue.poll()` (take index from front)
 * and `queue.offer()` (put it at the back).
 * b. We simulate the "reveal" step: `queue.poll()` gives us the *next*
 * available index (position) in our result array.
 * c. We place the current card (e.g., 3) into that position in the `res` array.
 * 8.  We repeat this until all cards are placed. The `res` array now holds the
 * correct initial order.
 */
class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        
        // 1. Sort the deck. This gives us the order in which cards
        //    must be revealed. This is the O(n log n) bottleneck.
        Arrays.sort(deck);
        
        // 2. Create the result array to store the final deck order.
        int n = deck.length;
        int[] res = new int[n];
        
        // 3. Create a queue to simulate the positions (indices) of the cards.
        Queue<Integer> queue = new ArrayDeque<>();
        
        // 4. Add all indices *except 0* to the queue.
        //    We handle index 0 separately because we know the smallest
        //    card goes there.
        for(int i = 1; i < n; i++) {
            queue.offer(i);
        }
        
        // 5. Place the smallest card (deck[0]) at the first position (res[0]).
        //    This is the first card to be revealed.
        res[0] = deck[0];
        
        // 6. Iterate through the rest of the sorted cards.
        for(int i = 1; i < n; i++) {
            
            // a. Simulate moving the next top card to the bottom:
            //    Take the index from the front...
            int temp = queue.poll();
            //    ...and put it at the back.
            queue.offer(temp);
            
            // b. Get the index for the *next* card to be "revealed".
            //    This is the correct position for deck[i] in the result.
            temp = queue.poll();
            
            // c. Place the current card (deck[i]) into its correct position.
            res[temp] = deck[i];
        }
        
        // 7. Return the re-ordered deck.
        return res;
    }
}