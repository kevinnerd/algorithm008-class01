# 算法学习笔记-Week-6

本周学习内容来到了算法中公认非常难以掌握的**动态规划(Dynamic Programming)**, 由于这部分的知识
之前是毫无基础，所以这里分两部分来总结一下本周我对于动态规划这部分知识的一些理解和积累。

第一部分着重介绍动态规划问题需要关注的一些关键特点，第二部分分析具体的动态规划题目。 

## 如何理解动态规划

我认为要理解任何的概念，最好都先从维基百科入手，所以我先去看了一下维基百科上面关于[动态规划的定义](https://en.wikipedia.org/wiki/Dynamic_programming)

首先从Dynamic Programming的翻译来看，这里的Programming实际上和编程的关系不大，虽然动态规划概念
的应用领域涉及到编程，但这里的含义其实是优化(Optimization)的意思，而所谓的动态规划，实际上不仅仅
是一种编程理论，更是一种数学优化方法，感觉类似数学归纳法的概念。

说完字面意思，我觉得要理解动态规划，首先应该知道动态规划的思想主要是为了解决什么类型的问题，下面引用
一下维基百科的一段描述：

> The term dynamic programming was originally used in the 1940s by Richard Bellman
> to describe the process of solving problems where one needs to find the best decisions
> one after another. By 1953, he refined this to the modern meaning, referring 
> specifically to nesting smaller decision problems inside larger decisions

从上面的描述中，最关键的一个词就是`decision`，实际上动态规划是为了解决决策问题而诞生的，也就是我们
常说的找到问题的最优解，其实从很多动态规划的题目中也不难发现，大多都是求最值，最短路径，最优方案...
这种类似的最优解问题，那么这里就有了一个小套路，一般遇到最优解问题，那么就可以考虑是不是能够用DP解决。

能够用动态规划的问题，有两个关键的性质：**最优子结构(Optimal Substructure)** 和 **重叠子问题(overlapping sub-problems)**

乍一看上去，这两个性质似乎很难理解，但是其实本质上非常简单，先说最优子结构，其实就是一个大的问题能够
分解为互不干扰的子问题，通过这些子问题的最优结果能够推出最终问题的最优结果，这里引用labuladong文章
中的例子来说明：

> 假设你们学校有 10 个班，你已经计算出了每个班的最高考试成绩。那么现在我要求你计算全校最高的成绩，
> 你会不会算？当然会，而且你不用重新遍历全校学生的分数进行比较，而是只要在这 10 个最高成绩中取最大
> 的就是全校的最高成绩。
>
> 让你算每个班的最优成绩就是子问题，你知道所有子问题的答案后，就可以借此推出全校学生的最优成绩这个
> 规模更大的问题的答案

再来说重叠子问题，其实上面的例子虽然具备最优子结构，但是并不具备重叠子问题的性质，因为不同班级之间的
最高成绩是互不干扰的，而解决这种不具备重叠子问题的办法叫做**分治(devide and conquer)**，这也正是
分治和动态规划的最主要的区别，其实重叠子问题可以用最简单的斐波那契数列来说明：

> Fib(n) = Fib(n - 1) + Fib(n - 2) -> Fib(n - 1) = Fib(n - 2) + Fib(n - 3)

从斐波那契问题，我们可以知道，假如要求 Fib(n) 的值，那么需要先求出 Fib(n - 1)和 Fib(n - 2)，
而要求Fib(n - 1)的时候，也需要知道 Fib(n - 2)，因此Fib(n - 2)这个子问题就重叠了，说道这里你可能
回想，既然子问题都重叠了，为什么子问题之间还是互不干扰呢？这难道不矛盾吗？

答案是并不矛盾，对于上面的Fib(n - 2)虽然重叠了，但是在我们计算Fib(n - 2)的过程中，并不会对其他
子问题有任何的影响，我们只是以一种递归的方式使用其他子问题的计算结果而已，因此各个子问题还是作为独立
的问题来解决的。

说完上面两个关键性质之后，就引出了解决动态规划问题的关键，也是难点，也就是所谓的**状态转移方程**，
我们假设要解决的问题规模范围从 1~n，那么状态转移方程就是指导我们如何从规模为1的问题的最优解一步步
推导到规模为n的最优解。

## 题目及分析

### [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

首先分析题目：

题目给出以下几个限制条件：
1. m x n 的包含非负整数的网格 (二维数组 大小 m * n)
2. 初始位置为左上角 (0, 0)
3. 终止位置为右下角 (m-1, n-1)
4. 只有两个选择：向下/向右

先不考虑边界等特殊位置，假设 dp(i, j)为 走到(i, j)位置时的最小路径和，由于(i, j)这个点只能从
(i-1, j)或(i, j-1)这两个位置到达，那么只需要知道 dp(i - 1, j) 和 dp(i, j - 1)的最小值，再
加上(i, j)位置的值，就能得到 dp(i, j)，于是就得到了下面的方程：

> dp(i, j) = min(dp(i-1, j), dp(i, j-1)) + v(i, j)

那么下面再考虑边界的情况，如果遇到了上边界(i == 0)，那么路径只能从左侧过来，也就是：

> dp(i, j) = dp(i, j-1) + v(i, j)

同理，如果遇到了左边界(j == 0)，那么：

> dp(i, j) = dp(i - 1, j) + v(i, j)

最后考虑base case，也就是(0, 0)点时，`dp(0, 0) = v(0, 0)`

最终，我们的代码如下：

```python
class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        dp = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if i == 0 and j == 0:
                    dp[i][j] = grid[i][j]
                elif i == 0:
                    dp[i][j] = dp[i][j - 1] + grid[i][j]
                elif j == 0:
                    dp[i][j] = dp[i - 1][j] + grid[i][j]
                else:
                    dp[i][j] = min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j]
        return dp[-1][-1]
```

可以看到，这个实现里使用了dp数组来存储动态规划过程的中间结果，可以对空间复杂度进行优化，使用grid来
作为dp数组，只需要将上面代码的dp替换为grid即可