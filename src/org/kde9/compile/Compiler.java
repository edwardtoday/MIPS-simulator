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
	Standardization stand;
	HashMap<Integer, Integer> pc2rownum;
	HashMap<Integer, Integer> rownum2pc;
	
	public HashMap<Integer, Integer> getRownum2pc() {
		return rownum2pc;
	}

	public HashMap<Integer, Integer> getPc2rownum() {
		return pc2rownum;
	}

	public Compiler() {
		errors = new HashMap<Integer, Integer>();
		result = new Vector<Integer>();
		check = new Check();
		stand = new Standardization();
		pc2rownum = new HashMap<Integer, Integer>();
		rownum2pc = new HashMap<Integer, Integer>();
	}
	
	public Vector<Integer> getRet() {
		return result;
	}
	
	public HashMap<Integer, Integer> getErrRows() {
		return errors;
	}
	
	public boolean compile(String text) 
	throws IOException {
		text = text.toLowerCase();
		boolean flag = true;
		errors.clear();
		result.clear();
		pc2rownum.clear();
		rownum2pc.clear();
		text = stand.check(text);
		StringReader sr = new StringReader(text);
		BufferedReader br = new BufferedReader(sr);
		String temp = br.readLine();
		int i = 1;
		while(temp != null) {
			temp = temp.trim();
			temp = stand.stand(temp);
			Integer ins;
			if (temp.length() != 0) {
				ins = check.compile(temp);
				if (ins == null) {
					errors.put(i, check.getErrorType());
					flag = false;
				} else {
					result.add(ins);
					pc2rownum.put(result.size(), i);
					rownum2pc.put(i, result.size());
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
