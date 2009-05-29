package org.kde9.memory;

import java.util.HashMap;
import java.util.Vector;

import org.kde9.util.Constants;

public class Cache 
implements Constants {
	HashMap<Integer, Integer> cache;
	HashMap<Integer, Integer> addr2index;
	Vector<Integer> usage;
	
	public Cache() {
		cache = new HashMap<Integer, Integer>();
		addr2index = new HashMap<Integer, Integer>();
		usage = new Vector<Integer>();
	}
	
	public Integer read(int addr) {
		Integer value = cache.get(addr);
		if(value == null) {
			load(addr);
		} else {
			int index = addr2index.get(addr);
			
		}
		return value;
	}
		
	private void load(int addr) {
		
	}
}
