# 算法学习笔记-Week-3

## 递归的代码模板

```java
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 
}
```

简单解释一下上面的代码模板，分为以下几个步骤：
1. 先处理递归终止条件，类比于循环也需要终止条件一样，避免无限递归
2. 处理当前层次的代码逻辑，也就是子问题的逻辑
3. 进入下一层递归
4. 清理当前层状态(视情况而定)

## 递归题目与解法分析

为了重点掌握递归的解题思路，这里归纳分析几道递归题目，作为后续参考。

### 1. [70-爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)

主要思路：
* 终止条件：当n < 3时，f(n) = n
* 递推公式：当n >= 3 时，f(n) = f(n - 1) + f(n - 2)

```java
class Solution {
    public int climbStairs(int n) {
        // 1. 终止条件: 当只有0, 1, 2级台阶时，分别有0, 1, 2种解法
        if (n < 3) {
          return n;
        }
        // 2. 当前层逻辑 3. 进入下一层递归
        int n_ans = climbStairs(n - 1) + climbStairs(n - 2);
        return n_ans;
    }
}
```

### 2. [22-括号生成](https://leetcode-cn.com/problems/generate-parentheses/)

主要思路：将题目看做n个左括号和n个右括号的排列组合，但有一个限制，必须先出现左括号，再出现右括号，
且左右括号都为n时得到结果
* 终止条件：open_cnt == n && close_cnt == n
* 递推逻辑描述：
  * when open_cnt < n : append '(' to current result
  * when close_cnt < open_cnt : append ')' to current result

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        helper(0, 0, n, "", ans);
        return ans;
    }

    private void helper(int open_cnt, int close_cnt, int max, String cur_result, List<String> ans) {
        if (open_cnt == max && close_cnt == max) {
            ans.add(cur_result);
            return ;
        }
        if (open_cnt < max) {
            helper(open_cnt + 1, close_cnt, max, cur_result + "(", ans);
        }
        if (close_cnt < open_cnt) {
            helper(open_cnt, close_cnt + 1, max, cur_result + ")", ans);
        }
    }
}
```

注意几点：
1. 递归函数helper是递归的生成每一个可能的结果
2. 之所以增加两个if判断，是因为要剪掉一些不符合题目要求的结果
3. 递归时要注意两个cnt变量的值是 +1 而不是 ++, 在当前层不能改变cnt变量的值，因为这里的+1 代表下一层的字符串增加了一个括号，同理当前层的字符串也是 +，而不是 +=，这点要理解

### 3. [226-翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/description/)

主要思路：
* 终止条件：如果节点为null，则不需要翻转，返回节点
* 递推逻辑：翻转所有左子树，翻转所有右子树
* 当前层处理：翻转本层

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
```

### 4. [98-验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)

主要思路：从根节点开始，通过DFS算法，通过判断每个节点的合法取值范围，来确认最终结果，对于二叉搜索
树来说，每个根节点的值，都应该大于它的左子节点，小于它的右子节点，那么依次递归下去，就能得到最终结果。

通过看LeetCode的题解，我找到了下面这个很形象的解释：

```
      10
    /    \
   5     15
  / \    /  
 3   6  7 
    
   考虑 10 的范围
     10(-inf,+inf)
    
   考虑 5 的范围
     10(-inf,+inf)
    /
   5(-inf,10)
   
   考虑 3 的范围
       10(-inf,+inf)
      /
   5(-inf,10)
    /
  3(-inf,5)  
          
   考虑 6 的范围
       10(-inf,+inf)
      /
   5(-inf,10)
    /       \
  3(-inf,5)  6(5,10)
          
   考虑 15 的范围
      10(-inf,+inf)
    /          \
    5(-inf,10) 15(10,+inf）
    /       \
  3(-inf,5)  6(5,10)  
   
   考虑 7 的范围，出现不符合返回 false
       10(-inf,+inf)
     /              \
5(-inf,10)           15(10,+inf）
  /       \             /
3(-inf,5)  6(5,10)   7（10,15）   
```
> 可以观察到，左孩子的范围是 （父结点左边界，父节点的值），右孩子的范围是（父节点的值，父节点的右边界）。

> 作者：windliang
> 链接：https://leetcode-cn.com/problems/validate-binary-search-tree/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--20/
> 来源：力扣（LeetCode）
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

通过上面的分析：
* 递归终止条件：
  1. root == null 则返回true
  2. root.val 小于等于左边界 返回false
  3. root.val 大于等于右边界 返回false
* 递推逻辑：
  1. 左节点作为新的根节点，当前节点的val作为右边界，入参的左边界不变，得到左节点结果
  2. 右节点作为新的根节点，当前节点的val作为左边界，入参的由边界不变，得到右节点结果
  3. 左右节点结果取 &&，return

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        // 这里用null来代替无穷大或无穷小的情况
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}
```

### 5. [104-二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)

主要思路：
* 递归终止条件：节点为null，返回最大深度为0
* 递推逻辑：max(计算左子树的深度，计算右子树的深度) + 1

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```

### 6. [111-二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

主要思路：这个题目看上去和最大深度类似，但是需要注意一点：

> 题目要求：最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
> 叶子节点：叶子节点是指没有子节点的节点

所以像[1,2]这种结构：
```
    1
   / \
null  2
```

节点2是叶子节点，而null节点不是叶子节点，所以最短路径是：2 而不是1

所以这道题需要考虑下面3种情况
1. 根节点为null，返回0
2. 左节和右节点都为空， 返回1
3. 左右节点有一个为空，返回另一个节点的最小深度值

* 递归终止条件：
  1. root == null return 0
  2. root.left == null && root.right == null return 1
* 递推逻辑：
  1. 如果left == null，返回minDepth(right) + 1
  2. 如果right == null， 返回minDepth(left) + 1
  3. 都不为空, 返回Min(left_result, right_result) + 1

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left_ans = minDepth(root.left);
        int right_ans = minDepth(root.right);
        if (root.left == null) return right_ans + 1;
        if (root.right == null) return left_ans + 1;
        return Math.min(left_ans, right_ans) + 1;
    }
}
```

### 7. [297-二叉树的序列化与反序列化](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/)

主要思路：序列化的时候，对二叉树进行前序遍历，遍历过程中组织字符串结果；反序列化时，以同样的前序
遍历方式依次创建节点

序列化思路：
* 终止条件：root == null 添加"null,"到结果中
* 递推逻辑：前序遍历，把当前节点添加到结果中

反序列化思路：
* 终止条件：节点的字符串 == "null"，return null
* 递推逻辑：以前序遍历的顺序依次递归恢复节点，先创建当前节点，然后递归创建左子树，然后右子树

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#,";

        String ans = root.val + ",";
        ans += serialize(root.left);
        ans += serialize(root.right);
        return ans;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArr = data.split(",");
        Queue<String> queue = new ArrayDeque<>();
        offerAll(queue, dataArr);
        return _deserialize(queue);
    }

    private void offerAll(Queue<String> queue, String[] arr) {
        for (String s : arr) {
            queue.offer(s);
        }
    }

    private TreeNode _deserialize(Queue<String> queue) {
        String nodeStr = queue.poll();
        if ("#".equals(nodeStr)) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(nodeStr));
        root.left = _deserialize(queue);
        root.right = _deserialize(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

## 分治与回溯

分治代码模板：
```java
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 
  if problem is None: 
	print_result 
	return 

  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 

  # conquer subproblems 
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …

  # process and generate the final result 
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states
```