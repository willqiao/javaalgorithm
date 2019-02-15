package com.will.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoSum {
	
    public int[] twoSum(int[] nums, int target) {
    	for (int i = 0; i < nums.length -1 ; i++) {
    		for (int j = i+1; j < nums.length; j++) {
    			if (nums[i] + nums[j] == target ) {
    				return new int[] {i, j};
    			}
    		}
    	}
        return null;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(Arrays.toString(new TwoSum().twoSum(new int[] {3,4,2,1,3,45,-1,2,2,0,0} , 3)));
	}

}
