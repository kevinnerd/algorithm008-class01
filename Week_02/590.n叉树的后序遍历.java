import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.scene.Node;

/*
 * @lc app=leetcode.cn id=590 lang=java
 *
 * [590] N叉树的后序遍历
 *
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/description/
 *
 * algorithms
 * Easy (72.79%)
 * Likes:    61
 * Dislikes: 0
 * Total Accepted:    20.5K
 * Total Submissions: 28.1K
 * Testcase Example:  '[1,null,3,2,4,null,5,6]'
 *
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * 
 * 例如，给定一个 3叉树 :
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 返回其后序遍历: [5,6,3,2,4,1].
 * 
 * 
 * 
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> rst = new ArrayList();
        helper(root, rst);
        return rst;
    }

    private void helper(Node root, List<Integer> rst) {
        if (root != null) {
            if (root.children != null && !root.children.isEmpty()) {
                for (Node node : root.children) {
                    helper(node, rst);
                }
            }
            rst.add(root.val);
        }
    }
}

class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> rst = new ArrayList();
        
        ColorNode colorNode = new ColorNode(Color.White, root);
        Stack<ColorNode> stack = new Stack<>();
        stack.push(colorNode);
        while (!stack.isEmpty()) {
            ColorNode node = stack.pop();
            if (node.node == null) continue;
            if (node.color == Color.White) {
                stack.push(new ColorNode(Color.Grey, node.node));
                List<Node> chilList = node.node.children;
                if (chilList != null && !chilList.isEmpty()) {
                    for (int i = chilList.size() - 1; i >= 0; i--) {
                        stack.push(new ColorNode(Color.White, chilList.get(i)));
                    }
                }
            } else {
                rst.add(node.getVal());
            }
        }
        return rst;
    }

    private enum Color {
        White,Grey
    }

    private static class ColorNode {
        Color color;
        Node node;

        ColorNode(Color color, Node node) {
            this.color = color;
            this.node = node;
        }

        int getVal() {
            return this.node.val;
        }
    }

}
// @lc code=end

