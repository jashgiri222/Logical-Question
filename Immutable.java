package com.giri.logical;

public final  class Immutable {
	
	private final int empId;
	
	public Immutable(int empId) {
		
		this.empId=empId;
	}

	public int getEmpId() {
		return empId;
	}
	
	public static void main(String[] args) {
		
		Immutable im=new Immutable(2200556);
		
		System.out.println("EmpId="+im.getEmpId());
		
		Immutable im2=new Immutable(2200557);
		
		
		System.out.println("EmpId2="+im.getEmpId());
		
		Immutable im3=new Immutable(2200558);
		
		System.out.println("EmpId2="+im3.getEmpId());
		
		System.out.println(im==im2);
		
		
		
	}

}
