class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        
        // Use a HashMap to store the items from the first list.
        // Key: Restaurant Name (String)
        // Value: Index of that restaurant (Integer)
        Map<String, Integer> map = new HashMap<>();
        
        // This list will store our result strings. We use an ArrayList
        // because we don't know the size of the result array in advance.
        ArrayList<String> res = new ArrayList<>();
        
        // Initialize 'min' to the largest possible integer value.
        // This variable will track the minimum index sum found so far.
        int min = Integer.MAX_VALUE;
        
        // === First Pass: Populate the HashMap ===
        // Iterate through the first list (list1) to build the map.
        // Note: You are using 1-based indexing (i=1 to length). 
        // This is unconventional but works.
        for (int i = 1; i <= list1.length; i++) {
            // Store the restaurant name and its 1-based index (i).
            // A more common approach is storing the 0-based index (i-1).
            map.put(list1[i - 1], i);
        }

        // === Second Pass: Find the Minimum Index Sum ===
        // Iterate through the second list (list2).
        for (int i = 1; i <= list2.length; i++) {
            
            // Get the 0-based index name from list2.
            String currentRestaurant = list2[i - 1];

            // Calculate the potential index sum.
            // map.getOrDefault(key, default):
            //   - If currentRestaurant is in the map, it returns its stored value (list1's 1-based index).
            //   - If not, it returns a very large number (999999) to ensure this 'val'
            //     is not mistakenly considered a minimum.
            // 'i' is the 1-based index from list2.
            // So, val = (list1_index + 1) + (list2_index + 1)
            int val = map.getOrDefault(currentRestaurant, 999999) + i;

            // Check if this sum is equal to our current minimum.
            if (val == min) {
                // If it's equal, it's another valid answer, so add it to the result list.
                res.add(currentRestaurant);
            } 
            // Check if this sum is *less than* our current minimum.
            else if (val < min) {
                // This is a new, smaller minimum sum.
                
                // 1. Clear the previous result list, as all items in it
                //    are now invalid (their sum was larger).
                res = new ArrayList<>();
                
                // 2. Add the current restaurant as the first item for this new minimum.
                res.add(currentRestaurant);
                
                // 3. Update 'min' to this new, smaller value.
                min = val;
            }
        }
        
        // === Final Step: Convert ArrayList to String[] ===
        // The problem requires returning a String array, not an ArrayList.
        
        int i = 0; // Index for the new array
        String[] result = new String[res.size()]; // Create the final array of the correct size
        
        // Copy each element from the ArrayList 'res' into the 'result' array.
        for (String s : res) {
            result[i] = s;
            i++;
        }
        
        // Return the final array.
        return result;
    }
}