package com.will.leetcode;

public class BinarySearch {
	static int search(int[] arr, int num) {
		int start = 0;
		int end = arr.length -1;
		while(start <=end) {
			int middle = (start + end) /2 ;
			if (arr[middle] < num) {
				start = middle +1;
			} else {
				end = middle -1;
			} 
		}
		return start;
	}
	public static void main(String[] args) {
		System.out.println(search(new int[] {1,5,10,15,20,25,30,35,40,55,100}, 55));;
	}

}
