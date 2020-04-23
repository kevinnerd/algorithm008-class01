import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=242 lang=java
 *
 * [242] 有效的字母异位词
 *
 * https://leetcode-cn.com/problems/valid-anagram/description/
 *
 * algorithms
 * Easy (59.23%)
 * Likes:    183
 * Dislikes: 0
 * Total Accepted:    92.5K
 * Total Submissions: 155.5K
 * Testcase Example:  '"anagram"\n"nagaram"'
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 
 * 示例 1:
 * 
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * 
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * 
 */

// @lc code=start
/**
 * 解法1：26位数组代替hashmap，存储字母出现次数，最后统计数组得出结果
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        
        int[] letters_cnt = countLetters(s, t, s.length());
        return checkLettersCnt(letters_cnt);
    }

    private int[] countLetters(String s, String t, int length) {
        int[] letters_cnt = new int[26];
        for (int i = 0; i < length; i++) {
            letters_cnt[s.charAt(i) - 'a'] += 1;
            letters_cnt[t.charAt(i) - 'a'] -= 1;
        }
        return letters_cnt;
    }

    private boolean checkLettersCnt(int[] letters_cnt) {
        for (int letter_count : letters_cnt) {
            if (letter_count != 0) return false;
        }
        return true;
    }
}

/**
 * 解法2：排序然后比较
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        return resort(s).equals(resort(t));
    }

    private String resort(String s) {
        if (s == null) return "";
        if (s.length() < 2) return s;

        char[] s_arr = s.toCharArray();
        Arrays.sort(s_arr);
        return String.valueOf(s_arr);
    }
}
// @lc code=end

