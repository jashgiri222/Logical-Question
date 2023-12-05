package com.giri.practice.sorting;

import java.util.Comparator;

public class EmpIdComparator  implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		
		if(o1==o2) {
			return 0;	
		}else if(o1.getEmpId()<o2.getEmpId()) {
			return 1;
		}else {
			return -1;
		}
		
	}

}
