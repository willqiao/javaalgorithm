package com.will.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class LongestSubstring {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
    	if (s.length() <=2) return s.length();
    	
    	int max = -1;
    	
    	
    	for (int i = 0; i < s.length(); i++) {
    		boolean lessTwoChar = true;
    		int end = i+1;
    		HashSet set = new HashSet();
    		set.add(s.charAt(i));
    		while (end < s.length() && lessTwoChar) {
    			if (set.contains(s.charAt(end)) || set.size() <=1) {//match
    				set.add(s.charAt(end));
    				
    				if (max < (end - i +1)) max = end-i +1;
    				end++;
    				
    			} else {
    				lessTwoChar = false;
    			}
    		}
		}
    	
    	return max;
    }
    
    
	public static void main(String[] args) {
		System.out.println(new LongestSubstring().lengthOfLongestSubstringTwoDistinct("acbbaccccc"));

	}

}
