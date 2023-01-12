public class 剑指07__重做 {
    /**
     * 剑指 Offer 07. 重建二叉树   https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
     * 与105相同 105. 从前序与中序遍历序列构造二叉树：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     */

    /**
     * 本题的核心其实是边界问题，要弄明白前序和中序遍历中边界的关系，这题就迎刃而解
     * 1. 首先，前序遍历第一个值就是root根节点，以此为初始，dfs递归
     * 2. 根据这个值去中序遍历中找到对应的值的索引index，这个索引之前的位置就是该节点的左节点，之后就是其右节点
     * 3. 接下来就是边界情况了，根据第二步的index和中序遍历记录的前后索引位置中的前位置il，可以求出当前节点左子树的前后索引位置
     * 4. 同理可求出右节点的索引位置，然后dfs去循环即可求出，具体看代码
     */

     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length);
        }
        private TreeNode dfs(int[] preorder, int[] inorder, int pl, int pr, int il, int ir) {
            if (pl > pr || il > ir) return null;
            TreeNode root = new TreeNode(preorder[pl]);
            int index = 0;
            for (int i = 0; i < preorder.length; i ++) {
                if (preorder[pl] == inorder[i]) index = i;
            }
            /**
             * 核心思路，当前pl是root节点，那么pl+1是下一层循环的左节点，index是中序遍历中当前pl值所在的索引位置
             * il是中序遍历的左节点，二者相减index-il即为当前root节点的左节点的左右范围，那么index-il+pl是在整个数组中的索引位置
             * 中序遍历已经有了index位置，index-1的位置就是左节点的右范围，         以上即为当前root节点的左子树求法
             * 当前root节点的右子树同理
             */
            root.left = dfs(preorder, inorder, pl + 1, index - il + pl, il, index - 1);
            root.right = dfs(preorder, inorder, index - il + pl + 1, pr, index + 1, ir);
            return root;
        }
    }
}
