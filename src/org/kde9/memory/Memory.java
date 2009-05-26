package org.kde9.memory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.kde9.exceptions.AlreadyExist;
import org.kde9.exceptions.DonotExist;
import org.kde9.util.Constants;


public class Memory 
implements Constants{
	HashMap<String, HashMap<Integer, Integer>> memorys;
	HashMap<String, Integer> locks;
	boolean conflict = false;
	String conflictMemName;
	int conflictAddr;
	
	public Memory() {
		memorys = new HashMap<String, HashMap<Integer,Integer>>();
		locks = new LinkedHashMap<String, Integer>();
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
			locks.put(name, 0);
		}
	}
	
	public void removeMem(String name) {
		memorys.remove(name);
		locks.remove(name);
		if(conflictMemName.equals(name))
			conflict = false;
	}
	
	public void require(String name) 
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(name);
		if(mem == null) {
			throw new DonotExist("memory '" +
					name +
					"' you wanted to require lock does not exist!");
		} else {
			locks.put(name, locks.get(name)+1);
		}
	}
	
	public void release(String name) 
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(name);
		if(mem == null) {
			throw new DonotExist("memory '" +
					name +
					"' you wanted to release lock does not exist!");
		} else {
			locks.put(name, locks.get(name)-1);
		}
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
	
	public void resetMem(String name) 
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(name);
		if(mem == null) {
			throw new DonotExist("memory '" +
					name +
					"' you wanted to clear does not exist!");
		} else {
			mem.clear();
			locks.put(name, 0);
			if(conflictMemName.equals(name))
				conflict = false;
		}
	}
	
	public void write(String memName, int addr, int value) 
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(memName);
		if(mem == null) {
			throw new DonotExist("memory '" +
					memName +
					"' you wanted to write does not exist!");
		} else if(locks.get(memName) == 0) {
			mem.put(addr, value);
			conflict = false;
		} else {
			System.err.println("Waining: Memory '" +
					memName +
					"' has been locked! write failed!");
			System.err.println("Lock: " + locks.get(memName) + 
					"; Addr: " + addr + "; Value: " + value);
			conflict = true;
			conflictMemName = memName;
			conflictAddr = addr;
		}
	}
	
	public int read(String memName, int addr)
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(memName);
		if(mem == null) {
			throw new DonotExist("memory '" +
					memName +
					"' you wanted to read does not exist!");
		} else if(locks.get(memName) == 0) {
			Integer value = mem.get(addr);
			conflict = false;
			if(value == null)
				return MEM_INITIAL_VALUE;
			else
				return value;
		} else {
			System.err.println("Waining: Memory '" +
					memName +
					"' has been locked! read failed!");
			System.err.println("Lock: " + locks.get(memName) +
					"; Addr: " + addr);
			conflict = true;
			conflictMemName = memName;
			conflictAddr = addr;
		}
		return MEM_INITIAL_VALUE;
	}
	
	public Set<String> getExistMemName() {
		return memorys.keySet();
	}
	
	public int getStation(String name) 
	throws DonotExist {
		HashMap<Integer, Integer> mem = memorys.get(name);
		if (mem == null) {
			throw new DonotExist("memory '" + name
					+ "' you wanted to check does not exist!");
		} else {
			return locks.get(name);
		}
	}
	
	public boolean isConflict() {
		return conflict;
	}

	public String getConflictMemName() {
		return conflictMemName;
	}

	public int getConflictAddr() {
		return conflictAddr;
	}
	
	public static void main(String args[]) {
		Memory m = new Memory();
		System.out.println(m.getExistMemName());
		try {
			m.addMem("data");
			System.out.println(m.getExistMemName());
			m.require("data");
			m.write("data", 0x10, 22);
			System.out.println(m.read("data", 0x10));
			m.release("data");
			System.out.println(m.read("data", 0x10));
			System.out.println("Conflict: " + m.getConflictMemName() + " " +
					m.getConflictAddr());
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
