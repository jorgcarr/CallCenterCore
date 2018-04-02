	package com.almundo.callcenter.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.EmployeeState;
import com.almundo.callcenter.model.EmployeeType;
import com.almundo.callcenter.utils.EmployeeGroupPriorityComparator;
import com.almundo.callcenter.utils.EmployeeInicializer;
import com.almundo.callcenter.utils.Utils;

/**
 * Clase encargada de controlar la asignación de empleados de acuerdo a
 * Prioridad por el tipo de empleado y si esta disponible
 * 
 * @author Jorge Caro
 * @since 1/4/2018
 * 
 */
public class EmployeeAssigner extends IEmployeeAssignerService {
	
	/** Lista de empleados**/
	private List<Employee> employeesList = new ArrayList<Employee>();
	
	/** <h1>Cola de tipos de empleado con prioridad</h1> 
	 * <p>A medidad que se van asignado llamadas puede que los empleados del grupo
	 * se agote por lo que se saca dicho grupo de la cola.</p>
	 * <p>En caso de que un empleado se desocupe y el grupo ya no este, se vuelve a agregar
	 * por medio del metodo <code>addEmployeeReleased</code> 
	 * invocado por la clase <code>CallTask<code> </p>**/
	private PriorityQueue<EmployeeType> availableGroupQueueByPriority;
	
	/** Mantine la relación del tipo de empleado con si respectiva lista de empleados **/
	private Map<EmployeeType, List<Employee>> availableEmployeesByGroupList;
	
	/** instancia única de la clase **/
	private static EmployeeAssigner instance;
	
	/**
	 * Metodo para obtener la instancia unica de la clase
	 * @return la instancia
	 */
	public static EmployeeAssigner getInstance() {
		
		if(instance == null) {
			instance = new EmployeeAssigner();
		}
		return instance;
	}
	
	/**
	 * Obtiene de la lista actual (con mayor prioridad y con empleados disponibles) 
	 * uno de forma aleatoria y lo remueve de la lista actual.
	 * 
	 * @return el empleado libre de la lista acutal
	 */
	public Employee getAvailableEmployee() {
		
		List<Employee> listWithActualPriority = getActualList();
		
		int randomId = Utils.getRandomId(listWithActualPriority.size());
		return listWithActualPriority.remove(randomId);
		
	}
	
	/**
	 * Agregar un empleado a los dispobles
	 * 
	 * @param employee
	 */
	public void addReleasedEmployee(Employee employee) {
		
		if(!availableGroupQueueByPriority.contains(employee.getType())) {
			availableGroupQueueByPriority.add(employee.getType());
		}
		employee.setState(EmployeeState.FREE);
		List<Employee> list = availableEmployeesByGroupList.get(employee.getType());
		list.add(employee);
	}
	
	
	/**
	 * Contructor inicializa la lista de empleados
	 * el mapa que relaciona el tipo de empleado con la lista de los mismos
	 */
	private EmployeeAssigner() {
		
		employeesList = EmployeeInicializer.inicialiceEmployeeList();
		
		availableEmployeesByGroupList = employeesList.stream()
				.collect(
						Collectors.groupingBy(Employee::getType));
		
		populateGruopQueueByPriority();
	}

	/**
	 * Toma de la enumeración los tipos empleado existentes
	 * y pobla la cola con ellos
	 */
	private void populateGruopQueueByPriority() {
		availableGroupQueueByPriority = new PriorityQueue<EmployeeType>(
				new EmployeeGroupPriorityComparator());
		
		for (EmployeeType employeeGroup : EmployeeType.values()) {
			availableGroupQueueByPriority.add(employeeGroup);
		}		
	}
	
	
	/**
	 * Retona la lista actual: con mayor prioridad y con empleados disponibles
	 * Propaga la excepción en caso de no haber grupos disponibles
	 *  
	 * @return la lista actual de empleados 
	 */
	private List<Employee> getActualList() {
		
		EmployeeType groupWithPriorityFromQueue = getActualGroup();
		List<Employee> listWithActualPriority =  
				availableEmployeesByGroupList.get(groupWithPriorityFromQueue);
		
		if(listWithActualPriority.isEmpty()) {
			System.out.println(Messages.getString("EmployeeAssigner.0") 
					+ groupWithPriorityFromQueue + Messages.getString("EmployeeAssigner.1"));
			availableGroupQueueByPriority.poll();
			groupWithPriorityFromQueue = getActualGroup();
			listWithActualPriority =  
					availableEmployeesByGroupList.get(groupWithPriorityFromQueue);
		}
		return listWithActualPriority;
	}

	/**
	 * Reotorna el grupo actual de la cola de grupo con prioridad
	 * En caso de no haber grupos de empleados disponibles
	 * retorna excepción dado que indica que no hay empleados disponibles
	 * @return
	 */
	private EmployeeType getActualGroup() {
		
		EmployeeType groupWithPriorityFromQueue = availableGroupQueueByPriority.peek();
		
		if(groupWithPriorityFromQueue == null){
			throw new IllegalStateException(Messages.getString("EmployeeAssigner.2")); 
		}		
		
		return groupWithPriorityFromQueue;
	}
	
}