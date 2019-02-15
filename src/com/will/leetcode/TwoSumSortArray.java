package com.will.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class TwoSumSortArray {
    public int[] twoSum(int[] numbers, int target) {
    	int s = 0;
    	int e = numbers.length - 1;
    	
    	while (e>s) {
    		if (numbers[s] + numbers[e] > target) {
    			e--;
    		} else if (numbers[s] + numbers[e] < target) {
    			s++;
    		} else if (numbers[s] + numbers[e] == target) {
    			return new int[] {s+1, e+1}; 
    		}
    	}
    	
    	return null;
    }
    
	public static void main(String[] args) {
		int[][] t = new int[10][];
		System.out.println(Arrays.toString(new TwoSumSortArray().twoSum(new int[] {-1, 0,1,2,4,5,6,7} , 5)));
	}

}
