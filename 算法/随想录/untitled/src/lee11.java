public class lee11 {
    /**
     * 11. 盛最多水的容器https://leetcode.cn/problems/container-with-most-water/
     */

    class Solution {
        public int maxArea(int[] height) {
            int res = 0, l = 0, r = height.length-1, s = 0;
            if (height.length == 0) return 0;
            while (l < r) {
                s = (r - l)*(height[l] > height[r] ? height[r] : height[l]);
                res = res > s ? res : s;
                if (height[l] < height[r]) {
                    l ++;
                }else {
                    r --;
                }
            }
            return res;
        }
    }
}
