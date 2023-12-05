package com.giri.lambda;

public class Impl {

	public static void main(String[] args) {

		Test1 test = (name) -> {

			return "hello , " + name;

		};
		System.out.println(test.wish("Jash"));
	}

}
