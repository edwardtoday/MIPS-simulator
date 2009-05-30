package org.kde9.compile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Vector;

import org.kde9.util.Functions;

public class Compiler {
	HashMap<Integer, Integer> errors;
	Vector<Integer> result;
	Check check;
	HashMap<Integer, Integer> pc2rownum;
	
	public HashMap<Integer, Integer> getPc2rownum() {
		return pc2rownum;
	}

	public Compiler() {
		errors = new HashMap<Integer, Integer>();
		result = new Vector<Integer>();
		check = new Check();
		pc2rownum = new HashMap<Integer, Integer>();
	}
	
	public Vector<Integer> getRet() {
		return result;
	}
	
	public HashMap<Integer, Integer> getErrRows() {
		return errors;
	}
	
	public boolean compile(String text) 
	throws IOException {
		boolean flag = true;
		errors.clear();
		result.clear();
		StringReader sr = new StringReader(text);
		BufferedReader br = new BufferedReader(sr);
		String temp = br.readLine();
		int i = 1;
		while(temp != null) {
			temp = temp.trim();
			Integer ins;
			if (temp.length() != 0) {
				ins = check.compile(temp);
				if (ins == null) {
					errors.put(i, check.getErrorType());
					flag = false;
				} else {
					result.add(ins);
					pc2rownum.put(result.size(), i);
				}
			}
			temp = br.readLine();
			i++;
		}
		br.close();
		sr.close();
		return flag;
	}
}
