package com.almundo.callcenter.core;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.almundo.callcenter.model.Call;

public class CallTask implements Callable<Integer> {

	private static final int MAX_TIME_IN_CALL = 20;
	private static final int MIN_TIME_IN_CALL = 10;
		
	private Call call;
	
	public CallTask(Call call) {
		this.call = call;
	}

	@Override
	public Integer call() throws Exception {
		
		System.out.println("**INICIO: "+ call);
		
    	int time = simulateCall();
    	
    	System.out.println("**FIN: " + call + "  (" + time +"Sg)");
    	CallConcurrentController.hangOut(call);
    	return time;
	}

	private int simulateCall() {
		
		Random rand = new Random();
		int time = 0;
		try {
			time = rand.nextInt(MAX_TIME_IN_CALL - MIN_TIME_IN_CALL + 1)
					+ MIN_TIME_IN_CALL;
	        TimeUnit.SECONDS.sleep(time);
	        
		} catch (InterruptedException e) {
			System.out.println("Error generando random de llamada");
			e.printStackTrace();
		}
		
		return time;
	}

	public void setCall(Call call) {
		this.call = call;
	}
}
