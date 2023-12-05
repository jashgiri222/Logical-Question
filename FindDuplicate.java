package com.giri.streamprogram;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicate {
	
	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(5, 6, 7, 8, 23, 45, 67, 5, 6, 7, 45);
		
		list.stream().distinct().forEach(System.out::println);
		
		System.out.println("dis"+list);
		
		HashSet<Integer> set = new HashSet<>();

		Set<Integer> duplicate = list.stream().filter(t -> !set.add(t)).collect(Collectors.toSet());
		
		System.out.println("Duplicate Data" + duplicate);

	}

}
