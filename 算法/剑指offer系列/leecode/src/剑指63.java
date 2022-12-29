public class 剑指63 {
    /**
     * 同121.买卖股票的最佳时机
     * 本题有两种做法，一种是按照动态规划思想，记录当前遍历的位置的最小值，用当前值-最小值，与dp[i - 1]位置比较，哪个大哪个作为dp[i]
     * dp[i]此时的含义为以prices[i] 为结尾的子数组的最大利润
     * 第二种做法就是套模板，设一个二维dp，dp[][0]和dp[][1]分别代表没有扣除当前值和扣除当前值时最大的利润，最终遍历一次后获得二者最大值即为结果
     */
    class Solution {
        public int maxProfit(int[] prices) {
            int cost = Integer.MAX_VALUE, profit = 0;
            for(int price : prices) {
                cost = Math.min(cost, price);
                profit = Math.max(profit, price - cost);
            }
            return profit;
        }
    }
    class Solution2 {
        public int maxProfit(int[] prices) {
            if (prices.length == 0) return 0;
            int[][] dp = new int[prices.length][2];
            dp[0][0] = - prices[0];
            for (int i = 1; i < prices.length; i ++) {
                dp[i][0] = Math.max(dp[i - 1][0], - prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i][0] + prices[i]);
            }
            return dp[dp.length - 1][1];
        }
    }
}
