package com.giri.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SecondLargestInArray {
	
	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2, 3, 4, 5, 6, 4, 5, 6));

		Set<Integer> set = new TreeSet<>(list);

		list.clear();

		for (int data : set) {
			
			list.add(data);

		}
		int n = list.size();
		
		System.out.println(list.get(n-2));
	}

}
