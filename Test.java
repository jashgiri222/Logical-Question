package com.giri.logical;

public class Test {
	
	   public static void main(String args[])
	    {
	        String s1 = "abc";
	        String s2 = s1;
	        s1 += "d";
	        System.out.println(s1 + " " + s2 + " " + (s1 == s2) + " " + s1.equals(s2));

	        StringBuffer sb1 = new StringBuffer("abc");
	        StringBuffer sb2 = sb1;
	        sb1.append("d");
	        System.out.println(sb1 + " " + sb2 + " " + (sb1 == sb2) + " " + sb1.equals(sb2));
	    }

}
