package com.giri.streamprogram;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OcuuranceUsingStream {

	public static void main(String[] args) {

		String str = "my name is giri";
		

		Map<Character, Long> frequency = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


		System.out.println(frequency);

		List<String> list = Arrays.asList("my", "name", "is", "giri");
		
		List<Integer> intlist = Arrays.asList(1,2,3,4,5,6,7,1,2,3,4,56,7,89);

		Map<String, Long> collect = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println(collect);
		
		Map<Integer, Long>data=intlist.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(data);
	}

}
