package com.almundo.callcenter.coretest;



import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.almundo.callcenter.client.Dispatcher;
import com.almundo.callcenter.model.Call;

public class DispatcherTest {

	Dispatcher dispatcher = new Dispatcher();
	
	@Test(threadPoolSize = 1, invocationCount = 1)
	public void dispatchCallTest() throws InterruptedException {
		
		for (int i = 1; i <= 20; i++) {
			
			Call call = new Call();
			call.setSequence(i);
			call.setClientName("Cliente " + i);
			dispatcher.dispatchCall(call);
		}	
		
		TimeUnit.SECONDS.sleep(60);
		
	}

}
