package com.giri.practice;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Occurance {
	public static void main(String[] args) {

		String str = "my name is giri";
       //Java 8 Approch
		/*
		 * Map<Character, Long> data = str.chars().mapToObj(c -> (char) c)
		 * .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		 * System.out.println("Data" + data);
		 */
		
		
		int freq[] = new int[str.length()];

		char string[] = str.toCharArray();

		for (int i = 0; i < str.length(); i++) {

			freq[i] = 1;

			for (int j = i + 1; j < string.length; j++) {
				if (string[i] == string[j]) {
					freq[i]++;
					string[j] = '0';

				}

			}
		}
		for (int i = 0; i < freq.length; i++) {

			if (string[i] != ' ' && string[i] != '0') {
				System.out.println(string[i] + "-" + freq[i]);
			}

		}

	}

}
