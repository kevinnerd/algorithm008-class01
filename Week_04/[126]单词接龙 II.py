# 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
# 需遵循如下规则： 
# 
#  
#  每次转换只能改变一个字母。 
#  转换过程中的中间单词必须是字典中的单词。 
#  
# 
#  说明: 
# 
#  
#  如果不存在这样的转换序列，返回一个空列表。 
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
# 输出:
# [
#   ["hit","hot","dot","dog","cog"],
#   ["hit","hot","lot","log","cog"]
# ]
#  
# 
#  示例 2: 
# 
#  输入:
# beginWord = "hit"
# endWord = "cog"
# wordList = ["hot","dot","dog","lot","log"]
# 
# 输出: []
# 
# 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
#  Related Topics 广度优先搜索 数组 字符串 回溯算法


# leetcode submit region begin(Prohibit modification and deletion)
from collections import defaultdict


class Solution(object):

    def build_inter_dict(self, l, wordList):
        inter_dict = defaultdict(list)
        for i in range(l):
            for word in wordList:
                inter_dict[word[:i] + '*' + word[i+1:]].append(word)
        return inter_dict

    def findAllNext(self, word, l, inter_dict):
        res = []
        for i in range(l):
            for w in inter_dict[word[:i] + '*' + word[i+1:]]:
                res.append(w)
        return res

    def findLadders(self, beginWord, endWord, wordList):
        """
        :type beginWord: str
        :type endWord: str
        :type wordList: List[str]
        :rtype: List[List[str]]
        """
        if endWord not in wordList:
            return []

        L = len(beginWord)
        inter_dict = self.build_inter_dict(L, wordList)

        queue = [(beginWord, [beginWord])]
        # visited = set([beginWord])
        ans = []
        while queue:
            size = len(queue)
            for i in range(size):
                word, path = queue.pop(0)
                for next_word in self.findAllNext(word, L, inter_dict):
                    next_path = path + [next_word]
                    if next_word == endWord:
                        ans.append(next_path)
                    else:
                        queue.append((next_word, next_path))
            if ans:
                return ans
        return ans

# leetcode submit region end(Prohibit modification and deletion)
