package com.giri.practice;

import java.util.LinkedHashSet;

public class RemoveDuplicate {

	public static void main(String[] args) {
		
		int a[] = { 1, 2, 3, 1, 2, 3, 456, 456, 342 };

		LinkedHashSet<Integer> set = new LinkedHashSet<>();
		
		for(int i=0;i<a.length;i++) 
			
			set.add(a[i]);
		
		System.out.println(set);
	}

}
