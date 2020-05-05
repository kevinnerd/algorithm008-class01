import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (74.62%)
 * Likes:    689
 * Dislikes: 0
 * Total Accepted:    123K
 * Total Submissions: 161.9K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        Deque<Integer> track = new ArrayDeque<>(nums.length);
        backtrack(nums, track);
        return ans;
    }

    private void backtrack(int[] nums, Deque<Integer> track) {
        if (track.size() == nums.length) {
            ans.add(new ArrayList<>(track));
            return ;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track);
            track.removeLast();
        }
    }
}
// @lc code=end

