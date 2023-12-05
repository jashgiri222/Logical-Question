package com.giri.practice;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Occu {

	public static void main(String[] args) {

		String s = "my name is giri";
		Map<Character, Long> data=s.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(data);
		int freq[] = new int[s.length()];
		
		char string[] = s.toCharArray();
		
		
		for(int i=0;i<string.length;i++) {
			freq[i]=1;
			for(int j=i+1;j<string.length;j++) {
				if(string[i]==string[j]) {
					freq[i]++;
					string[j]='0';
					
				}
			}
		}
		for(int i=0;i<freq.length;i++) {
			
			if(string[i]!=' ' && string[i]!='0') {
				System.out.println(string[i]+"-"+freq[i]);
				
				
			}
		}

	}

}
