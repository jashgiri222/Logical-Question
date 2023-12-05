package com.giri.logical;

import java.util.LinkedHashSet;

public class RemoveDuplicate {

	public static void main(String[] args) {
		
		/*
		 * List<Integer> list =Arrays.asList(1,2,3,4,5,6,2,3,4);
		 * 
		 * list.stream().distinct().forEach(System.out::println);
		 */		
		 int a[] = {5,2,6,8,6,7,5,2,8};
		
		LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
		
		 for (int i = 0; i < a.length; i++) 
			 
	            set.add(a[i]);
			
			System.out.println(set);
			
			
		}

	}


