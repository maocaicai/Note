public class 剑指12 {
    /**
     * 剑指 Offer 12. 矩阵中的路径:https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/
     * 与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
     */
    /**
     * 本题使用了回溯，要用dfs解决，主要解决思路和岛屿面积类似
     */

    class Solution {
        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i ++) {
                for (int j = 0; j < board[0].length; j ++) {
                    /**
                     * for循环，对每个地方都进行一次dfs，如果碰到了符合条件的，终止两层循环，直接返回true；
                     */
                    if (dfs(board, word, i, j, 0)) return true;
                }
            }
            return false;
        }
        private boolean dfs(char[][] board, String word, int i, int j, int index) {
            /**
             * 下面这两步可以合并到一步上
             */
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
            if (word.charAt(index) != board[i][j]) return false;

            if (index == word.length() - 1) return true;
            /**
             * 这的回溯是关键，改变当前值为空，这样dfs回当前这个点时会直接终止
             * 这样就减少了一个空间复杂度的数组，节省了空间
             */
            board[i][j] = ' ';
            boolean res =  dfs(board, word, i + 1, j, index + 1) || dfs(board, word, i, j + 1, index + 1) || dfs(board, word, i, j - 1, index + 1) || dfs(board, word, i - 1, j, index + 1);
            board[i][j] = word.charAt(index);
            return res;
        }
    }
}
