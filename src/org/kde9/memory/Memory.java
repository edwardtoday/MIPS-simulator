package org.kde9.memory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.kde9.exceptions.MemAlreadyExist;
import org.kde9.exceptions.MemDonotExist;


public class Memory {
	HashMap<String, HashMap<Integer, Integer>> memorys;
	
	public Memory() {
		memorys = new LinkedHashMap<String, HashMap<Integer,Integer>>();
	}
	
	public void addMem(String name) 
	throws MemAlreadyExist {
		if(memorys.containsKey(name)) {
			throw new MemAlreadyExist("memory " +
					name +
					" you tried to add has already exist!");
		} else {
			HashMap<Integer, Integer> mem = new HashMap<Integer, Integer>();
			memorys.put(name, mem);
		}
	}
	
	public void removeMem(String name) {
		memorys.remove(name);
	}
	
	public void clearMem(String name) 
	throws MemDonotExist {
		HashMap<Integer, Integer> mem = memorys.get(name);
		if(mem == null) {
			throw new MemDonotExist("memory " +
					name +
					" you wanted to clear does not exist!");
		} else {
			mem.clear();
		}
	}
	
	public void write(String memName, int addr, int value) 
	throws MemDonotExist {
		HashMap<Integer, Integer> mem = memorys.get(memName);
		if(mem == null) {
			throw new MemDonotExist("memory " +
					memName +
					" you wanted to write does not exist!");
		} else {
			mem.put(addr, value);
		}
	}
	
	public int read(String memName, int addr)
	throws MemDonotExist {
		HashMap<Integer, Integer> mem = memorys.get(memName);
		if(mem == null) {
			throw new MemDonotExist("memory " +
					memName +
					" you wanted to read does not exist!");
		} else {
			Integer value = mem.get(addr);
			if(value == null)
				return 0;
			else
				return value;
		}
	}
	
	public static void main(String args[]) {
		try {
			throw new MemAlreadyExist("mem");
		} catch (MemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
