package com.will.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class Solution2 {
    public List<List<Integer>> threeSum(int[] nums) {
    	Arrays.sort(nums);
    	Set result = new HashSet();
    	for (int i = 0; i < nums.length; i ++) {
    		
    		int secondI = i+1;
    		int thirdI = nums.length -1;
    		
    		while (secondI < thirdI) {
    			int mysum = nums[i]+nums[secondI]+nums[thirdI];
    			if (mysum == 0) {
    				List l = new ArrayList();
    				l.add(nums[i]);
    				l.add(nums[secondI]);
    				l.add(nums[thirdI]);
    				result.add(l);
    				secondI ++;
    				thirdI--;
    			} else if (mysum >0) {
    				thirdI--;
    			} else if (mysum <0) {
    				secondI++;
    			}
    		}
    		
    		
    	}
    	
    	return new ArrayList(result);
//        return null;
    }
}


class ThreeSum {

	@Test
	void test() {
		System.out.println(new Solution2().threeSum( new int[] {10,-2,-12,3,-15,-12,2,-11,3,-12,9,12,0,-5,-4,-2,-7,-15,7,4,-5,-14,-15,-15,-4,10,9,-6,7,1,12,-6,14,-15,12,14,10,0,10,-10,3,4,-12,10,7,-9,-7,-15,-8,-15,-4,2,9,-4,3,-10,13,-3,-1,5,5,-4,-15,4,-11,4,-4,6,-11,-9,12,7,11,7,2,-5,13,10,-5,-10,3,7,0,-3,10,-12,2,9,-8,8,-9,13,12,13,-6,8,3,5,-9,7,12,10,-8,0,2,1,10,-7,-3,-10,-10,7,4,5,-11,-8,0,-2,-14,8,13,-8,-2,10,13} ));
	}

}
