import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=917 lang=java
 *
 * [917] 仅仅反转字母
 *
 * https://leetcode-cn.com/problems/reverse-only-letters/description/
 *
 * algorithms
 * Easy (53.78%)
 * Likes:    44
 * Dislikes: 0
 * Total Accepted:    10.8K
 * Total Submissions: 19.9K
 * Testcase Example:  '"ab-cd"'
 *
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入："ab-cd"
 * 输出："dc-ba"
 * 
 * 
 * 示例 2：
 * 
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 
 * 
 * 示例 3：
 * 
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122 
 * S 中不包含 \ or "
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reverseOnlyLetters(String S) {
        Deque<Character> letters = getLetters(S);
        return reverseLetters(S, letters);
    }

    private String reverseLetters(String S, Deque<Character> letters) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (Character.isLetter(S.charAt(i))) {
                ans.append(letters.pollFirst());
            } else {
                ans.append(S.charAt(i));
            }
        }
        return ans.toString();
    }

    private Deque<Character> getLetters(String S) {
        Deque<Character> letters = new ArrayDeque<>(S.length());
        for (int i = 0; i < S.length(); i++) {
            if ( Character.isLetter(S.charAt(i)) ) {
                letters.offerFirst(S.charAt(i));
            }
        }
        return letters;
    }
}
// @lc code=end

