package Strings.Medium;

/**
 * LeetCode Problem: 71. Simplify Path
 *
 * Problem Description:
 * Given a string path, which is an absolute path (starting with a '/'),
 * simplify it to its canonical form.
 *
 * In a Unix-style file system:
 * - A period '.' refers to the current directory.
 * - A double period '..' refers to the directory up a level.
 * - Multiple slashes '//' are treated as a single slash '/'.
 * - The canonical path must start with a single slash '/' and not have a trailing slash.
 *
 * Example:
 * Input: path = "/a/./b/../../c/"
 * Output: "/a/c"
 */
class Solution {
    public String simplifyPath(String path) {
        // --- Step 1: Initialize Data Structures ---
        // `str` will temporarily build up each path component (e.g., "home", ".", "..").
        StringBuilder str = new StringBuilder();
        // `deque` will act as a stack to store the valid directory names.
        // We use a Deque because it provides efficient stack operations (push/pop).
        Deque<String> deque = new ArrayDeque<>();

        // --- Step 2: Manually Parse the Path String ---
        // We iterate through the path, character by character, to identify components.
        // The loop starts at 1 to skip the initial '/'. It goes to path.length()
        // to handle the last component correctly using a virtual slash at the end.
        for(int i = 0; i <= path.length(); i++) {
            char c;
            // This clever trick treats the end of the string as if it were a final slash.
            // This ensures the last component (e.g., "c" in "/a/b/c") gets processed.
            if(i == path.length()) {
                c = '/';
            } else {
                c = path.charAt(i);
            }

            // When we find a slash, it's time to process the component we've built in `str`.
            if(c == '/') {
                // Only process if we have actually built a component (to handle multiple slashes like "//").
                if(str.length() > 0) {
                    // Case 1: The component is a single dot '.', which means "current directory".
                    if(str.length() == 1 && str.charAt(0) == '.') {
                        // We do nothing and just ignore it.
                    }
                    // Case 2: The component is a double dot '..', which means "go up one directory".
                    else if(str.length() == 2 && str.charAt(0) == '.' && str.charAt(1) == '.') {
                        // We go up by popping the last valid directory from our stack.
                        // We must check if the deque is not empty to avoid errors on paths like "/../".
                        if(!deque.isEmpty()) {
                            deque.pop(); // pop() removes from the head (top of the stack).
                        }
                    } else {
                        // Case 3: The component is a valid directory name.
                        // We push it onto our stack. push() adds to the head.
                        deque.push(str.toString());
                    }
                    // After processing, we clear the StringBuilder to get ready for the next component.
                    str.setLength(0);
                }
            } else {
                // If the character is not a slash, append it to our current component string.
                str.append(c);
            }
        }

        // --- Step 3: Reconstruct the Canonical Path from the Stack ---
        // Clear the StringBuilder so we can reuse it to build the final result.
        str.setLength(0);

        // We need to build the path from the "bottom" of the stack to the "top".
        // Example: For path "/a/b/c", the deque will be [c, b, a] (head is 'c').
        // A descending iterator goes from tail to head, so it will correctly visit "a", then "b", then "c".
        Iterator<String> reverseDeque = deque.descendingIterator();
        while(reverseDeque.hasNext()) {
            str.append("/"); // Prepend each component with a slash.
            str.append(reverseDeque.next());
        }

        // --- Step 4: Handle the Edge Case of an Empty Path ---
        // If the final path is empty (e.g., from input "/../"), the result should be "/".
        if(str.length() == 0) {
            return "/";
        }

        return str.toString();
    }
}