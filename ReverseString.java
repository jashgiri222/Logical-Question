package com.giri.practice;

public class ReverseString {

	public static void main(String[] args) {
		String str = "my name is giri";
		
		char string[] = str.toCharArray();
		
		String rev = "";
		
		for (int i = string.length - 1; i >= 0; i--) {
			rev+=string[i];

		}
		System.out.println(rev);

	}
	
}
