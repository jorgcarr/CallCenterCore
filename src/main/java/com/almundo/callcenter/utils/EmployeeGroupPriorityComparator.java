package com.almundo.callcenter.utils;

import java.util.Comparator;

import com.almundo.callcenter.model.EmployeeType;

public class EmployeeGroupPriorityComparator implements Comparator<EmployeeType>{

	@Override
	public int compare(EmployeeType arg0, EmployeeType	 arg1) {
		 return arg0.getPriority() - arg1.getPriority();
	}

}
