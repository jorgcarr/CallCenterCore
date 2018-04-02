	package com.almundo.callcenter.core;

import com.almundo.callcenter.model.Employee;

/**
 * Clase encargada de controlar la asignación de empleados de acuerdo a
 * Prioridad por el tipo de empleado y si esta disponible
 * 
 * @author Jorge Caro
 * @since 1/4/2018
 * 
 */
public abstract class IEmployeeAssignerService {
	
	
	/**
	 * Obtiene de la lista actual (con mayor prioridad y con empleados disponibles) 
	 * uno de forma aleatoria y lo remueve de la lista actual.
	 * 
	 * @return el empleado libre de la lista acutal
	 */
	public abstract Employee getAvailableEmployee() ;
	
	/**
	 * Agregar un empleado a los dispobles
	 * 
	 * @param employee
	 */
	public abstract void addReleasedEmployee(Employee employee);
		
}