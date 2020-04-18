/*
 * @lc app=leetcode.cn id=977 lang=java
 *
 * [977] 有序数组的平方
 *
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/description/
 *
 * algorithms
 * Easy (71.18%)
 * Likes:    83
 * Dislikes: 0
 * Total Accepted:    33.7K
 * Total Submissions: 47.4K
 * Testcase Example:  '[-4,-1,0,3,10]'
 *
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 
 * 
 * 示例 2：
 * 
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] sortedSquares(int[] A) {
        int[] sortedSquareA = new int[A.length];

        int l = 0, r = A.length - 1;
        while (l <= r) {
            int left = Math.abs(A[l]);
            int right = Math.abs(A[r]);
            if (left > right) {
                sortedSquareA[r - l] = left * left;
                l++;
            } else {
                sortedSquareA[r - l] = right * right;
                r--;
            }
        }
        return sortedSquareA;
    }
}
// @lc code=end

