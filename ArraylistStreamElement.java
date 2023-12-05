package com.giri.practice.streamapi;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArraylistStreamElement {

	public static void main(String[] args) {

		String str = "my name is giri";
		
		List<Integer> intlist = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 56, 7, 89);
		
		List<String> li = Arrays.asList("my", "name", "is", "giri");
		
		List<Integer> list = Arrays.asList(12, 34, 56, 78, 9, 1, 3, 4, 6);
		
		
		Map<String, Long> word = li.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println("word" + word);

		Map<Character, Long> freq = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("Freq Data" + freq);

		Map<Integer, Long> int1 = intlist.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println("frq in Number" + int1);

		List<Integer> l1 = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
		System.out.println("Even No=" + l1);

		List<Integer> l = list.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());
		System.out.println("Odd No=" + l);

		long data = list.stream().filter(i -> i % 2 != 0).mapToInt(n -> n).sum();
		System.out.println("Sum of Odd No=" + data);

		list.stream().sorted().forEach(System.out::println);

		list.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);

		HashSet<Integer> set = new HashSet<>();
		
		list.stream().filter(t -> !set.add(t)).forEach(System.out::println);

	}

}
