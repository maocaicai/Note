import java.util.ArrayList;
import java.util.List;

public class 剑指34 {
    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径  https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * 同113. 路径总和 II   https://leetcode.cn/problems/path-sum-ii/
     */

    /**
     * 本题 并不算难，但是核心在于，找到的是从根节点到叶子结点，也就是说如果没有到叶子结点是不能符合条件的，这我刚开始出了点问题，其他没什么问题
     */

     public class TreeNode {
          int val;
         TreeNode left;
          TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int target) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            dfs(root, target, res, list);
            return res;
        }
        private void dfs(TreeNode root, int target, List<List<Integer>> res, List<Integer> list) {
            if (root == null) return;
            if (target == root.val && root.left == null && root.right == null) {
                list.add(root.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                return;
            }
            list.add(root.val);
            dfs(root.left, target - root.val, res, list);
            dfs(root.right, target - root.val, res, list);
            list.remove(list.size() - 1);
        }
    }
}
