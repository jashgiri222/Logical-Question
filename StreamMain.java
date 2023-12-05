package com.giri.streamprogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {

	public static void main(String[] args) {
		
		
		List<Integer>giri=Arrays.asList(4,5,6,7);
		
		
		
		//Integer i=IntStream.of(1,4,2,7,5).reduce(1, (a, b) -> a * b);
		//System.out.println("hh"+i);
		
		

		
	Integer giri1	= giri.stream().max(Comparator.comparing(Integer::valueOf)).get();
	
	
		System.out.println("giri"+giri1);

		List<Employee> emp = new ArrayList<>();

		emp.add(new Employee(101, "Hari", "DEV", "hcl", 98000.00,30,"MALE"));
		emp.add(new Employee(102, "Ram", "DEV", "hcl", 28000.00,20,"FEMALE"));
		emp.add(new Employee(103, "madhu", "test", "CTS", 78000.00,18,"MALE"));
		emp.add(new Employee(104, "Raaj", "test", "CTS", 68000.00,23,"FEMALE"));
		emp.add(new Employee(105, "pintu", "HR", "info", 58000.00,20,"FEMALE"));
		emp.add(new Employee(106, "Gautam", "HR", "info", 48000.00,25,"FEMALE"));

		// grouping employee by DEPT

		Map<String, List<Employee>> groupingDept = emp.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.toList()));

		groupingDept.entrySet().forEach(data -> {

			System.out.println(data.getKey() + "-----" + data.getValue());
		});
		
		// grouping employee by DEPT and Count

		Map<String, Long> groupingDeptCount= emp.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));

		groupingDeptCount.entrySet().forEach(data -> {

			System.out.println(data.getKey() + "-----" + data.getValue());
		});
		
		// Max salary
		Optional<Employee> maxsalary = emp.stream().max(Comparator.comparing(Employee::getSalary));
		System.out.println(maxsalary.get());
		
		// Min salary
		Optional<Employee> minsalary = emp.stream().min(Comparator.comparing(Employee::getSalary));
		System.out.println(minsalary.get());
		
		// Each department Max salary
		
		Map<String, Optional<Employee>> eachDeptSalary = emp.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));
		
		eachDeptSalary.entrySet().forEach(data->{
			System.out.println(data.getKey()+"-----"+data.getValue());
			
		});
		
		Long dev =emp.stream().filter(x->x.getDeptName().equals("DEV")).count();
		
		System.out.println(dev);
		
		Stream<String> empName =emp.stream().filter(x->x.getEmpName().startsWith("R")).map(Employee::getEmpName);
		
		empName.forEach(res->{
			
			System.out.println(res);
		});
		
		// sort employee
		
		emp.stream().sorted(Comparator.comparing(Employee::getEmpName,Collections.reverseOrder())).forEach(System.out::println);
		
		emp.stream().sorted(Comparator.comparing(Employee::getEmpName)).forEach(System.out::println);
		
		
		
		emp.stream().filter(x->x.getSalary()>50000).forEach(System.out::print);
		
		// distinct Dept
		
		emp.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);
		
		
		//grouping by Gender and Avrg by age

		
		Map<String, Double > avgAge = emp.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingLong(Employee::getAge)));
		System.out.println("Average Age="+avgAge);
		
		//emp.stream().collect(Collectors.groupingBy(Employee::get));
	}

}
