package com.giri.lambda;

@FunctionalInterface
public interface Test2 {

	//int add(int a, int b);
	
	
	String msg();

	public class Impl {
		
		public static void main(String[] args) {
			
			Test2 test = () -> {
				return "Hello Giri";

			};
			System.out.println(test.msg());

		}

	}

}
