package com.giri.lambda;

public class Impl3 {
	
	public static void main(String[] args) {

		Test2 test = (a, b) -> a + b;

		System.out.println(test.add(20, 30));

	}

}
