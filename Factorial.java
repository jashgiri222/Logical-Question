package com.giri.practice;

public class Factorial {
	
	public static void main(String[] args) {

		int fact = 1;
		int num = 10;

		for (int i = 1; i <= num; i++) {

			fact = fact * i;

		}
		System.out.println("Factorial of " + fact);

	}

}
