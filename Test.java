package com.giri.lambda;

@FunctionalInterface
public interface Test {

	String msg();
	
	public class Impl {

		public static void main(String[] args) {

			Test test = () -> {

				return "hello";

			};
			System.out.println(test.msg());
		}
	}

}
