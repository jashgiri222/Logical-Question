package com.giri.logical;

public class SwapTwoNumber {
	
	public static void main(String[] args) {

		int a = 10, b = 20;
		System.out.println("Before Swap a=="+a+"Before Swap b=="+b);
		
		a=a+b;
		b=a-b;
		a=a-b;
		System.out.println("After swap a=="+a+"After Swap b=="+b);
	}


}
