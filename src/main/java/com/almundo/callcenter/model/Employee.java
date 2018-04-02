package com.almundo.callcenter.model;


/**
 * @author Jorge Caro
 * @since 31/03/2018
 * 
 * Representación de un empleado
 * 
 */
public class Employee {
	
	private Integer id;
	private EmployeeType type;
	private String name;
	private EmployeeState state; 
	
	@Override
	public String toString() {
		return "Empleado[Nombre: " +name + ", Cargo: " +type +"]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setState(EmployeeState state) {
		this.state = state;
	}
	public EmployeeState getState() {
		return state;
	}
	
	
	

}
