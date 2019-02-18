package com.will.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordBreak {
    public boolean wordBreakHelper(String s, List<String> wordDict, HashMap<String, Boolean> map) {
    	if (s.length() == 0) {
    		return true;
    	}
    	boolean result = false;
    	for (int i = 0; i < wordDict.size(); i++) {
    		String word = wordDict.get(i);
    		if (s.startsWith(word)) {
    			result = map.computeIfAbsent(s.substring(word.length()), k->this.wordBreakHelper(k, wordDict, map));
//    			System.out.println(map);
    		    if (result == true) {
    		    	return true;
    		    }
    		}
		}
        return false;
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
    	return this.wordBreakHelper(s, wordDict, new HashMap<String, Boolean>());
    	
    }
    
	public static void main(String[] args) {
		//"cats", "dog", "sand", "and", "cat"
//		"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
//		["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
		System.out.println(new WordBreak().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", new ArrayList<String>(Arrays.asList(new String[] {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"}))));
	}
}
