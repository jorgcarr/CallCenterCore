package com.almundo.callcenter.core;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.EmployeeState;

public final class CallConcurrentController {
	
	public static final int CAPACITY = 10;
	public static int concurrentCounter = 0;
	
	private static ExecutorService executor = Executors.newFixedThreadPool(CAPACITY);;
	
	public static void tryCall(Call call) {
		
		CallConcurrentController.verifyCapacity();
		CallConcurrentController.assignEmployee(call);			
		CallConcurrentController.startCall(call);
		
	}
	
	public static void verifyCapacity() {
		if(concurrentCounter >= CAPACITY) {
			throw new IllegalStateException ("No hay capacidad");
		}		
	}
	
	public static void startCall(Call call) {
		concurrentCounter = concurrentCounter+1;
		Callable<Integer> task = new CallTask(call);
		call.getEmployee().setState(EmployeeState.ON_CALL);
		executor.submit(task);
		System.out.println("Contador de concurrencia Start: " + concurrentCounter);
	}
	
	public static void hangOut(Call call) {
		
		IEmployeeAssignerService employeeAssigner = EmployeeAssigner.getInstance();
    	employeeAssigner.addReleasedEmployee(call.getEmployee());
    	concurrentCounter = concurrentCounter -1;
    	System.out.println("Contador de concurrencia HangOut: " + concurrentCounter);
	}
			
	public static synchronized void assignEmployee(Call call) {
		IEmployeeAssignerService assignerService = EmployeeAssigner.getInstance();
		Employee employee = assignerService.getAvailableEmployee();
		call.setEmployee(employee);
		call.setAssignedEmployee(true);
		System.out.println("Empleado Asignado: " + employee);
	}
	
}
