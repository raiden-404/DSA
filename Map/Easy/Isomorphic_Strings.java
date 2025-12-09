class Solution {
    public boolean isIsomorphic(String s, String t) {
        // LeetCode 205. Isomorphic Strings
        
        // n is the length of the strings (they are guaranteed to be of the same length)
        int n = s.length();
        
        // Two arrays of size 256 (for extended ASCII/char values) used as maps.
        // Array initialization to 0 means no mapping exists yet.
        int[] mapStoT = new int[256]; // Maps character from s to character from t
        int[] mapTtoS = new int[256]; // Maps character from t to character from s (reverse check)

        // --- First Pass: Check mapping from s to t ---
        for(int i = 0; i < n; i++) {
            // char is implicitly cast to its int ASCII value for array indexing.
            int c1 = s.charAt(i);
            int c2 = t.charAt(i);

            // If no mapping exists for c1 yet (mapStoT[c1] == 0)
            if(mapStoT[c1] == 0) {
                // Create the new mapping: c1 maps to c2.
                mapStoT[c1] = c2;
            } else if(mapStoT[c1] != c2) {
                // If a mapping already exists, but it's not to the current c2, it's invalid.
                return false;
            }
        }
        
        // --- Second Pass: Check mapping from t to s (enforces the reverse constraint) ---
        // This pass ensures that two distinct characters in 's' don't map to the same character in 't'.
        for(int i = 0; i < n; i++) {
            int c1 = s.charAt(i); // Character from s (needed for the value being stored)
            int c2 = t.charAt(i); // Character from t (used as the array index)

            // If no reverse mapping exists for c2 yet (mapTtoS[c2] == 0)
            if(mapTtoS[c2] == 0) {
                // Create the new reverse mapping: c2 maps to c1.
                mapTtoS[c2] = c1;
            } else if(mapTtoS[c2] != c1) {
                // If a reverse mapping already exists, but it's not to the current c1, it's invalid.
                // e.g., s="foo", t="bar".
                // i=0: f->b, b->f.
                // i=1: o->a, a->o.
                // i=2: 'o' maps to 'a' (OK from mapStoT), but 'a' is now trying to map to 'o' again. 
                //      If we had s="ab", t="aa", then:
                //      i=0: a->a (mapStoT[a]=a, mapTtoS[a]=a)
                //      i=1: b->a (mapStoT[b]=a). mapTtoS[a] is NOT 0. mapTtoS[a] (which is 'a') != c1 ('b'). returns FALSE. Correct.
                return false;
            }
        }
        
        return true;
    }
}