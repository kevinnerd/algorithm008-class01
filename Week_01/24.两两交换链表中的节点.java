/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
 *
 * algorithms
 * Medium (65.04%)
 * Likes:    471
 * Dislikes: 0
 * Total Accepted:    94K
 * Total Submissions: 144.2K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 
 * 
 * 示例:
 * 
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * 思路：增加哨兵节点
 * 这里增加哨兵节点的好处有两个：
 * 1. 哨兵的next节点就是最终的返回结果
 * 2. 循环的结束条件得到简化，哨兵节点后面必须有2个节点
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode a = cur.next;
            ListNode b = cur.next.next;
            // 交换a, b
            a.next = b.next;
            b.next = a;
            cur.next = b;
            cur = cur.next.next;
        }
        return dummy.next;
    }
}

/**
 * 递归
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(head.next.next);
        next.next = head;
        return next;
    }
}
// @lc code=end

