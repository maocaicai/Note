/**
 * 数组中重复的数组，掌握原地修改的方法
 * On时间O1空间
 */
public class 剑指03 {
    class Solution {
        public int findRepeatNumber(int[] nums) {
            int i = 0;
            while(i < nums.length) {
                if(nums[i] == i) {
                    i++;
                    continue;
                }
                if(nums[nums[i]] == nums[i]) return nums[i];
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
            return -1;
        }
    }
}
