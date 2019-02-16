package com.will.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node{
	String node;
	double value;
	public Node(String node, double value) {
		super();
		this.node = node;
		this.value = value;
	}
	@Override
	public String toString() {
		return "[node=" + node + ", value=" + value + "]";
	}
}

class Graph{
	HashMap<String, ArrayList> ajacency = new HashMap<String, ArrayList> ();
	
	void addEdges(String a, String b, double value) {
		ajacency.computeIfAbsent(a, k-> new ArrayList()).add(new Node(b, value));
		ajacency.computeIfAbsent(b, k-> new ArrayList()).add(new Node(a, 1.0/value));
	}
	
	boolean findPath(String a, String b, Set passes, ArrayList nodes) {
		ArrayList l = ajacency.get(a);
		if (l == null) return false;
		
		if (a.equals(b)) {
			return true;
		}
		
		passes.add(a);
		
		for (int i = 0; i < l.size();i++) { 
			
			Node n = (Node)l.get(i);
			if (!passes.contains(n.node)) {
				boolean result = this.findPath(n.node, b, passes, nodes);
				if (result == false) {//continue
					continue;
				} else {//found
					nodes.add(n);
					return true;
				}
			}
		}
		
		return false;
	}
	
}

public class EvaluateDivision {
	
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    	Graph g = new Graph();
    	for (int i = 0; i < equations.length;i++) {
    		g.addEdges(equations[i][0], equations[i][1], values[i]);
    	}
//    	System.out.println(g.ajacency);
    	
    	double[] results = new double[queries.length];
    	for (int i = 0; i < queries.length;i++) {
	    	ArrayList mylist = new ArrayList();
	    	boolean found = g.findPath(queries[i][0], queries[i][1], new HashSet(), mylist);
	    	System.out.println(Arrays.toString(results) + mylist + found);
	    	if (found ) {
	    		if (!mylist.isEmpty()) {
	    		Node n = (Node)mylist.stream().reduce((n1, n2)-> new Node("", ((Node)n1).value * ((Node)n2).value)).get();
	    		results[i]  = n.value;
	    		} else {
	    			results[i]  = 1.0;
	    		}
	    	} else {
	    		results[i] = -1.0;
	    	}
	    	
	    	
    	}
    	
        return results;
    }
    
    
    
	public static void main(String[] args) {
		new EvaluateDivision().calcEquation(new String[][] {{"a", "b"} , {"b", "c"}} , new double[] {1.0,2.0},  new String[][] { {"a","c"},{"b","c"},{"a","e"},{"a","a"},{"x","x"}});
	}
}
