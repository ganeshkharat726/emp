package singleone;
import java.util.ArrayList;
import java.util.Scanner;


import singleone.Employee;

import singleone.EmpHandlingException;

import static singleone.Employee.sdf;
import static singleone.Employee.Department.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Employee {
	private int id;
	private String name;
	private double salary;
//date of birth
	private Date dob;
	// dept
	private Department dept;
	//string --> date n date --> string
	public static SimpleDateFormat sdf;
	
	static {
		sdf=new SimpleDateFormat("dd-MM-yyyy");
	}
	
	
	public Employee(int id, String name, double salary, Date dob, Department dept) {
		super();

		this.id = id;
		this.name = name;
		this.salary = salary;
		this.dob = dob;
		this.dept = dept;
	}
	
	public Employee(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", dob=" + sdf.format(dob) + ", dept=" + dept + "]";
	}
	@Override
	public boolean equals(Object o)
	{
		System.out.println("in emp equals");
		if(o instanceof Employee)
			return this.id == ((Employee)o).id;//downcasting
		return false;
		
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	public enum Department {
		HR, RND, MARKETING, FINANCE, SALES
	}

	
	
}

 class EmpHandlingException extends Exception {
	public EmpHandlingException(String errMesg) {
		super(errMesg);
	}
}
 
 
 class EmpManagementSysytem {

		public static void main(String[] args) {
			try (Scanner sc = new Scanner(System.in)) {
				// create an empty Data structure (ArrayList) to store emp info.
				ArrayList<Employee> empList = new ArrayList<>();// size =0 capa=10
				boolean exit = false;
				while (!exit) {
					System.out.println("Options 1. Add Emp details 2. Display all emp details 3. Get Emp details "
							+ "4. Update Emp Details 5. Delete Emp details 100.Exit");
					System.out.println("Choose option");
					try {
						switch (sc.nextInt()) {
						case 1: // add emp details
							System.out.println("Enter emp details :  id,  name,  salary,  dob,  dept ");
							// API : add
							empList.add(new Employee(sc.nextInt(), sc.next(), sc.nextDouble(), sdf.parse(sc.next()),
									valueOf(sc.next().toUpperCase())));
							break;
						case 2: // display all emp details
							System.out.println("All Emp details");
							for (Employee e : empList)// iteration is from 0 ----size-1
								System.out.println(e);
							break;
						case 3: // get emp details
							System.out.println("enter emp id");
							Employee e = new Employee(sc.nextInt());
							int index = empList.indexOf(e);
							if (index == -1)
								throw new EmpHandlingException("Invalid emp id !!!!!!");
							System.out.println("Emp details " + empList.get(index));
							break;
						case 4:
							System.out.println("Enter emp id , salary increment n new dept id");
							// create emp ref from emp id
							e = new Employee(sc.nextInt());
							// indexOf
							index = empList.indexOf(e);
							// chk for -1
							if (index == -1)
								throw new EmpHandlingException("Can't Update details : Invalid emp id");
							// emp id valid : get emp details using index
							Employee ref = empList.get(index);
							// setters : setting salary
							ref.setSalary(ref.getSalary() + sc.nextDouble());
							// set dept
							Employee.Department dept = valueOf(sc.next().toUpperCase());
							ref.setDept(dept);
							System.out.println("Emp details updated.....");
							break;
							
						case 5 : System.out.println("Enter emp id to remove the details");
						// create emp ref from emp id
						e = new Employee(sc.nextInt());//e has only emp id
						// indexOf
						
						index = empList.indexOf(e);//internally calls equals
						// chk for -1
						if (index == -1)
							throw new EmpHandlingException("Can't delete details : Invalid emp id");
						// emp id valid : remove(index)
						System.out.println("Deleted emp details " + empList.remove(index));
							break;

						case 100:
							exit = true;
							break;
						}
					} catch (Exception e) {
						// e.printStackTrace();
						System.out.println(e);
					}
					// clear data from scanner
					sc.nextLine();
				}

			} // sc.close()

		}

	}


