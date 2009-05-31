package org.kde9.memory;

import java.util.HashMap;
import java.util.Vector;

import org.kde9.cpu.UnitPool;
import org.kde9.exceptions.AlreadyExist;
import org.kde9.exceptions.DonotExist;
import org.kde9.util.Constants;

public class Cache 
implements Constants {
	static Memory mem = new Memory();

	HashMap<Integer, Integer> cache;

	HashMap<Integer, Integer> addr2showindex;
	int[] showindex;
	Vector<Integer> changeindex;
	
	int flag = 0;
	int addr = -1;
	
	public Cache() {
		try {
			mem.addMem(DATA);
		} catch (AlreadyExist e) {}
		cache = new HashMap<Integer, Integer>();
		showindex = new int[CACHE_SIZE];
		addr2showindex = new HashMap<Integer, Integer>();
		changeindex = new Vector<Integer>();
		for(int i = 0; i < CACHE_SIZE; i++)
			showindex[i] = -1;
	}
	
	public static Memory getMem() {
		return mem;
	}
	
	public void clear() {
		cache.clear();
		addr2showindex.clear();
		changeindex.clear();
		for(int i = 0; i < CACHE_SIZE; i++)
			showindex[i] = -1;
	}
	
	public void write(int addr, int value) {
		Integer val = cache.get(addr);
		if(val != null) {
			update(addr);
		} else {
			load(addr, false);
		}
		cache.put(addr, value);
		if(flag != 0)
			flag--;
	}
	
	public Integer read(int addr) {
		Integer value = cache.get(addr);
		if(value == null) {
			if(flag == 1 && this.addr == addr) {
				load(addr, true);
				value = cache.get(addr);
			} else {
				flag = 2;
				this.addr = addr;
			}
		} else {
			update(addr);
		}
		if(flag != 0)
			flag--;
		return value;
	}
		
	private void update(int addr) {
		int i = 0;
		for(; i < changeindex.size(); i++)
			if(changeindex.get(i) == addr)
				break;
		changeindex.remove(i);
		changeindex.add(0, addr);
	}
	
	private void load(int addr, boolean log) {
		try {
			if(cache.size() == CACHE_SIZE) {
				int a = changeindex.lastElement();
				if(mem.read(DATA, a, false) != cache.get(a))
					mem.write(DATA, a, cache.get(a), true);
				cache.remove(a);
				changeindex.removeElementAt(changeindex.size()-1);
				showindex[addr2showindex.get(a)] = -1;
				addr2showindex.remove(a);
			}
			int value = mem.read(DATA, addr, log);
			cache.put(addr, value);
			int i = 0;
			for(;i < CACHE_SIZE; i++)
				if(showindex[i] == -1)
					break;
			showindex[i] = addr;
			addr2showindex.put(addr, i);
			changeindex.add(0, addr);
		} catch (DonotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer, Integer> getCache() {
		return cache;
	}
}
