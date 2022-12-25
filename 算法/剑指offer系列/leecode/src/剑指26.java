import javax.swing.tree.TreeNode;

public class 剑指26 {
    /**
     * 剑指 Offer 26. 树的子结构 https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=y7tanut
     * 本题需要将问题拆分一下，首先求的B是A的子结构，因此我们需要对A的每个节点都判断一下与B是否相同，这一步可以通过方法自身去递归调用A的左右子树判断
     * 其次就是对每个A节点及其下的结构与B进行比较，本质就是判断以A和B为根节点的结构中B是否是A的子树
     * 优质题解：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/solution/di-gui-fang-shi-jie-jue-by-sdwwld/
     */

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    class Solution {
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            //A或B为空，直接为false
            if (A == null || B == null) return false;
            /**
             * 首先recur是将当前A,B的每个值对比判断是不是相等
             * 其次对A的左右子树递归去查看，并对每个子树recur一次判断是否A==B
             */
            return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }
        private boolean recur(TreeNode A, TreeNode B) {
            //B为空说明这条路走到底了，返回true
            if (B == null) return true;
            //A为空说明此时B没走到底，但是A走到了，返回false
            //A的值和B不相等，也返回false
            if (A == null || A.val != B.val) return false;
            //递归对每个值都进行比较
            return recur(A.left, B.left) && recur(A.right, B.right);
        }
    }
}
