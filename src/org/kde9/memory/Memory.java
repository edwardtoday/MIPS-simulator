package org.kde9.memory;

import java.util.HashMap;
import java.util.Set;

import org.kde9.exceptions.AlreadyExist;
import org.kde9.exceptions.DonotExist;
import org.kde9.util.Constants;


public class Memory 
implements Constants{
	HashMap<String, HashMap<Integer, Integer>> memorys;
	
	public Memory() {
		memorys = new HashMap<String, HashMap<Integer,Integer>>();
	}
	
	public void addMem(String name) 
	throws AlreadyExist {
		if(memorys.containsKey(name)) {
			throw new AlreadyExist("memory '" +
					name +
					"' you tried to add has already exist!");
		} else {
			HashMap<Integer, Integer> mem = new HashMap<Integer, Integer>();
			memorys.put(name, mem);
		}
	}
	
	public void removeMem(String name) {
		memorys.remove(name);
	}
	
	public void clearMem(String name) 
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(name);
		if(mem == null) {
			throw new DonotExist("memory '" +
					name +
					"' you wanted to clear does not exist!");
		} else {
			mem.clear();
		}
	}
	
	public void write(String memName, int addr, int value) 
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(memName);
		if(mem == null) {
			throw new DonotExist("memory '" +
					memName +
					"' you wanted to write does not exist!");
		} else {
			mem.put(addr, value);
		}
	}
	
	public int read(String memName, int addr)
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(memName);
		if(mem == null) {
			throw new DonotExist("memory '" +
					memName +
					"' you wanted to read does not exist!");
		} else {
			Integer value = mem.get(addr);
			if(value == null)
				return MEM_INITIAL_VALUE;
			else
				return value;
		}
	}
	
	public Set<String> getExistMemName() {
		return memorys.keySet();
	}
	
	public static void main(String args[]) {
		Memory m = new Memory();
		System.out.println(m.getExistMemName());
		try {
			m.addMem("data");
			System.out.println(m.getExistMemName());
			m.write("data", 0x10, 22);
			System.out.println(m.read("data", 0x10));
			m.clearMem("data");
			System.out.println(m.read("data", 0x10));
		} catch (AlreadyExist e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DonotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
