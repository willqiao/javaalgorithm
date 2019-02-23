package com.will.leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
    	if (people.length <=1) return people;
    	
    	HashMap map = new HashMap();
    	for (int i = 0; i < people.length; i++) {
			map.put(people[i], people[i][1]);
		}
    	for (int i = 0; i < people.length; i++) {
    		int index = this.findBestOption(people, i);
    		int[] tmp = people[i];
    		people[i] = people[index];
    		people[index] = tmp;
    		
    		
    		int height = people[i][0];
    		for (int j = i+1; j < people.length; j++) {
				if (people[j][0] <= height) {
					people[j][1]=people[j][1]-1;
				}
			}
    		
    		
		}
    	
    	for (int i = 0; i < people.length; i++) {
			people[i][1] = (Integer)map.get(people[i]);
		}
    	
        return people;
    }
    
    int findBestOption (int[][] people, int s) {
    	int min = Integer.MAX_VALUE;
    	int returnIndex = 0;
    	for (int i = s; i < people.length; i++) {
			if (people[i][1] == 0 && people[i][0] < min) {
				min = people[i][0];
				returnIndex = i;
			}
		}
    	return returnIndex;
    	
    }
    
	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(new ReconstructQueue().reconstructQueue(new int[][] {
			{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}
		})));

	}

}
