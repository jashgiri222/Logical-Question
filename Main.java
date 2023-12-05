package com.giri.streamprogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) {
		
		List<Employee> emp = new ArrayList<>();

		emp.add(new Employee(101, "Hari", "DEV", "hcl", 98000.00,30,"MALE"));
		emp.add(new Employee(102, "Ram", "DEV", "hcl", 28000.00,20,"FEMALE"));
		emp.add(new Employee(103, "madhu", "test", "CTS", 78000.00,18,"MALE"));
		emp.add(new Employee(104, "Raaj", "test", "CTS", 68000.00,23,"FEMALE"));
		emp.add(new Employee(105, "pintu", "HR", "info", 58000.00,20,"FEMALE"));
		emp.add(new Employee(106, "Gautam", "HR", "info", 48000.00,25,"FEMALE"));
		
		
	Map<String, List<Employee>>groupDept = emp.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.toList()));
		System.out.println("GroupDept="+groupDept);
		
		
	Map<String, Long>	eachDeptCount =emp.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.counting()));
	
	System.out.println("eachDeptCount"+eachDeptCount);
	
	
	emp.stream().max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);
	emp.stream().min(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);
		
	Map<String, Optional<Employee>> data =emp.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));
	System.out.println("Data"+data);
	
	Map<String, Double>avgData=emp.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge)));
	System.out.println("AVgData"+avgData);
	
	
	List<Integer>list=Arrays.asList(1,2,3,3,9,9,5,5);
	// find odd number and sum all
	Integer d=list.stream().filter(t -> t % 2 != 0).mapToInt(n->n).sum();
	
	System.out.println("sum="+d);
	
	list.stream().distinct().forEach(System.out::println);
		
	}

}
