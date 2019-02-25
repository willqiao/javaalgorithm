package com.will.leetcode;

import java.util.LinkedList;
import java.util.Stack;

class BSTIterator {

	Stack stack = new Stack();

	public BSTIterator(TreeNode root) {
		super();
		TreeNode current = root;
		while(current != null) {
			stack.push(current);
			current = current.left;
		}
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public int next() {
		TreeNode returnNode = (TreeNode)stack.pop();
		
		
		TreeNode current = returnNode;
		
		if (current.right != null) {
			current = current.right;
			stack.push(current);
			
			while (current != null) {
				current = current.left;
				if (current != null) {
					stack.push(current);
				}
			}
		}
		
		return returnNode.val;
		
		
	}
}

public class ValidBST {
    public boolean isValidBST(TreeNode root) {
    	if (root == null) return true;
        return this.isValidBSTHelper(root, null, null );
    }
    
    public boolean isValidBSTHelper(TreeNode n, Integer mustGreaterThan, Integer mustSmallerThan) {
    	boolean result = true;
		//delete implementation.
        return result;
    } 
    
    static void printDFS(TreeNode n) {
    	if (n == null ) return;
    	
    	printDFS(n.left);
    	System.out.println(n);
    	printDFS(n.right);
    }
    
    
    
    
    static void printBFS(TreeNode n) {
    	Stack stack = new Stack();
    	TreeNode current = n;
    	while (current !=null  || !stack.isEmpty()) {
    		if (current != null) {
    			stack.push(current);
	    		current = current.left;	    		
    		} else { //current is null, pop stack and check for right and current
    			current = (TreeNode)stack.pop();
    			System.out.println(current);
    			current = current.right;
    		}
    	}
    }    
    static void printBFSNode(TreeNode n) {
    	LinkedList queue = new LinkedList();
    	queue.add(n);
    	
    	while(!queue.isEmpty()) {
    		TreeNode current = (TreeNode)queue.poll();
    		
    		if (current!=null) {
    			System.out.println(current);
	    		if (current != null) {
	    			queue.add(current.left);
	    			queue.add(current.right);
	    		}
    		}
    	}
    	
    	
    }
    
	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(11);
		root.left.right.right.right = new TreeNode(2);
		
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		printBFSNode(root);
//		BSTIterator t = new BSTIterator(root);
//		while(t.hasNext()) {
//			System.out.println(t.next());
//		}
//		System.out.println(t.hasNext());
//		printDFS(root);
//		printBFS(root);
//		System.out.println(new ValidBST().isValidBST(root));
	}

}
