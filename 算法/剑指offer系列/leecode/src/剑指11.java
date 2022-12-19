public class 剑指11 {
    /**
     * 剑指 Offer 11. 旋转数组的最小数字/154. 寻找旋转排序数组中的最小值 II
     * 本题是使用二分法求解，考虑数组中的最后一个元素 xx：在最小值右侧的元素，它们的值一定都小于等于 xx；而在最小值左侧的元素，它们的值一定都大于等于 xx。因此，我们可以根据这一条性质，通过二分查找的方法找出最小值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-by-leetcode-s/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public int minArray(int[] numbers) {
            int l = 0, r = numbers.length - 1;
            while (l <= r) {
                int m = ((r - l) >> 1) + l;
                //中间值小于最右侧值，说明这段中没有最小值，但是m这个点可能是最小值，所以不能r=m-1，只能r=m
                if (numbers[m] < numbers[r]) {
                    r = m;
                    //如果中间值大于最右侧值说明最小值就在这个范围中，肯定不是m这个比r小的位置，所以l=m+1而不是l=m
                }else if (numbers[m] > numbers[r]) {
                    l = m + 1;
                    //如果中间值和最右侧相等，并不能说明这部分是全部相等的，比如111110111111也是符合要求的
                    //因此此时需要让最右侧-1，每次-1去判断
                }else r -= 1;
            }
            return numbers[l];
        }
    }
}
