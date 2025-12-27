class Solution {
    public String reverseStr(String s, int k) {
        // Convert to char array once (Space: O(N))
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Jump 2k steps at a time
        for (int i = 0; i < n; i += 2 * k) {
            // Determine the range to reverse
            // Start is i, End is min(i + k - 1, end of string)
            int start = i;
            int end = Math.min(i + k - 1, n - 1);

            // Standard Two-Pointer swap logic
            while (start < end) {
                char temp = arr[start];
                arr[start++] = arr[end];
                arr[end--] = temp;
            }
        }
        
        return new String(arr);
    }
}