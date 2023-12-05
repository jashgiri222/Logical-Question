package com.giri.practice.streamapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class EmployeeMain {

	public static void main(String[] args) {

		List<Employee> list = new ArrayList<Employee>();

		list.add(new Employee(101, "Hari", "DEV", "hcl", 98000.00, 30, "MALE"));
		list.add(new Employee(102, "Ram", "DEV", "hcl", 28000.00, 20, "FEMALE"));
		list.add(new Employee(103, "madhu", "test", "CTS", 78000.00, 18, "MALE"));
		list.add(new Employee(104, "Raaj", "test", "CTS", 68000.00, 23, "FEMALE"));
		list.add(new Employee(105, "pintu", "HR", "info", 58000.00, 20, "FEMALE"));
		list.add(new Employee(106, "Gautam", "HR", "info", 48000.00, 25, "FEMALE"));

		Map<String, List<Employee>> data = list.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.toList()));
		System.out.println(data);

		Map<String, Long> data1 = list.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
		System.out.println(data1);
		Optional<Employee> max = list.stream().max(Comparator.comparing(Employee::getSalary));
		System.out.println(max);
		Optional<Employee> min = list.stream().min(Comparator.comparing(Employee::getSalary));
        //****IMP
		Map<String, Optional<Employee>> t = list.stream().collect(Collectors.groupingBy(Employee::getDeptName,
				Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));
		
		 list.stream().sorted(Comparator.comparing(Employee::getEmpId)).forEach(System.out::println);
		 
		 list.stream().sorted(Comparator.comparing(Employee::getEmpId,Collections.reverseOrder())).forEach(System.out::println);
		 
		 list.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);
		 System.out.println("------------------------------------------");
		 
		 list.stream().filter(i->i.getAge()>=30 && i.getSalary()>47000).forEach(System.out::println);
		 
		 list.stream().filter(i->i.getDeptName().equalsIgnoreCase("dev")).map(Employee::getDeptName).forEach(System.out::println);;
		 
		Map<String,Double> z=list.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge)));
		
		z.entrySet().forEach(y->{
			System.out.println(y.getKey()+"-"+y.getValue());
			
		});
		 
		
	}

}
