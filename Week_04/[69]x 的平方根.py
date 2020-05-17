# 实现 int sqrt(int x) 函数。 
# 
#  计算并返回 x 的平方根，其中 x 是非负整数。 
# 
#  由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
# 
#  示例 1: 
# 
#  输入: 4
# 输出: 2
#  
# 
#  示例 2: 
# 
#  输入: 8
# 输出: 2
# 说明: 8 的平方根是 2.82842..., 
#      由于返回类型是整数，小数部分将被舍去。
#  
#  Related Topics 数学 二分查找


# leetcode submit region begin(Prohibit modification and deletion)

# x = s * s  s > 0
# 单调递增
# 下界：1  上界：x
class Solution(object):
    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """

        left, right = 0, x // 2 + 1
        while left < right:
            mid = (left + right + 1) / 2
            res = mid * mid
            if x < res:
                right = mid - 1
            else:
                left = mid
        return left
# leetcode submit region end(Prohibit modification and deletion)
