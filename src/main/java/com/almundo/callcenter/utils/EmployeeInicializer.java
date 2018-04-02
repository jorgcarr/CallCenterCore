package com.almundo.callcenter.utils;

import java.util.ArrayList;
import java.util.List;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.EmployeeState;
import com.almundo.callcenter.model.EmployeeType;

public class EmployeeInicializer {
	
	private static final int NUMBER_OF_OPERATORS = 6;
	private static final int NUMBER_OF_SUPERVISORS = 4;
	private static final int NUMBER_OF_MANAGERS = 3;

	public static List<Employee> inicialiceEmployeeList() {
		
		List<Employee> employeesList = new ArrayList<Employee>();
		
		employeesList.addAll(createEmployeesList(NUMBER_OF_OPERATORS, EmployeeType.OPERATOR));
		employeesList.addAll(createEmployeesList(NUMBER_OF_SUPERVISORS, EmployeeType.SUPERVISOR));
		employeesList.addAll(createEmployeesList(NUMBER_OF_MANAGERS, EmployeeType.MANAGER));
		
		return employeesList;
	}
	
	private static List<Employee> createEmployeesList(int quantity,
			EmployeeType group) {
		
		List<Employee> employees = new ArrayList<Employee>();

		for (int i = quantity; i >= 1; i--) {
			employees.add(createEmployee(i, group));
		}

		return employees;
	}
	
	private static Employee createEmployee(int number, EmployeeType group) {

		Employee e = new Employee();
		e.setId(number);
		e.setName(group.name() + " " + e.getId());
		e.setType(group);
		e.setState(EmployeeState.FREE);
		return e;
	}
}
