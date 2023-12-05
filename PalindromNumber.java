package com.giri.logical;

public class PalindromNumber {
	
	
	public static void main(String[] args) {
		
	int num = 3553, reverse= 0, remainder;

	// store the number to originalNum
	int originalNum =num;

	// get the reverse of originalNum
	// store it in variable
	while(num!=0)
	{
		remainder = num % 10;
		reverse = reverse * 10 + remainder;
		num =num/ 10;
	}

	// check if reversedNum and originalNum are equal
	if(originalNum==reverse)
	{
		System.out.println(originalNum + " is Palindrome.");
	}else
	{
		System.out.println(originalNum + " is not Palindrome.");
	}
	

}

}
