package com.giri.logical;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Occurance {

	public static void main(String[] args) {

		// 1- java 8 find using "g"
		
		
		String data[] = { "giri", "jash" };
		List<String> n = Arrays.asList(data);
		n.stream().filter(t -> t.startsWith("g")).forEach(System.out::println);

		
		
		// 2-java 8
		
		
		String s = "aaaabbbbcccddddd";
		Map<Character, Integer> map = new HashMap<>();
		s.chars().forEach(e -> map.put((char) e, map.getOrDefault((char) e, 0) + 1));
		System.out.println("freq" + map);

      //3- normal
		
		
		String text = "aasjjikkk";

		char[] charArray = text.toCharArray();

		Map<Character, Integer> freqList = new LinkedHashMap<Character, Integer>();

		for (char key : charArray) {
			if (freqList.containsKey(key)) {
				freqList.put(key, freqList.get(key) + 1);
			} else
				freqList.put(key, 1);
		}
		System.out.println("freq" + freqList);
		
		
		// java 8
		
		List<String> list = Arrays.asList("Zohne", "Redy", "Zohne", "Redy", "Stome");
		
		Map<String, Long> collect = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println(collect);
	

	}
}