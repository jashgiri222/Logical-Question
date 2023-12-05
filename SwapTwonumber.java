package com.giri.practice;

public class SwapTwonumber {
	
	public static void main(String[] args) {
		
		int a = 10;
		int b = 40;
		
		System.out.println("before swap number a="+a+"b ="+b);
		
		  a =a+b;
	      b=a-b;
		 a=a-b;
		
		System.out.println( "After swap a="+a+"b="+b);
		
	}

}
