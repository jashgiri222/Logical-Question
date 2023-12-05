package com.giri.practice;

public class FactorialUsingRecuersion {
	
	public static int fact(int n) {
		
		if(n==1) {
			return 1;
		}
		return n*fact(n-1);
		
	}
	public static void main(String[] args) {
		
		int n=5;
		int fact = fact(n);
		
		System.out.println("Factorila of "+n+ "is :"+fact);
	}

}
