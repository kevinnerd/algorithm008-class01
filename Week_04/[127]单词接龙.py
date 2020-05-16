# 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
#  
# 
#  
#  每次转换只能改变一个字母。 
#  转换过程中的中间单词必须是字典中的单词。 
#  
# 
#  说明: 
# 
#  
#  如果不存在这样的转换序列，返回 0。 
#  所有单词具有相同的长度。 
#  所有单词只由小写字母组成。 
#  字典中不存在重复的单词。 
#  你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
#  
# 
#  示例 1: 
# 
#  输入:
# beginWord = "hit",
# endWord = "cog",
# wordList = ["hot","dot","dog","lot","log","cog"]
# 
# 输出: 5
# 
# 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
#      返回它的长度 5。
#  
# 
#  示例 2: 
# 
#  输入:
# beginWord = "hit"
# endWord = "cog"
# wordList = ["hot","dot","dog","lot","log"]
# 
# 输出: 0
# 
# 解释: endWord "cog" 不在字典中，所以无法进行转换。 
#  Related Topics 广度优先搜索


# leetcode submit region begin(Prohibit modification and deletion)
from collections import defaultdict


class Solution(object):
    def ladderLength(self, beginWord, endWord, wordList):
        """
        :type beginWord: str
        :type endWord: str
        :type wordList: List[str]
        :rtype: int
        """
        # corner case
        if endWord not in wordList:
            return 0
        # Dictionary to hold combination of words that can be formed,
        # from any given word. By changing one letter at a time.
        L = len(beginWord)
        intermediate_dict = defaultdict(list)
        for i in range(L):
            for word in wordList:
                intermediate_word = word[:i] + "*" + word[i+1:]
                intermediate_dict[intermediate_word].append(word)
        # bfs
        queue = [(beginWord, 1)]
        visited = set([beginWord])
        while queue:
            word, step = queue.pop(0)
            for i in range(L):
                intermediate_word = word[:i] + "*" + word[i+1:]
                for inter_word in intermediate_dict[intermediate_word]:
                    if inter_word == endWord:
                        return step + 1
                    if inter_word not in visited:
                        queue.append((inter_word, step + 1))
                        visited.add(inter_word)
        return 0
# leetcode submit region end(Prohibit modification and deletion)
if __name__ == '__main__':
    Solution().ladderLength("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"])
