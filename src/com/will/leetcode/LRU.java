package com.will.leetcode;

import java.util.HashMap;

class DNode {
	DNode pre;
	DNode next;
	int key;
	int value;
	@Override
	public String toString() {
		return "DNode [value=" + value + "]";
	}
	
}

class LRUCache {
	int capacity = 0;
	int lastRank  = 0;
	DNode last = new DNode();
	DNode first = new DNode();
	
	HashMap<Integer, DNode> index = new HashMap<Integer, DNode>(); 

    public LRUCache(int capacity) {
        this.capacity = capacity;
        last.pre = first;
        first.next = last;
    }
    
    void removeNode(DNode n) {
    	n.pre.next = n.next;
    	n.next.pre = n.pre;
    	n.next = null;
    	n.pre = null;
    }
    
    void addNode(DNode n) {
    	n.next = last;
    	n.pre = last.pre;
    	last.pre.next = n;
    	last.pre = n;
    }
    
    public int get(int key) {
    	if (index.containsKey(key)) {
    		//update queue
    		DNode current = index.get(key);
    		this.removeNode(current);
    		this.addNode(current);
    		
    		return index.get(key).value;
    	} else {
    		return -1;
    	}
    }
    
    public void put(int key, int value) {
    	
    	if (index.containsKey(key)) {
    		//simply move up
    		this.get(key);
    		index.get(key).value = value;
    		
    		
    	} else {//
	    	if (index.size() >=capacity) { //new key, too large, need evict first.
	    		//remove first
	    		int firstkey = this.first.next.key;
	    		this.removeNode(this.first.next);
	    		index.remove(firstkey);
	    		//add last
	    		DNode n = new DNode();
	    		n.key = key;
	    		n.value = value;
	    		this.addNode(n);
	    		
	    		index.put(key, n);
	    		
	    	} else {//new key, still has room, just add
	    		DNode n = new DNode();
	    		n.key = key;
	    		n.value = value;
	    		
	    		this.addNode(n);
	    		index.put(key, n);
	    	}
    	}
    	
    	
    }

}

public class LRU {
	public static void main(String[] args) {
		
		
//		["LRUCache","get","put","get","put","put","get","get"]
//				[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
		LRUCache cache = new LRUCache( 2 /* capacity */ );
		cache.put(2, 6);
		
		System.out.println(cache.get(1));;
		
		cache.put(1, 5);
		
		cache.put(1, 2);
		
		System.out.println(cache.get(1));;
		
		System.out.println(cache.get(2));
//		cache.put(1, 1);
//		System.out.println(cache.index + " " + cache.first + " " + cache.last);; 
//		cache.put(2, 2);
//		System.out.println(cache.index + " " + cache.first + " " + cache.last);; 
//		System.out.println(cache.get(1));;       // returns 1 
//		cache.put(3, 3);    // evicts key 2
//		System.out.println(cache.index + " " + cache.first + " " + cache.last);;
//		cache.get(2);       // returns -1 (not found)
//		System.out.println(cache.index + " " + cache.first + " " + cache.last);;
//		cache.put(4, 4);    // evicts key 1
//		System.out.println(cache.index + " " + cache.first + " " + cache.last);;
//		System.out.println(cache.get(1));;       // returns -1 (not found)
//		System.out.println(cache.get(3));;       // returns 3
//		System.out.println(cache.get(4));;       // returns 4
	}
}
