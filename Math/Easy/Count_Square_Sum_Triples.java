class Solution {
    public int countTriples(int n) {
        // LeetCode Problem 1925: Count Square Sum Triples
        int res = 0;
        // Iterate through all possible values for 'c' (the largest side, hypotenuse)
        // A Pythagorean triple (a, b, c) requires a^2 + b^2 = c^2.
        // The smallest such triple is (3, 4, 5), so c must be at least 5.
        for(int i = n; i >= 5; i--) {
            int c = i * i;
            
            // Use two pointers for 'a' (left) and 'b' (right)
            // 'a' starts from the smallest possible value (3).
            // 'b' starts from a value just below 'c' (i-1).
            int left = 3; 
            int right = i - 1;
            
            while(left < right) {
                int sumOfSquares = (left * left) + (right * right);
                
                if(sumOfSquares == c) {
                    // Found a triple (left, right, i) where left^2 + right^2 = i^2.
                    // We must count both (a, b, c) and (b, a, c), so we add 2.
                    res += 2;
                    
                    // Crucial: Increment 'left' and decrement 'right' to look for other pairs 
                    // that might have the same 'c'. Since the sides must be distinct and ordered 
                    // (left < right), we need to check other combinations.
                    left++;
                    right--;
                    
                } else if(sumOfSquares > c) {
                    // Sum is too large, need a smaller 'b' (right)
                    right--;
                } else { // sumOfSquares < c
                    // Sum is too small, need a larger 'a' (left)
                    left++;
                }
            }
        }
        return res;
    }
}