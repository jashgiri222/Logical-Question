package com.giri.practice.streamapi;

public final class Immutable {

	private final int i;

	public Immutable(int i) {
		super();
		this.i = i;
	}

	public Immutable modify(int i) {
		if (this.i == i) {

			return this;
		} else {
			return (new Immutable(i));
		}

	}

	public static void main(String[] args) {
		Immutable im = new Immutable(10);
		Immutable im2 = im.modify(100);
		Immutable im3 = im.modify(10);
		System.out.println(im == im2);
		System.out.println(im == im3);
		Immutable im4 = im.modify(100);
		System.out.println(im2 == im4);

	}
}
