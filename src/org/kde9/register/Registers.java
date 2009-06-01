package org.kde9.register;

import java.util.HashMap;
import java.util.Vector;

import org.kde9.exceptions.AlreadyExist;
import org.kde9.exceptions.DonotExist;
import org.kde9.util.Constants;

public class Registers 
implements Constants {
	HashMap<String, Integer> registers;
	HashMap<String, Integer> his1;
	HashMap<String, Integer> his2;

	Vector<Integer> read;
	Vector<Integer> write;
	
	String name = "";
	
	public HashMap<String, Integer> getHis1() {
		return his1;
	}

	public HashMap<String, Integer> getHis2() {
		return his2;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public HashMap<String, Integer> getRegisters() {
		return registers;
	}
	
	public Registers() {
		registers = new HashMap<String, Integer>();
		his1 = new HashMap<String, Integer>();
		his2 = new HashMap<String, Integer>();
	}
	
	public void addReg(String name) 
	throws AlreadyExist {
		if(registers.containsKey(name))
			throw new AlreadyExist("register '" +
					name +
					"' you tried to add has already exist!");
		else {
			registers.put(name, REGISTER_INITIAL_VALUE);
			his1.put(name, REGISTER_INITIAL_VALUE);
			his2.put(name, REGISTER_INITIAL_VALUE);
		}
	}
	
	public void removeReg(String name) {
		registers.remove(name);
		his1.remove(name);
		his2.remove(name);
	}
	
	public void clear() {
		for(String name : registers.keySet()) {
			registers.put(name, REGISTER_INITIAL_VALUE);
			his1.put(name, REGISTER_INITIAL_VALUE);
			his2.put(name, REGISTER_INITIAL_VALUE);
		}
	}
	
	public void write(String name, int value) 
	throws DonotExist {
		Integer r = registers.get(name);
		if(r == null)
			throw new DonotExist("register '" +
					name +
					"' you wanted to write does not exist!");
		else {
			registers.put(name, value);
			Integer rr = his1.get(name);
			his1.put(name, r);
			if(rr != null)
				his2.put(name, rr);
			this.name = name;
		}
	}
	
	public int read(String name) 
	throws DonotExist {
		Integer r = registers.get(name);
		if(r == null)
			throw new DonotExist("register '" +
					name +
					"' you wanted to read does not exist!");
		else {
			return r;
		}
	}
	
	public static void main(String args[]) {
		Registers r = new Registers();
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
