package org.kde9.memory;

import java.util.HashMap;
import java.util.Vector;

import org.kde9.util.Constants;

public class Cache 
implements Constants {
	HashMap<Integer, Integer> cache;
	HashMap<Integer, Integer> addr2showindex;
	int[] showindex;
	HashMap<Integer, Integer> addr2changeindex;
	int[] changeindex;
	
	public Cache() {
		cache = new HashMap<Integer, Integer>();
		addr2showindex = new HashMap<Integer, Integer>();
		showindex = new int[CACHE_SIZE];
		for(int i = 0; i < CACHE_SIZE; i++)
			showindex[i] = -1;
	}
	
	public Integer read(int addr) {
		Integer value = cache.get(addr);
		if(value == null) {
			load(addr);
		} else {
			update(addr);
		}
		return value;
	}
		
	private void update(int addr) {
		
	}
	
	private void load(int addr) {
		
	}
}
