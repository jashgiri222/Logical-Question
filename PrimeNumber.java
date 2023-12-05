package com.giri.practice;

public class PrimeNumber {
	public static void main(String[] args) {

		int num = 11;// the number which is divisible by 1 and itself called prime number.
		
		Boolean isPrime = true;
		
		for (int i = 2; i <= num / 2; i++) {

			if (num % i == 0) {

				isPrime = false;
			}

		}
		if (isPrime) {
			System.out.println(+num + "is a Prime number");
		} else {
			System.out.println(+num + "is not a Prime Number");
		}

	}

}
