package com.giri.streamprogram;

import java.util.Arrays;

import java.util.List;

public class All {
	public static void main(String args[]) {

		List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 32);

		// 1. find out all the numbers starting with 1 using Stream functions?

		myList.stream().map(s -> s + "") // Convert integer to String
				.filter(s -> s.startsWith("1")).forEach(System.out::println);

		// 2. find the first element of the list using Stream functions?
		
		myList.stream().findFirst().ifPresent(System.out::println);

		// 3.find the total number of elements present in the list using Stream functions?

		long count = myList.stream().count();
		System.out.println(count);

		// 4.find the maximum value element present in it using Stream functions?

		int max = myList.stream().max(Integer::compare).get();
		System.out.println(max);
		
		// divisible by 2
		Boolean divisible2 =myList.stream().anyMatch(n->n%2==0);
		
		if(divisible2) {
			System.out.println("at least 1 number is divisible by 2");
		}else {
			
		
		System.out.println("No number is divisible by 2");
		}

	}

}
