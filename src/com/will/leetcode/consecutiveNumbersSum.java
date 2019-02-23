package com.will.leetcode;

public class consecutiveNumbersSum {
    public int consecutiveNumbersSum(int N) {
        if (N <=1) return 1;
    	int count = 0;
    	for (int i = 1; i <= (int)Math.ceil(N/2.0); i++) {
    		if (((2*N) % i == 0) && (2*N/i - i+1) >0 && (2*N/i - i+1)%2 == 0) {
    			System.out.print((2*N/i - i+1)/2);
    			System.out.println(" "+i);
    			count++;
    		}
		}
    	return count;
    }
	public static void main(String[] args) {
		System.out.println(new consecutiveNumbersSum().consecutiveNumbersSum(674598122));

	}

}
