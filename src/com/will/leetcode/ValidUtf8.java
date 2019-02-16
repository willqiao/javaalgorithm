package com.will.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class ValidUtf8 {
    public boolean validUtf8(int[] data) {
    	List<String> l = Arrays.stream(data).boxed().map(num-> {
    		String r = Integer.toBinaryString(num);
    		if (r.length()>= 8) {
    			return r.substring(r.length() - 8);
    		} else {
    			char[] t = new char[8-r.length()];
    			Arrays.fill(t, '0');
    			StringBuilder sb = new StringBuilder();
    			sb.append(t);
    			sb.append(r);
    			return sb.toString();
    		}
    		}).collect(Collectors.toList());
    	System.out.println(l);
    	int i = 0;
    	boolean start = true;
    	
    	Stack stack = new Stack();
    	while (i < l.size()) {
    		if (stack.isEmpty()) {//start point
    			if (l.get(i).charAt(0) == '0') {
        			//start with 0
    				i++;
    				continue;
        		} else {
        			int countOne = l.get(i).indexOf('0');
        			if (countOne <= 1) return false;//can only be 2,3,4
        			if (countOne > 4) return false;
        			for (int j = 0; j< countOne -1; j++) {
        				stack.push(1);
        			}
        			i++;
        			continue;
        		}
    			
    		} else {//pop stack phase
    			stack.pop();
    			if (!l.get(i).startsWith("10")) {
    				return false;
    			}
    			i++;
    			continue;	
    		}
    		
    	}
    	
        return stack.isEmpty();
    }
	public static void main(String[] args) {
		
		System.out.println(Integer.toBinaryString(0b101011001 & 0b11000) +  "|" + (0b101011001) + "|" + 0b11000);
		System.out.println(Integer.toBinaryString(1<<7) +  "|" + (1<<7));
		System.out.println(Integer.toBinaryString(1197) +  "|" + (1197 & 128));
//		   10000000|128
//		10010101101|128
		
		System.out.println(new ValidUtf8().validUtf8(new int[] {255}));;
		System.out.println(Integer.toBinaryString(1197));

	}

}
