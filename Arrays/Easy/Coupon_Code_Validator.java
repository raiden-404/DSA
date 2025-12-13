// 3606. Coupon Code Validator
class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        
        // Main result list
        List<String> res = new ArrayList<>();
        
        // Temporary lists for each specific business line
        // Using separate lists allows us to sort them independently later
        List<String> electronics = new ArrayList<>();
        List<String> grocery = new ArrayList<>();
        List<String> pharmacy = new ArrayList<>();
        List<String> restaurant = new ArrayList<>();

        int n = code.length;

        // Iterate through all coupons
        for(int i=0; i<n; i++) {
            // Step 1: Filter - Proceed only if the coupon is currently active
            if(isActive[i]) {
                // Step 2: Validate - Check if code syntax is valid (alphanumeric or underscore)
                if(isValid(code[i])) {
                    // Step 3: Categorize - Add to the correct bucket based on business line
                    switch(businessLine[i]) {
                        case "electronics" -> electronics.add(code[i]);
                        case "grocery" -> grocery.add(code[i]);
                        case "pharmacy" -> pharmacy.add(code[i]);
                        case "restaurant" -> restaurant.add(code[i]);
                    }
                }
            }
        }

        // Step 4: Sort & Append - Sort each category lexicographically and append in order
        
        // Electronics
        Collections.sort(electronics);
        res.addAll(electronics);

        // Grocery
        Collections.sort(grocery);
        res.addAll(grocery);

        // Pharmacy
        Collections.sort(pharmacy);
        res.addAll(pharmacy);

        // Restaurant
        Collections.sort(restaurant);
        res.addAll(restaurant);

        return res;
    }

    // Helper method to validate coupon syntax
    public boolean isValid(String s) {
        if(s.length() == 0) return false;
        
        // Iterating over char array is readable and standard for string validation
        for(char c : s.toCharArray()) {
            // Logic: Must be Lowercase OR Uppercase OR Digit OR Underscore
            if(!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_')) {
                return false;
            }
        }
        return true;
    }
}