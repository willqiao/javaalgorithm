package com.will.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Permutations {
	static int count  = 0;
    public List<List<Integer>> permute(int[] nums) {
    	if (nums.length == 0) {
    		return new ArrayList<List<Integer>>();
    	}
    	ArrayList<Integer> options = new ArrayList<Integer>(); 
    	for (int num : nums) {
    		options.add(num);
    	}
    	
    	List<List<Integer>>  result = this.helper(options, 0);
    	System.out.println(result);
    	
        return result;
    }
    
    List<List<Integer>> helper(List<Integer> l, int start) {
    	List<List<Integer>> newResult = new ArrayList<List<Integer>> ();
    	if (start == l.size()) {
    		newResult.add(new ArrayList<Integer>());
    		return newResult;
    	}
    	
    	List<List<Integer>> children = this.helper(l, start+1);
    	for (Integer num: l) {
    		children.stream().forEach(child->{
    			if (!child.contains(num)) {
    				ArrayList temp = new ArrayList(child);
    				temp.add(num);
    				newResult.add(temp);
    				
    			} //else just continue
    			
        	});
		}
    	
    	return newResult;
    }
    
    
	public static void main(String[] args) {
		new Permutations().permute(new int[] {1,2,3,4,5});

	}

}
