/*
 * @lc app=leetcode.cn id=1281 lang=java
 *
 * [1281] 整数的各位积和之差
 *
 * https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/description/
 *
 * algorithms
 * Easy (82.28%)
 * Likes:    31
 * Dislikes: 0
 * Total Accepted:    16.6K
 * Total Submissions: 20.2K
 * Testcase Example:  '234'
 *
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 234
 * 输出：15 
 * 解释：
 * 各位数之积 = 2 * 3 * 4 = 24 
 * 各位数之和 = 2 + 3 + 4 = 9 
 * 结果 = 24 - 9 = 15
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 4421
 * 输出：21
 * 解释： 
 * 各位数之积 = 4 * 4 * 2 * 1 = 32 
 * 各位数之和 = 4 + 4 + 2 + 1 = 11 
 * 结果 = 32 - 11 = 21
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int subtractProductAndSum(int n) {
        int sum_of_bit = 0;
        int mul_of_bit = 1;
        while (n != 0) {
            int bit = n % 10;
            mul_of_bit *= bit;
            sum_of_bit += bit;
            n = n / 10;
        } 
        return mul_of_bit - sum_of_bit;
    }
}
// @lc code=end

