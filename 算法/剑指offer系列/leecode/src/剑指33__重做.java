public class 剑指33__重做 {
    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列   https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
     */

    /**
     * 递归分治
     *
     *     根据二叉搜索树的定义，可以通过递归，判断所有子树的 正确性 （即其后序遍历是否满足二叉搜索树的定义） ，若所有子树都正确，则此序列为二叉搜索树的后序遍历。
     *
     * 递归解析：
     *
     *     终止条件： 当 i≥ji≥j ，说明此子树节点数量 ≤1≤1 ，无需判别正确性，因此直接返回 truetrue ；
     *     递推工作：
     *         划分左右子树： 遍历后序遍历的 [i,j][i,j] 区间元素，寻找 第一个大于根节点 的节点，索引记为 mm 。此时，可划分出左子树区间 [i,m−1][i,m−1] 、右子树区间 [m,j−1][m,j−1] 、根节点索引 jj 。
     *         判断是否为二叉搜索树：
     *             左子树区间 [i,m−1][i,m−1] 内的所有节点都应 << postorder[j]postorder[j] 。而第 1.划分左右子树 步骤已经保证左子树区间的正确性，因此只需要判断右子树区间即可。
     *             右子树区间 [m,j−1][m,j−1] 内的所有节点都应 >> postorder[j]postorder[j] 。实现方式为遍历，当遇到 ≤postorder[j]≤postorder[j] 的节点则跳出；则可通过 p=jp=j 判断是否为二叉搜索树。
     *     返回值： 所有子树都需正确才可判定正确，因此使用 与逻辑符 &&&& 连接。
     *         p=jp=j ： 判断 此树 是否正确。
     *         recur(i,m−1)recur(i,m−1) ： 判断 此树的左子树 是否正确。
     *         recur(m,j−1)recur(m,j−1) ： 判断 此树的右子树 是否正确。
     *
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Solution {
        public boolean verifyPostorder(int[] postorder) {
            return dfs(postorder, 0, postorder.length - 1);
        }
        private boolean dfs(int[] postorder, int l, int r) {
            if (l >= r) return true;
            //从左边界向中间遍历遇到比右边界大的中止遍历，即这是当前根节点的左右子树边界位置
            int i = l;
            while (postorder[i] < postorder[r]) i ++;
            //m记录当前位置
            int m = i;
            //继续遍历，右边所有值必须比右边界（当前根节点）都大
            while (postorder[i] > postorder[r]) i ++;
            //最后要比较i和r的值，只有相等才是正确的，然后dfs对其他所有子树遍历
            return i == r && dfs(postorder, l, m - 1) && dfs(postorder, m, r - 1);
        }
    }
}
