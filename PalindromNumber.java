package com.giri.practice;

public class PalindromNumber {

	public static void main(String[] args) {

		int num = 121, rev = 0, remainder;
		
		int originalNum = num;

		while (num != 0) {
			remainder = num % 10;
			rev = rev * 10 + remainder;
			num = num / 10;
		}
		if (rev == originalNum) {
			System.out.println(originalNum + "is a Palindrom String");
		} else {
			System.out.println(originalNum + "is not a Palindrom String");
		}
	}

}
