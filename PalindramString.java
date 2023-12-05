package com.giri.logical;

public class PalindramString {

	public static void main(String[] args) {
		
		String s = "nitin";
		//String s="java";
		String rev = "";
		
		char[] c = s.toCharArray();

		for (int i = c.length-1;i>=0;i--) {
			
			rev += s.charAt(i);

		}
		if (rev.equals(s)) {

			System.out.println(s+" is Palindrom");

		} else {
			System.out.println(s+" Not a Palindrom");

		}

	}
}
