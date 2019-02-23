package com.will.leetcode;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "[val=" + val + ", next=" + next + "]";
	}

	
}


public class SortListToTree {
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return new TreeNode(head.val);
		
		return this.treeHelper(head);
	}
	
	public TreeNode treeHelper(ListNode head) {
		ListNode middle = this.findMiddle(head);
		if (head == null) return null;
		if (middle == head) {
			return new TreeNode(head.val);
		}
		
		TreeNode root = new TreeNode(middle.val);
		root.left = this.treeHelper(head);
		root.right = this.treeHelper(middle.next);
		
		return root;
	}
	
	
	
	ListNode findMiddle(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode previous = head;
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next!= null) {
			previous = slow;
			slow = slow.next;
			fast = fast.next;
			if (fast!=null && fast.next != null) {
				fast = fast.next;
			}
		}
		previous.next = null;
		return slow;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		
		
		System.out.println(new SortListToTree().sortedListToBST(head));
		// TODO Auto-generated method stub

	}

}
