/*
 * @lc app=leetcode.cn id=189 lang=java
 *
 * [189] 旋转数组
 *
 * https://leetcode-cn.com/problems/rotate-array/description/
 *
 * algorithms
 * Easy (40.96%)
 * Likes:    549
 * Dislikes: 0
 * Total Accepted:    115.4K
 * Total Submissions: 281K
 * Testcase Example:  '[1,2,3,4,5,6,7]\n3'
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 
 * 
 * 示例 2:
 * 
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释: 
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 
 * 说明:
 * 
 * 
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 * 
 * 
 */

// @lc code=start
/**
 * 思路：暴力法
 * 描述：每次循环将数组最后一个元素进行反转，然后所有其他元素向后移动，
 *      重复k次，得到结果
 * 复杂度：空间复杂度O(1)，时间复杂度 O(k*n)
 */
class Solution {
    public void rotate(int[] nums, int k) {
        int last_index = nums.length - 1;
        while (k > 0) {
            int last = nums[last_index];
            for (int i = last_index; i > 0; i--) {
                nums[i] = nums[i-1];
            }
            nums[0] = last;
            k--;
        }
    }
}

/**
 * 思路：多次反转
 * 描述：!负负得正!
 *      先将整个数组全部反转，然后反转前k个元素，再反后n-k个元素，最后得到结果
 *  假设: k = 3
 *  原始数组： [1,2,3,4,5,6]
 *  一次反转： [6,5,4,3,2,1]
 *  二次反转： [4,5,6,3,2,1]
 *  三次反转： [4,5,6,1,2,3] --> 最终结果
 */
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length; // 避免k>length的情况
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1); 
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
}
// @lc code=end

