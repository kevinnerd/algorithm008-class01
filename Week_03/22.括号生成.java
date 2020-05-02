import java.util.ArrayList;

/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 *
 * https://leetcode-cn.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (73.73%)
 * Likes:    981
 * Dislikes: 0
 * Total Accepted:    119.7K
 * Total Submissions: 159K
 * Testcase Example:  '3'
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：n = 3
 * 输出：[
 * ⁠      "((()))",
 * ⁠      "(()())",
 * ⁠      "(())()",
 * ⁠      "()(())",
 * ⁠      "()()()"
 * ⁠    ]
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        _generate(0, 0, n, "", ans);
        return ans;
    }

    private void _generate(int left, int right, int max, String result, List<String> ans) {
        if (left == max && right == max) {
            ans.add(result);
            return ;
        }

        if (left < max)
            _generate(left + 1, right, max, result + "(", ans);
        if (right < left)
            _generate(left, right + 1, max, result + ")", ans);
    }
}
// @lc code=end

