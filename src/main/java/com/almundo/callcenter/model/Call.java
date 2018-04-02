package com.almundo.callcenter.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author Jorge Caro
 * @since 31-03-2018
 * 
 * Representación de una llamada
 *
 */
public class Call implements Comparable<Call> {

	private Integer id;
	private Integer sequence;
	private String clientName;
	private Employee employee;
	private boolean assignedEmployee;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;		
	}
	
	public boolean isAssignedEmployee() {
		return assignedEmployee;
	}
	public void setAssignedEmployee(boolean assignedEmployee) {
		this.assignedEmployee = assignedEmployee;
	}
	public boolean isNotAssignedEmployee() {
		return !isAssignedEmployee();
	}
	
	@Override
	public String toString() {

		SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss:SSS");
			return "CallId: "+ getId() +" Contesta " + getEmployee().getName() 
					+ " Atendiendo a: " 
					+ getClientName() + " Time: " 
					+ timeFormat.format(Calendar.getInstance().getTime());
	}
	@Override
	public int compareTo(Call o) {
		return this.getId().compareTo(o.getId());
	}
	
}
