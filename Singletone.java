package com.giri.logical;

public class Singletone {
	
	public static Singletone singletone = null;

	@SuppressWarnings("unused")
	private void Singletoen() {

	}

	public static Singletone getInstant() {
		if (singletone == null) {
			singletone = new Singletone();

		}
		return singletone;

	}

	public static void main(String[] args) {
		Singletone a = Singletone.getInstant();
		Singletone b = Singletone.getInstant();
		Singletone c = Singletone.getInstant();
		System.out.println("Hashcode of a=" + a.hashCode());
		System.out.println("Hashcode of b=" + b.hashCode());
		System.out.println("Hashcode of c=" + c.hashCode());

	}
}
