import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (73.42%)
 * Likes:    261
 * Dislikes: 0
 * Total Accepted:    47.7K
 * Total Submissions: 64.8K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> cur = new LinkedList<>();
        _combine(n, k, 1, cur);
        return ans;
    }

    private void _combine(int n, int k, int a, LinkedList<Integer> cur) {
        if (cur.size() == k) {
            ans.add(new ArrayList<>(cur));
            return ;            
        }
        for (int i = a; i <= n; i++) {
            cur.add(i);
            _combine(n, k, i + 1, cur);
            cur.removeLast();
        }
    }

}
// @lc code=end

