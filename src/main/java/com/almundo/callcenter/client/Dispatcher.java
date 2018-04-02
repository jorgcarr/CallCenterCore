package com.almundo.callcenter.client;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.almundo.callcenter.core.CallConcurrentController;
import com.almundo.callcenter.model.Call;

public class Dispatcher {	
	
	private int INITIAL_DELAY_RETRY_NON_ATTENDED = 2;
	private int PERIOD_NON_ATTENDED = 3;
	
	private ScheduledExecutorService executorNonAttendedCall;
	private PriorityQueue<Call> nonAttendedCallQuue;
	
	private int callId = 0;
	
	public Dispatcher() {
		
		nonAttendedCallQuue = new PriorityQueue<Call>();
		executorNonAttendedCall = Executors.newScheduledThreadPool(1);
	}
	
	public void dispatchCall(Call call) throws InterruptedException {
		
		call.setId(callId++);
		
		try {
						
			CallConcurrentController.tryCall(call);
			
		} catch (IllegalStateException e) {
			
			System.out.println("Error: "+ e.getMessage());
			
			nonAttendedCallQuue.add(call);	
			Runnable task = () -> tryAssignEmployee();
			
			executorNonAttendedCall.scheduleAtFixedRate(task, INITIAL_DELAY_RETRY_NON_ATTENDED
					, PERIOD_NON_ATTENDED, TimeUnit.SECONDS);
			
		}
		
	}
	

	private Object tryAssignEmployee() {
		try {
			Call call = nonAttendedCallQuue.peek();
			CallConcurrentController.tryCall(call);
			System.out.println("Se asignó e inicio una llamada de la cola NO atendidad :)");
			nonAttendedCallQuue.poll();
		} catch (IllegalStateException e) {
			
		}
		return null;
	}

}
