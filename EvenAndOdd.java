package com.giri.streamprogram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EvenAndOdd {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(5, 6, 7, 8, 9, 12, 23, 34);
		
		list.stream().sorted().forEach(System.out::print);
		
		list.stream().sorted(Collections.reverseOrder()).forEach(System.out::print);
		
		//Even Numbers
		List<Integer> even = list.stream().filter(n->n%2==0).collect(Collectors.toList());
		System.out.println("EVEN= "+even);
		
		//Odd Numbers
		List<Integer> odd = list.stream().filter(i->i%2!=0).collect(Collectors.toList());
		System.out.println("ODD= "+odd);

	}

}
