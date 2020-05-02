/*
 * @lc app=leetcode.cn id=415 lang=java
 *
 * [415] 字符串相加
 *
 * https://leetcode-cn.com/problems/add-strings/description/
 *
 * algorithms
 * Easy (49.68%)
 * Likes:    155
 * Dislikes: 0
 * Total Accepted:    31.3K
 * Total Submissions: 63K
 * Testcase Example:  '"0"\n"0"'
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * 
 * 注意：
 * 
 * 
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * 
 * 
 */

// @lc code=start
class Solution {
    public String addStrings(String num1, String num2) {
        int p1 = num1.length() - 1, p2 = num2.length() - 1;
        int carry = 0;

        StringBuilder ans = new StringBuilder();
        while (p1 >= 0 || p2 >= 0 || carry == 1) {
            int d1 = p1 >= 0 ? num1.charAt(p1--) - '0' : 0;
            int d2 = p2 >= 0 ? num2.charAt(p2--) - '0' : 0;
            int sum = d1 + d2 + carry;
            carry = sum / 10;
            ans.append(String.valueOf(sum % 10));
        }
        return ans.reverse().toString();
    }
}
// @lc code=end
