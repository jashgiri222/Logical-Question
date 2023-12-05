package com.giri.practice.sorting;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SortMap {

	static Map<Integer, String> map = new HashMap<>();

	public static void sort() {
		
		TreeMap<Integer, String> treeMap =  new TreeMap<>();
		
		treeMap.putAll(map);
		
		 for (Map.Entry<Integer, String> entry : treeMap.entrySet())
	            System.out.println("Key = " + entry.getKey() +
	                         ", Value = " + entry.getValue()); 
		
	}
	public static void main(String[] args) {
		    map.put(1,"Jayant");
	        map.put(2,"Abhishek");
	        map.put(3,"Anushka");
	        map.put(4,"Amit");
	        map.put(5,"Danish");
	        sort();
		
	}

}
