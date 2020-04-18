/*
 * @lc app=leetcode.cn id=66 lang=java
 *
 * [66] 加一
 *
 * https://leetcode-cn.com/problems/plus-one/description/
 *
 * algorithms
 * Easy (43.72%)
 * Likes:    458
 * Dislikes: 0
 * Total Accepted:    143.4K
 * Total Submissions: 326.7K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 
 * 
 * 示例 2:
 * 
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] plusOne(int[] digits) {
        int[] ans = new int[digits.length];
        digits[digits.length - 1] += 1;
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] == 10) {
                digits[i - 1] += 1;
                ans[i] = 0;
            } else {
                ans[i] = digits[i];
            }
        }
        
        if (digits[0] == 10) {
            ans[0] = 0;
            int[] newAns = new int[ans.length + 1];
            System.arraycopy(ans, 0, newAns, 1, ans.length);
            newAns[0] = 1;
            ans = newAns;
        } else {
            ans[0] = digits[0];
        }
        return ans;
    }
}
// @lc code=end

