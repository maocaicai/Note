public class 剑指46 {
    /**
     * 剑指 Offer 46. 把数字翻译成字符串https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
     */
    /**
     * 本题本质上就是跳台阶问题，需要透过题目看本质，dp[i]表示前i个数字有多少种翻译方法，当前位置f(i)就是f(i-1)+f(i-2)，也就是·转变成跳台阶问题
     * 但是需要先判断一下i-1和i这俩组合的数字是否符合要求，不符合要求直接延续f(i-1)的翻译方法
     * 并且本题f(i)只和f(i-1)/f(i-2)有关，所以可以优化空间，只用三个数就能操作
     * */

    /**
     * 常规dp
     */
    class Solution {
        public int translateNum(int num) {
            if (num == 0) return 1;
            int len = 0, tem = num;
            while (tem != 0) {
                tem /= 10;
                len ++;
            }
            int[] dp = new int[len];dp[0] = 1;
            String nums = String.valueOf(num);
            for (int i = 1; i < nums.length(); i ++) {
                int l = nums.charAt(i - 1) - '0';
                int r = nums.charAt(i) - '0';
                int n = l * 10 + r;
                if (l != 0 && n < 26) {
                    if (i - 2 == -1) {
                        dp[i] = dp[i - 1] + 1;
                    }else dp[i] = dp[i - 1] + dp[i - 2];
                }else dp[i] = dp[i - 1];
            }
            return dp[dp.length - 1];
        }
    }

    /**
     * 本质上其实做出来普通版，优化版就好说了，面试的时候就直接做普通的就可以
     */
    /**
     * 优化空间dp
     */
    class Solution2 {
        public int translateNum(int num) {
            if (num == 0) return 1;
            int len = 0, tem = num;
            while (tem != 0) {
                tem /= 10;
                len ++;
            }
            int l = 1, m = 1, r = 1;
            String nums = String.valueOf(num);
            for (int i = 1; i < nums.length(); i ++) {
                int a = nums.charAt(i - 1) - '0';
                int b = nums.charAt(i) - '0';
                int n = a * 10 + b;
                if (a != 0 && n < 26) {
                    if (i - 2 == -1) {
                        r = m + 1;
                    }else r = l + m;
                }else r = m;
                l = m;m = r;
            }
            return r;
        }
    }

}
