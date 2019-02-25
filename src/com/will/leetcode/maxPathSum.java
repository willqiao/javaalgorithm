package com.will.leetcode;

import java.util.LinkedList;

public class maxPathSum {
	public static int maxValue = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
    	travel(root);
    	
        return maxValue;
    }
    
    void travel(TreeNode node) {
    	//calculate node
    	//3 scenario
    	if (node != null) {
	    	int sum = node.val;
	    	int left = this.findMaxOneWayPath(node.left);
	    	int right = this.findMaxOneWayPath(node.right);
	    	if (left>0) {
	    		sum +=left;
	    	}
	    	if (right>0) {
	    		sum+=right;
	    	}
	    	System.out.println(node + " " + left + " " + right );
	    	if (sum > maxValue) maxValue = sum; 
	    	
	    	
	    	travel(node.left);
	    	travel(node.right);
    	} else {
    		return;
    	}
    }
    
    int findMaxOneWayPath(TreeNode node) {
    	if (node == null) return 0;
    	if (node.left ==null && node.right == null) return node.val;
    	
    	int sum = node.val;
    	int left = this.findMaxOneWayPath(node.left);
    	
    	int right = this.findMaxOneWayPath(node.right);
    	
    	if (Math.max(left, right) > 0) {
    		return sum + Math.max(left, right);
    	} else {
    		return sum;
    	}
    }
    
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(-5);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(11);
		root.left.right.right.right = new TreeNode(2);
		
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		System.out.println(new maxPathSum().maxPathSum(new TreeNode(0)));

	}

}
