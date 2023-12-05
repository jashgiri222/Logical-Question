package com.giri.practice.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {
	
	public static void main(String[] args) {
		
		List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee(101,"Giri","CTS"));
        list.add(new Employee(102,"Hari","TCS"));
        list.add(new Employee(103,"Ram","IBM"));
		
		list.stream().sorted(Comparator.comparing(Employee::getEmpId,Collections.reverseOrder())).forEach(System.out::println);;
		

       Collections.sort(list, new EmpIdComparator());
       
       list.forEach(data->{
    	   
    	   System.out.println("Comparing by ID="+data);
       });
       
       
     Collections.sort(list, new EmpNameComparator());
       
       list.forEach(data->{
    	   
    	   System.out.println("Comparing by Name="+data);
       });
       
		}
		
	}


