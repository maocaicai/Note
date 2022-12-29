public class lee11 {
    /**
     * 11. 盛最多水的容器https://leetcode.cn/problems/container-with-most-water/
     * 本题使用了双指针+贪心的算法，本质上很简单，从数组左右边界开始向中间靠拢，哪边值小哪边向内移动一位，每次移动都记录当前最大容量
     * 这样遍历一遍就求出了最大容量，比较简单。
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
