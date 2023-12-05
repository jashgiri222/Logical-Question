package com.giri.practice;

public class PalindromString {
	public static void main(String[] args) {
		
		String s = "nitin", 
				rev = "";

		char string[] = s.toCharArray();
		
		for(int i=string.length-1;i>=0;i--) {
			
			rev+=string[i];
		}
		if(rev.equals(s)) {
			
		System.out.println(s+"is a palindrom string");	
		}
		else {
			System.out.println(s+"is not  a palindrom string");	
			
		}
	}

}
