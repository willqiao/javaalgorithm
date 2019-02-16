package com.will.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
//    	Arrays.sort(coins);
    	
    	
    	return this.coinHelper(coins, amount, new HashMap());
    }
    
    public int coinHelper(int[] sortCoins, int amount, Map data) {
    	if (amount == 0) {
    		return 0;
    	}
    	if (data.get(amount) != null) return (Integer)data.get(amount);
    	
    	int mintotal = Integer.MAX_VALUE;
    	int foundamount = amount;
    	for (int i = sortCoins.length -1; i >=0;i-- ) {
    		if (sortCoins[i] <= amount) {
    			
    			int child = this.coinHelper(sortCoins, amount - sortCoins[i], data);
    			if (child == -1) {//didnt found
    				continue;
    			}
    			if (1+child < mintotal) {
    				mintotal = 1+child;
    				foundamount = amount - sortCoins[i];
    			}
    		}
    	}
    	
    	if (mintotal == Integer.MAX_VALUE) {
    		data.put(amount, -1);
    		System.out.println(data);
    		return -1;
    	} else {
    		data.put(amount, mintotal);
    		System.out.println(data);
    		return mintotal;
    	}
    }
    
	public static void main(String[] args) {
		System.out.println(new CoinChange().coinChange(new int[]{2},3));;
//		System.out.println(new CoinChange().coinChange(new int[]{186,419,83,408}, 6249));;
		//   3,  7, 100,    111=>

	}

}
