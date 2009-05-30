package org.kde9.compile;

import java.util.Vector;

public class Check {
	/*
	 * 0 ²»´æÔÚµÄÖ¸Áî
	 * 1 ¼Ä´æÆ÷±àºÅ´íÎó
	 * 2 ¼Ä´æÆ÷¸ñÊ½´íÎó
	 * 3 Ö¸Áî¸ñÊ½´íÎó
	 * 4 Á¢¼´Êý¸ñÊ½´íÎó
	 */
	int errorType = 0;
	
	public int getErrorType() {
		return errorType;
	}
	
	private Integer getReg(String reg) {
		if(reg.startsWith("r")) {
			String sub = reg.substring(1, reg.length());
			try {
				int id = Integer.valueOf(sub);
				if(id > 0 && id < 31)
					return id;
				else {
					errorType = 1;
					return null;
				}
			} catch (NumberFormatException e) {
				errorType = 1;
				return null;
			}
		}
		errorType = 2;
		return null;
	}
	
	private Integer getIm(String im) {
		try {
			int id = Integer.valueOf(im);
			return id;
		} catch (NumberFormatException e) {
			errorType = 4;
			return null;
		}
	}
	
	private int makeIns(String s, Integer r1, Integer r2, Integer r3, String e) {
		int n = 32-s.length();
		if(r1 != null)
			n -= 5;
		if(r2 != null)
			n -= 5;
		n -= e.length();
		String temp = s;
		s += makeReg(r1, 5);
		s += makeReg(r2, 5);
		s += makeReg(r3, n);
		s += e;
		return Integer.valueOf(s, 2);
	}
	
	private String makeReg(Integer r, int n) {
		if(r == null)
			return "";
		String reg = Integer.toBinaryString(r);
		while(reg.length() < n)
			reg = "0" + reg;
		if(reg.length() > n)
			reg = reg.substring(reg.length()-n, reg.length());
		return reg;
	}
	
	public Integer compile(String ins) {
		if(ins != null && ins.length() != 0) {
			ins += " ";
			ins = ins.toLowerCase();
			Vector<String> keys = new Vector<String>();
			for (int i = 0, j = 0; i < ins.length(); i++) {
				if (ins.charAt(i) == ' ') {
					if (j != i) {
						String temp = ins.substring(j, i).trim();
						keys.add(temp);
					}
					j = i + 1;
				}
			}
			
			if(keys.get(0).equals("add")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000100000");
				}
			} else if(keys.get(0).equals("addu")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000100001");
				}
			} else if(keys.get(0).equals("sub")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000100010");
				}
			} else if(keys.get(0).equals("subu")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000100011");
				}
			} else if(keys.get(0).equals("and")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000100100");
				}
			} else if(keys.get(0).equals("or")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000100101");
				}
			} else if(keys.get(0).equals("xor")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000100110");
				}
			} else if(keys.get(0).equals("seq")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000101000");
				}
			} else if(keys.get(0).equals("sne")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000101001");
				}
			} else if(keys.get(0).equals("slt")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000101010");
				}
			} else if(keys.get(0).equals("sgt")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000101011");
				}
			} else if(keys.get(0).equals("sle")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000101100");
				}
			} else if(keys.get(0).equals("mult")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000001", r1, r2, r3, "00000001110");
				}
			} else if(keys.get(0).equals("div")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000001", r1, r2, r3, "00000001111");
				}
			} else if(keys.get(0).equals("multu")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000001", r1, r2, r3, "00000010110");
				}
			} else if(keys.get(0).equals("divu")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000001", r1, r2, r3, "00000010111");
				}
			} else if(keys.get(0).equals("sll")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000000100");
				}
			} else if(keys.get(0).equals("srl")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000000110");
				}
			} else if(keys.get(0).equals("sra")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer r3 = getReg(keys.get(3));
					if(r1 == null || r2 == null || r3 == null)
						return null;
					else
						return makeIns("000000", r1, r2, r3, "00000000111");
				}
			} else if(keys.get(0).equals("addi")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("001000", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("addui")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("001001", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("subi")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("001010", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("subui")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("001011", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("andi")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("001100", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("ori")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("001101", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("xori")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("001110", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("slli")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("010100", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("srli")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("010110", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("srai")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("010111", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("seqi")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("011000", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("snei")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("011001", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("slti")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("011010", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("sgti")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("011011", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("slei")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("011100", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("j")) {
				if(keys.size() != 2) {
					errorType = 3;
					return null;
				} else {
					Integer im = getIm(keys.get(1));
					if(im == null)
						return null;
					else
						return makeIns("000010", null, null, im, "");
				}
			} else if(keys.get(0).equals("beqz")) {
				if(keys.size() != 3) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer im = getIm(keys.get(2));
					if(r1 == null || im == null)
						return null;
					else
						return makeIns("000100", r1, 0, im, "");
				}
			} else if(keys.get(0).equals("bnez")) {
				if(keys.size() != 3) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer im = getIm(keys.get(2));
					if(r1 == null || im == null)
						return null;
					else
						return makeIns("000101", r1, 0, im, "");
				}
			} else if(keys.get(0).equals("lw")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("100011", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("sw")) {
				if(keys.size() != 4) {
					errorType = 3;
					return null;
				} else {
					Integer r1 = getReg(keys.get(1));
					Integer r2 = getReg(keys.get(2));
					Integer im = getIm(keys.get(3));
					if(r1 == null || r2 == null || im == null)
						return null;
					else
						return makeIns("101011", r1, r2, im, "");
				}
			} else if(keys.get(0).equals("halt")) {
				if(keys.size() != 1) {
					errorType = 3;
					return null;
				} else {
					return 0xfc000000;
				}
			} else if(keys.get(0).equals("nop")) {
				if(keys.size() != 1) {
					errorType = 3;
					return null;
				} else {
					return 0xf8000000;
				}
			} 
		}
		errorType = 0;
		return null;
	}
	
	public void main(String args[]) {
		System.out.println(compile("add r2ra r4"));
		System.out.println(errorType);
	}
}
