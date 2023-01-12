public class 剑指55 {
    /**
     * 剑指 Offer 55 - II. 平衡二叉树  https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/
     * 与110相同：110. 平衡二叉树    https://leetcode.cn/problems/balanced-binary-tree/
     */
    /**
     * 本题的自顶向下双重递归法最后做出来了，但是并不是最优解，因为自顶向下，每次dfs都要重新计算左右树高，浪费性能
     *
     * 因此本题有第二种自底向上的做法，递归 + 剪枝，剪枝是当左右子树超过1时，返回-1，直接判错
     */



      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
    /**
     * 正常双重递归自顶向下做法
     *
     */
    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;
            boolean a, b;
            int l = dfs(root.left);
            int r = dfs(root.right);
            return Math.abs(r - l) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
        private int dfs(TreeNode root) {
            if (root == null) return 0;
            return Math.max(dfs(root.left), dfs(root.right)) + 1;
        }
    }

    /**
     * 自底向上递归 + 剪枝
     */
    class Solution1 {
        public boolean isBalanced(TreeNode root) {
            return recur(root) != -1;
        }
        private int recur(TreeNode root) {
            if (root == null) return 0;
            int l = recur(root.left);
            if (l == -1) return -1;
            int r = recur(root.right);
            if (r == -1) return -1;
            return Math.abs(r - l) > 1 ? -1 : Math.max(l, r) + 1;
        }
    }
}
