package com.will.leetcode;

import java.util.Iterator;
import java.util.LinkedList;

class TreeIterator implements Iterator<TreeNode>{
	TreeNode current;
	TreeNode findDFS(TreeNode n) {
		if (n == null) return null;
		
		if (n.left != null)  return this.findDFS(n.left);
		if (n.left == null && n.right == null)  return n;
		if (n.right != null)  return this.findDFS(n);
		
		return null;
	}

	@Override
	public boolean hasNext() {
		if (current.left!= null) return true;
		if (current.right!= null) return true;
		
		return false;
	}

	@Override
	public TreeNode next() {
		// TODO Auto-generated method stub
		return null;
	}
}

public class ValidBST {
    public boolean isValidBST(TreeNode root) {
    	if (root == null) return true;
        return this.isValidBSTHelper(root, null, null );
    }
    
    public boolean isValidBSTHelper(TreeNode n, Integer mustGreaterThan, Integer mustSmallerThan) {
    	boolean result = true;
		if (mustGreaterThan!= null && n.val <= mustGreaterThan) {
			return false;
		}
    	
		if (mustSmallerThan!= null && n.val >= mustSmallerThan) {
			return false;
		}
		
		
    	if (n.right != null) {
	    	result = this.isValidBSTHelper(n.right, n.val, mustSmallerThan);
	    	if (result == false) return false;
    	}
    	
    	if (n.left != null) {
	    	result = this.isValidBSTHelper(n.left, mustGreaterThan, n.val);
	    	if (result == false) return false;
    	}
    	
        return result;
    }
    
    static void printDFS(TreeNode n) {
    	if (n.left != null) {
    		printDFS(n.left);
    	}
    	System.out.println(n);
    	if (n.right != null) {
    	printDFS(n.right);
    	}
    }
    
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(1);
		root.right.right = new TreeNode(9);
//		printDFS(root);
		System.out.println(new ValidBST().isValidBST(root));
	}

}
