import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=350 lang=java
 *
 * [350] 两个数组的交集 II
 *
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/description/
 *
 * algorithms
 * Easy (47.53%)
 * Likes:    265
 * Dislikes: 0
 * Total Accepted:    79.6K
 * Total Submissions: 166.4K
 * Testcase Example:  '[1,2,2,1]\n[2,2]'
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 
 * 示例 1:
 * 
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 
 * 说明：
 * 
 * 
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 
 * 
 * 进阶:
 * 
 * 
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> intersectMap = new HashMap<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            intersectMap.put(nums1[i], intersectMap.getOrDefault(nums1[i], 0) + 1);
        }

        return findIntersection(nums2, intersectMap);
    }

    private int[] findIntersection(int[]                 nums2, 
                                           Map<Integer, Integer> intersectMap) {
        List<Integer> res = new ArrayList<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            if (intersectMap.containsKey(nums2[i]) 
                    && intersectMap.get(nums2[i]) > 0) {
                res.add(nums2[i]);
                intersectMap.put(nums2[i], intersectMap.get(nums2[i]) - 1);
            }
        }
        return toArr(res);
    }

    private int[] toArr(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
// @lc code=end

