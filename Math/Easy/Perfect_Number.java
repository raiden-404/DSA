// 507. Perfect Number
class Solution {
    public boolean checkPerfectNumber(int num) {
        
        // Edge Case: 1 is not a perfect number.
        // Its only divisor is 1, and the sum of proper divisors (excluding itself) is 0.
        if (num <= 1) return false;
        
        // Start sum at 1 because 1 is always a proper divisor for any num > 1.
        int sum = 1;

        // Loop from 2 up to the square root of num.
        // We use 'i * i <= num' to verify we cover all factors.
        // Optimization: Iterating only to sqrt(num) reduces time from O(N) to O(sqrt(N)).
        for (int i = 2; i * i <= num; i++) {
            
            // Check if 'i' is a divisor
            if (num % i == 0) {
                
                // Add the small divisor
                sum += i;
                
                // Add the corresponding large divisor (the pair)
                // Example: If num=28 and i=4, we add 4. The pair is 28/4 = 7. We add 7 too.
                // We check (i * i != num) to avoid adding the square root twice (e.g., for num=36, i=6).
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        
        // Check if the sum of proper divisors equals the original number
        return sum == num;
    }
}