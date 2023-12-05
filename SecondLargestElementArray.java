package com.giri.logical;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SecondLargestElementArray {
	
	 public static void main(String[] args) {
		 
	        //given ArrayList
	        List<Integer> v = new ArrayList<>(Arrays.asList(12, 35, 1, 10, 34, 1));
	        
	        //inserting all the elements from ArrayList v to TreeSet s.
	        Set<Integer> s = new TreeSet<>(v);
	        
	        //clear the ArrayList.
	        v.clear();
	        
	        //insert all elements back in ArrayList in sorted order.
	        
	        for (int value : s) {
	            v.add(value);
	        }
	        //the size of updated ArrayList.
	        
	        int n = v.size();
	        
	        //printing the second largest element in ArrayList.
	        
	        System.out.print("The Second Largest Element in ArrayList is: ");
	        
	        System.out.println(v.get(n-2));
	    }

}
