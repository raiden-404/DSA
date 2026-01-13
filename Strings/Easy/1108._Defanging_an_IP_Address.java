/*
 * Question Name: Defanging an IP Address
 * Question No: 1108
 * Platform: LeetCode
 * * Problem Statement: 
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 * A defanged IP address replaces every period "." with "[.]".
 */

class Solution {
    public String defangIPaddr(String address) {
        // Use StringBuilder for efficient string manipulation. 
        // Strings in Java are immutable, so standard concatenation (+) in a loop 
        // would result in O(N^2) performance. StringBuilder keeps it O(N).
        StringBuilder res = new StringBuilder();

        // Convert the input string to a char array to iterate through each character
        for (char c : address.toCharArray()) {
            if (c == '.') {
                // If the character is a dot, append the defanged pattern "[.]"
                res.append('[');
                res.append(c);
                res.append(']');
            } else {
                // Otherwise, simply append the digit
                res.append(c);
            }
        }
        
        // Convert the StringBuilder back to a string and return
        return res.toString();
    }
}