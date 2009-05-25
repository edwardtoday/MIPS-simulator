package org.kde9.register;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.kde9.exceptions.AlreadyExist;
import org.kde9.exceptions.DonotExist;
import org.kde9.util.Constants;

public class RegisterHeap 
implements Constants {
	HashMap<String, Integer> registers;
	
	public RegisterHeap() {
		registers = new LinkedHashMap<String, Integer>();
	}
	
	public void addReg(String name) 
	throws AlreadyExist {
		if(registers.containsKey(name))
			throw new AlreadyExist("register '" +
					name +
					"' you tried to add has already exist!");
		else
			registers.put(name, REGISTER_INITIAL_VALUE);
	}
	
	public void removeReg(String name) {
		registers.remove(name);
	}
	
	public void clear() {
		for(String name : registers.keySet()) {
			registers.put(name, REGISTER_INITIAL_VALUE);
		}
	}
	
	public void write(String name, int value) 
	throws DonotExist {
		Integer r = registers.get(name);
		if(r == null)
			throw new DonotExist("register '" +
					name +
					"' you wanted to write does not exist!");
		else
			registers.put(name, value);
	}
	
	public int read(String name) 
	throws DonotExist {
		Integer r = registers.get(name);
		if(r == null)
			throw new DonotExist("register '" +
					name +
					"' you wanted to read does not exist!");
		else
			return r;
	}
	
	public static void main(String args[]) {
		RegisterHeap r = new RegisterHeap();
		try {
			r.addReg("pc");
			System.out.println(r.read("pc"));
			r.write("pc", 333);
			System.out.println(r.read("pc"));
			r.clear();
			System.out.println(r.read("pc"));
		} catch (DonotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
