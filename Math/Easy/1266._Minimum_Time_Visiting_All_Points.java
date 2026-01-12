class Solution {
    // 1266. Minimum Time Visiting All Points
    public int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;
        int n = points.length - 1;
        for(int i = 0; i < n; i++) {
            // Calculate Chebyshev distance: max of absolute difference in x or y
            res += Math.max(Math.abs(points[i][0] - points[i+1][0]), Math.abs(points[i][1] - points[i+1][1]));
        }
        return res;
    }
}
