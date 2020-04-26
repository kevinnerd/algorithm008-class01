import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=239 lang=java
 *
 * [239] 滑动窗口最大值
 *
 * https://leetcode-cn.com/problems/sliding-window-maximum/description/
 *
 * algorithms
 * Hard (45.66%)
 * Likes:    329
 * Dislikes: 0
 * Total Accepted:    43.2K
 * Total Submissions: 92.5K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k
 * 个数字。滑动窗口每次只向右移动一位。
 * 
 * 返回滑动窗口中的最大值。
 * 
 * 
 * 
 * 进阶：
 * 
 * 你能在线性时间复杂度内解决此题吗？
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7] 
 * 解释: 
 * 
 * ⁠ 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * ⁠1 [3  -1  -3] 5  3  6  7       3
 * ⁠1  3 [-1  -3  5] 3  6  7       5
 * ⁠1  3  -1 [-3  5  3] 6  7       5
 * ⁠1  3  -1  -3 [5  3  6] 7       6
 * ⁠1  3  -1  -3  5 [3  6  7]      7
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> window = new PriorityQueue<>(k, (val1, val2) -> (val2 - val1));
        List<Integer> ansList = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            window.add(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            ansList.add(window.peek());
            window.remove(nums[i - k]);
            window.add(nums[i]);
        }
        
        ansList.add(window.peek());

        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}

class Solution {
    private final int[] EMPTY = new int[0];

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length * k == 0) {
            return EMPTY;
        }
        if (k == 1) {
            return nums;
        }

        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> window = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!window.isEmpty() && window.peekFirst() < i - k + 1) {
                window.pollFirst();
            }
            while (!window.isEmpty() && nums[i] >= nums[window.peekLast()]) {
                window.pollLast();
            }
            window.offerLast(i);
            if (i >= k - 1) {
                ans[i - k + 1] = nums[window.peekFirst()];
            }
        }
        return ans;
    }
}
// @lc code=end

