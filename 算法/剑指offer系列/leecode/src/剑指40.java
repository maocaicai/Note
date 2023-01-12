import java.util.PriorityQueue;

public class 剑指40 {

    /**
     * 剑指 Offer 40. 最小的k个数   https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
     * 本题是排序题，可以有多种做法，我用了三种做法：
     * 1. 快排变种
     * 2. 快排优化
     * 3. 优先队列大顶堆
     *
     * 同    215. 数组中的第K个最大元素    https://leetcode.cn/problems/kth-largest-element-in-an-array/
     */

    /**
     * 快排优化，在判断某些条件时，可以只排序需要的部分，因为输出的只是最小的n个数，不需要排序，所以不用全部排序
     */
    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            QuickSort(arr, 0, arr.length - 1, k);
            int[] res = new int[k];
            for (int i = 0; i < res.length; i ++) {
                res[i] = arr[i];
            }
            return res;
        }
        private void QuickSort(int[] arr, int l, int r, int k) {
            if (l > r) return;
            int m = sort(arr, l, r);
            /**
             * 当前索引=k，说明前k个都排序好了，是最小的k个，直接返回
             * 当前索引>k，说明当前最小的m个数不满足条件，排序当前这部分即可
             * 当前索引<k，说明最小的m个数不够需要的k个数，所以要继续排序
             */
            if(m == k) {
                return;
            }else if (m > k) {
                QuickSort(arr, l, m - 1, k);
            }else QuickSort(arr, m + 1, r, k);
        }

        private int sort(int[] arr, int l, int r) {
            int index = arr[l];
            int ll = l + 1, rr = r;
            while (ll <= rr) {
                while (ll <= r && arr[ll] <= index) ll ++;
                while (rr > l && arr[rr] > index) rr --;
                if (ll < rr) swap(arr, ll, rr);
            }
            swap(arr, l, rr);
            return rr;
        }
        private void swap(int[] arr, int l, int rr) {
            int tem = arr[l];
            arr[l] = arr[rr];
            arr[rr] = tem;
        }
    }


    /**
     * 大根堆做法
     */
    class Solution1 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0) return new int[0];
            PriorityQueue<Integer> que = new PriorityQueue<>((x, y) -> (y - x));
            for (int a : arr) {
                if (que.size() < k) {
                    que.offer(a);
                }else if (que.peek() > a){
                    que.poll();
                    que.offer(a);
                }
            }
            int[] res = new int[que.size()];
            int index = 0;
            for (int i : que) {
                res[index ++] = i;
            }
            return res;
        }
    }
}
