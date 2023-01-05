public class 剑指36 {

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表     https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
     * 同426. 将二叉搜索树转化为排序的双向链表    https://leetcode.cn/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
     */

    /**
     * 本题关键点，也是我刚开始没想通的就是，需要用pre和cur来做，中序遍历，这一步想好怎么做就好说了
     * 我的做法中增加了一个dummy，看起来可能更容易理解？最后返回的dummy.right并且看起来有点乱
     */
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
    class Solution {
        Node pre;
        Node dummy;
        public Node treeToDoublyList(Node root) {
            if (root == null) return root;
            Node head = root;
            while (head.left != null) head = head.left;
            dummy = new Node(-1, null, head);
            /**
             * 这一步之上都是为了把dummy与最小值连起来
             */
            dfs(root);
            /**
             * 最后需要把前后头尾节点连起来
             */
            dummy.right.left = pre;
            pre.right = dummy.right;
            return dummy.right;
        }
        private void dfs(Node root) {
            //当前遍历到叶子结点外，直接返回
            if (root == null) return;
            //pre是空，说明是第一次存放，直接=dummy
            if (pre == null) pre = dummy;
            //中序遍历
            dfs(root.left);
            //每次执行都将前后连起来
            pre.right = root;
            root.left = pre;
            pre = root;
            dfs(root.right);
        }
    }
}
