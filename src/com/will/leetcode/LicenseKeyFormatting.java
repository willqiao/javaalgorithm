package com.will.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

class Solution {
    public String licenseKeyFormatting(String s, int k	) {
    	StringBuilder finalresult = new StringBuilder();
    	int temp = 0;
    	for (int i = s.length()-1 ; i >=0 ; i--) {
    		if (s.charAt(i) != '-') {
    			if (temp == k) {
    				finalresult.append('-');
    				temp = 1;
    			} else {
    				temp++;
    			}
    			finalresult.append(Character.toUpperCase(s.charAt(i)));
    			
    		} else {
    			//move on
    		}
    	}    	
        return finalresult.reverse().toString();
    }
}

public class LicenseKeyFormatting {
	
	@Test
	void test() {
		assertEquals(new Solution().licenseKeyFormatting("5F3Z-2e-9-w", 4), "5F3Z-2E9W");
		assertEquals(new Solution().licenseKeyFormatting("2-5g-3-J", 2), "2-5G-3J");
		assertEquals(new Solution().licenseKeyFormatting("r", 1), "R");
	}

}
