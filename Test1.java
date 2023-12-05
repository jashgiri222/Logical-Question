package com.giri.lambda;

@FunctionalInterface
public interface Test1 {

	String wish(String name);

	public class Impl {
		
		public static void main(String[] args) {

			Test1 test1 = (name) -> {

				return "Hello" + name;

			};

			System.out.println(test1.wish("giri"));

		}
	}
}
