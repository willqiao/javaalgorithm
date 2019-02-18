package com.will.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordBreakII {
	HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>> ();
	
    public List<String> wordBreak(String s, List<String> wordDict) {	
    	LinkedList<String> result = this.helper(s, new HashSet(wordDict));
//    	System.out.println(map);
    	
    	return result == null? new ArrayList<String>(): new ArrayList<String>(result);
    }
    
    LinkedList<String> helper(String s, Set<String> wordDict) {
    	if (s.length() == 0) {
    		LinkedList l = new LinkedList<String>();
    		l.add("");
    		return l;
    	}
    	if (map.get(s) != null) {
    		return map.get(s);
    	}
    	
    	
    	LinkedList<String> newResult = null;
    	for (int i = 1; i < s.length()+1; i++) {
			String left = s.substring(0, i);
			String right = s.substring(i);
//			System.out.println(left + "|" + right + wordDict);
			if (wordDict.contains(left)) {
                
				LinkedList<String> childSet = map.computeIfAbsent(right, k->this.helper(k, wordDict));
//				System.out.println(right + childSet);
				if (childSet != null) {
					if (newResult == null) newResult = new LinkedList<String>();
                    for (int j = 0; j < childSet.size();j++) {
                        String child = childSet.get(j);
						boolean tmp = child.equals("") ? newResult.add(left): newResult.add(left + " " + child);
                    }
				}
			} else {
				//didnt find, continue.
			}
		}
    	System.out.println(map.size());
    	return newResult;
    }
    
	public static void main(String[] args) {
//		"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//		["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
		
//		System.out.println(new WordBreakII().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new ArrayList<String>(Arrays.asList(new String[] {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"}))).size());;
		System.out.println(new WordBreakII().wordBreak("catsanddogcatsanddogcatsanddog", new ArrayList<String>(Arrays.asList(new String[] {"cat", "cats", "and", "sand", "dog"}))));;

	}

}
