package com.will.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.IntStream;

public class NextClosestTime {
    public String nextClosestTime(String time) {
    	HashSet<String> s = new HashSet<String>();//list of numbers
    	for (int m = 0; m < time.length();m++) {
    		if (time.charAt(m) != ':') {
    			s.add(String.valueOf(time.charAt(m)));
    		}
    	}
    	
    	
    	StringBuilder sb = new StringBuilder();
    	
    	ArrayList<String> alloptions = this.nextHelper(new ArrayList(s), 0);
    	
    	int min = Integer.MAX_VALUE;
    	String found = "";
    	for (String option: alloptions) {
    		int diff = this.minDifference(time, option);
    		System.out.println(diff + "|" + time + " " + option);
    		if (diff < min && diff != 0) {
    			found = option;
    			min = diff;
    		}
    	}
//    	System.out.println(found);
    	return found.equals("") ? time: found;
    }
    
    ArrayList<String> nextHelper(ArrayList<String> l, int level) {
    	if (level == 4) return l ;
    	
    	ArrayList<String> newresult = new ArrayList<String>();
    	ArrayList<String> childResult = this.nextHelper(l, level+1);
    	if (level == 2) {
    		for (String childStr : childResult) {
				newresult.add(":" + childStr);
			}
    	}
    		
		for (int i = 0; i < l.size(); i++ ) {
    		if (level == 0) {
    			if (Integer.valueOf(l.get(i))<=2) {
    				for (String childStr : childResult) {
    					newresult.add(l.get(i) + childStr);
    				}
    			}
    		} else if (level == 1) {	
				for (String childStr : childResult) {
					newresult.add(l.get(i) + childStr);
				}    			
    		}else if (level == 2) {
    			//
    		}else if (level == 3) {
    			if (Integer.valueOf(l.get(i))<=5) {
    				for (String childStr : childResult) {
    					newresult.add(l.get(i) + childStr);
    				}
    			}
    		}else if (level == 4) {
    			newresult.add(l.get(i));
    		}
    	}
		System.out.println(newresult);
    	
    	
    	
    	return newresult;
    }
    
    int minDifference(String start, String end) {
    	int sHour = Integer.valueOf(start.split(":")[0]);
    	int sMin = Integer.valueOf(start.split(":")[1]);
    	int minInDayS = sHour*60+ sMin;
    	
    	int eHour = Integer.valueOf(end.split(":")[0]);
    	int eMin = Integer.valueOf(end.split(":")[1]);
    	
    	int minInDayE = eHour*60+ eMin;
    	
    	if (sHour > 24 || eHour > 24) {
    		return Integer.MAX_VALUE;
    	}
    	
    	if (minInDayE < minInDayS) {//second day scenario
    		return minInDayE + (24*60 -minInDayS ) ;
    	} else { //same day scenario
    		return minInDayE - minInDayS ;
    	}
    }
    
	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		IntStream.range(0, l.size()).forEach(i->{
			System.out.println(l.get(i));
		});
		for (Integer h1 : l) for (Integer h2 : l)  {
			System.out.println(h1 + " " + h2);
		}
		
		for (int i =0;i< l.size();i++) if (i < 2) {
			System.out.println(i);
		}
//		System.out.println(new NextClosestTime().nextClosestTime("00:00"));;
	}

}
