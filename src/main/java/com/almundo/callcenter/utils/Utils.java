package com.almundo.callcenter.utils;

import java.util.Random;

public class Utils {

	public static int getRandomId(int size) {
		int randomId = 0;
		
		if(size > 1) {
			randomId = randomId(0, size-1);
		}
		return randomId;
	}
	
	private static int randomId(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}

}
