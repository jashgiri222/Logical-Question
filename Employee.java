package com.giri.practice.streamapi;

public class Employee {
	private int empId;
	private String empName;
	private String deptName;
	private String companyName;
	private Double salary;
	private int age;
	private String gender;

	public Employee(int empId, String empName, String deptName, String companyName, Double salary,int age,String gender) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.deptName = deptName;
		this.companyName = companyName;
		this.salary = salary;
		this.age = age;
		this.gender = gender;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", deptName=" + deptName + ", companyName="
				+ companyName + ", salary=" + salary + ", age=" + age + ", gender=" + gender + "]";
	}

	

}
