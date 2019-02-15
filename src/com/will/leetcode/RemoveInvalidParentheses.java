package com.will.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
    	
//    	
    	StringBuilder sb = new StringBuilder();
    	Stack<Character> stack = new Stack<Character>();
    	//remove ) from left
    	for (int i = 0; i < s.length();i++) {
    		if (s.charAt(i) == '(') {
    			sb.append(s.substring(i));
    			break;
    		} 
    		
    		if (s.charAt(i) != ')') {
    			sb.append(s.charAt(i));
    		}
    	}
    	s = sb.toString();
    	sb = new StringBuilder();
    	boolean hasbreak = false;
    	//remove ( from right
    	for (int i = s.length()-1; i >= 0;i--) {
    		if (s.charAt(i) == ')') {
    			s = s.substring(0, i+1) + sb.reverse().toString();
    			hasbreak = true;
    			break;
    		} 
    		
    		if (s.charAt(i) != '(') {
    			sb.append(s.charAt(i));
    		}
    	}
    	if (hasbreak == false) {
    		s = sb.reverse().toString();
    		
    	}
    	
    	
    	
    	
    	HashMap map = new HashMap();
    	List left = new ArrayList();
    	List right = new ArrayList();
    	int leftC = 0;
    	int rightC = 0;
    	for (int i = 0; i < s.length();i++) {
    		if (s.charAt(i) == '(') {
    			leftC++;
    		} 
    		
    		if (s.charAt(i) == ')') {
    			if (leftC >0) {
    				leftC--;
    			} else {
    				rightC++;
    			}
    			
    		}
    	}
    	System.out.println("cccccccccc");
    	System.out.println(leftC);
    	System.out.println(rightC);
    	
    	if (s.equals("") || (leftC== 0 && rightC==0)) {
    		List my = new ArrayList();
    		my.add(s);
    		return my;
    	}
    	map.put('(', left);
    	map.put(')', right);
    	System.out.println(map);
    	final Set mylist = new HashSet();
    	this.removeHelper(s, 0, leftC, rightC, mylist);
    	
    	
    	
    	System.out.println(mylist);
    	
        return new ArrayList(mylist);
    }
    
    String removeHelper(String s, int index, int left, int right, Set res){
    	if (left == 0 && right == 0) {
    		System.out.println("ssssssssssss"+s);
    		if (this.isValid(s)) {
    			res.add(s);
    			return s;
    		} else return null;
    	}
    	
    	
    	for (int i = index; i < s.length();i++) {
    		if (s.charAt(i)== '(' && left > 0) {
    			StringBuilder sb = new StringBuilder(s);
    			sb.deleteCharAt(i);
    			this.removeHelper(sb.toString(), i, left - 1, right, res);
    		} else if( s.charAt(i)== ')' && right >0) {
    			StringBuilder sb = new StringBuilder(s);
    			sb.deleteCharAt(i);
    			this.removeHelper(sb.toString(), i, left, right -1, res);
    		}
    	}
    	
    	return "";
    }
    
    boolean isValid (String s) {
    	boolean result = false;
    	Stack<Character> stack = new Stack<Character>();
    	for (int i = 0; i < s.length();i++) {
    		if (s.charAt(i) == '(') {
    			stack.push(s.charAt(i));
    		} 
    		
    		if (s.charAt(i) == ')') {
    			if (stack.size() == 0) {
    				return false;
    			}
    			stack.pop();
    		}
    	}
    	
    	return stack.size() == 0;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new RemoveInvalidParentheses().removeInvalidParentheses(")("));
	}

}
