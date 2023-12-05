package com.giri.practice;

public class Singletone {
	
	private static Singletone st=null;

	private Singletone() {
		
		super();
	}

	public static Singletone getSingletone() {
		
		if (st == null) {
			
			st = new Singletone();
		}
		
		return st;
	}

	public static void main(String[] args) {
		
		Singletone s1 = Singletone.getSingletone();
		Singletone s2 = Singletone.getSingletone();
		Singletone s3 = Singletone.getSingletone();
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
	}

}
