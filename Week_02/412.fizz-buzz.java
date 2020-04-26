import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=412 lang=java
 *
 * [412] Fizz Buzz
 *
 * https://leetcode-cn.com/problems/fizz-buzz/description/
 *
 * algorithms
 * Easy (63.08%)
 * Likes:    56
 * Dislikes: 0
 * Total Accepted:    32.4K
 * Total Submissions: 51.2K
 * Testcase Example:  '1'
 *
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * 
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * 
 * 示例：
 * 
 * n = 15,
 * 
 * 返回:
 * [
 * ⁠   "1",
 * ⁠   "2",
 * ⁠   "Fizz",
 * ⁠   "4",
 * ⁠   "Buzz",
 * ⁠   "Fizz",
 * ⁠   "7",
 * ⁠   "8",
 * ⁠   "Fizz",
 * ⁠   "Buzz",
 * ⁠   "11",
 * ⁠   "Fizz",
 * ⁠   "13",
 * ⁠   "14",
 * ⁠   "FizzBuzz"
 * ]
 * 
 * 
 */
/**
 * 备注：这里的题解我认为具备扩展性，如果要满足更多的倍数要求，只需要
 * 改变config即可，太简单的解法这里就直接提交到LeetCode网页上面了
 */
// @lc code=start
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();

        Map<Integer, String> config = new LinkedHashMap<>();
        config.put(3, "Fizz");
        config.put(5, "Buzz");

        for (int i = 1; i <= n; i++) {
            String numString = "";
            for (Map.Entry<Integer, String> entry : config.entrySet()) {
                if (i % entry.getKey() == 0) {
                    numString += entry.getValue();
                }
            }
            if (numString == "") {
                numString = String.valueOf(i);
            }
            ans.add(numString);
        }
        return ans;
    }
}
// @lc code=end

