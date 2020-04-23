import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Medium (70.84%)
 * Likes:    474
 * Dislikes: 0
 * Total Accepted:    143.3K
 * Total Submissions: 201.6K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的中序 遍历。
 * 
 * 示例:
 * 
 * 输入: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * 输出: [1,3,2]
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 
 */

// @lc code=start
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
    private enum Color {
        White,Grey
    }

    private static class ColorNode {
        Color color;
        TreeNode node;

        ColorNode(Color color, TreeNode node) {
            this.color = color;
            this.node = node;
        }

        int getVal() {
            return this.node.val;
        }

        TreeNode getLeft() {
            return this.node.left;
        }

        TreeNode getRight() {
            return this.node.right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Stack<ColorNode> cnStack = new Stack<>();
        ColorNode first = new ColorNode(Color.White, root);
        cnStack.push(first);

        while (!cnStack.isEmpty()) {
            ColorNode node = cnStack.pop();
            if (node.node == null) continue;
            if (node.color == Color.White) {
                cnStack.push(new ColorNode(Color.White, node.getRight()));
                cnStack.push(new ColorNode(Color.Grey, node.node));
                cnStack.push(new ColorNode(Color.White, node.getLeft()));
            } else {
                result.add(node.getVal());
            }
        }
        return result;
    }
}

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode root, List<Integer> result) {
        if (root != null) {
            helper(root.left, result);
            result.add(root.val);
            helper(root.right, result);
        }
    }
}
// @lc code=end

