package org.kde9.compile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;

import org.kde9.util.Constants;

public class Standardization 
implements Constants {
	HashMap<String, Integer> flags;
	
	public Standardization() {
		flags = new HashMap<String, Integer>();
	}
	
	public String check(String text) {
		flags.clear();
		checkx(text);
		return checkx(text);
	}
	
	public String checkx(String text) {
		String finish = "";
		try {
			text = text.replace('\t', ' ');
			StringReader sr = new StringReader(text);
			BufferedReader br = new BufferedReader(sr);
			String temp = br.readLine();
			int i = 1;
			while (temp != null) {
				temp = temp.trim();
				if(temp.indexOf(':') != -1 &&
						(temp.indexOf(';') == -1 || temp.indexOf(':') < temp.indexOf(';'))) {
					flags.put(temp.substring(0, temp.indexOf(':')), i);
					temp = "";
				}
				for(String flag : flags.keySet()) {
					if (temp.contains(flag)) {
//						System.err.println(temp);
						temp += " ";
						if (temp.contains("," + flag + ";")
								|| temp.contains("," + flag + " ")
								|| temp.contains("," + flag + ",")
								|| temp.contains(" " + flag + ";")
								|| temp.contains(" " + flag + " ")) {
//							System.err.println("+++++" + temp);
							temp = temp.replace(flag, 
									String.valueOf(flags.get(flag)-i-1)).trim();
							break;
						}
					}
				}
				if(temp.length() != 0 && !temp.startsWith(";"))
					i++;
				finish += temp;
				finish += NEWLINE;
				temp = br.readLine();
			}
			br.close();
			sr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finish;
	}
	
	public String stand(String ins) {
		String temp = "";
		if(ins.startsWith(";") || ins.length() == 0)
			return temp;
		else {
			temp = ins.replace(',', ' ');
			int i = temp.indexOf(';');
			if(i != -1)
				temp = temp.substring(0, i);
			if(temp.startsWith("sw")) {
				// TODO
				temp = temp.replace('(', ' ');
				temp = temp.replace(')', ' ');
				int bb = temp.indexOf('r');
				int cc = temp.indexOf(' ');
				if(cc != -1 && bb != -1 && cc < bb) {
					String str = temp.substring(cc+1, bb);
					temp = temp.replace(str, " ");
					temp += (" " + str);
				}
			}
//			System.out.println(temp);
			return temp;
		}
	}
	
	public static void main(String args[]) {
		Standardization stand = new Standardization();
		String s =";Test 3" + NEWLINE
				+ ";Calculate the first 5 primes from 10" + NEWLINE
				+ ";Author: DAI Zhenlong" + NEWLINE 
				+ ";Date:   08.05.13"+ NEWLINE 
				+ "" + NEWLINE 
				+ "addi r1,r0,10	;count of primes"+ NEWLINE 
				+ "addi r2,r0,2	;begin with 2" + NEWLINE
				+ "addi r7,r0,256	;r7 = 256, position to be stored" + NEWLINE
				+ "begin:" + NEWLINE 
				+ "beqz r1,end	;if count =  0, halt"+ NEWLINE 
				+ "addi r11,r0,2" + NEWLINE
				+ "div  r3,r2,r11	;half of the number to be tested" + NEWLINE
				+ "addi r3,r3,1" + NEWLINE 
				+ "addi r4,r0,2	;loop from 2"+ NEWLINE 
				+ "loop:" + NEWLINE + "sub  r5,r3,r4   ;r5 = r3 - r4"+ NEWLINE 
				+ "beqz r5,store" + NEWLINE
				+ "div  r8,r2,r4	;r8 = r2/r4" + NEWLINE
				+ "mult r9,r4,r8	;r9 = r8*r4" + NEWLINE
				+ "sub  r10,r2,r9	;r10 = r2-r9" + NEWLINE
				+ "beqz r10,next	;r10 == r2, break" + NEWLINE
				+ "addi r4,r4,1	;r4++" + NEWLINE 
				+ "j    loop" + NEWLINE
				+ "store:" + NEWLINE 
				+ "sw   0(r7),r2	;store r2 in 0(r7)"+ NEWLINE 
				+ "addi r7,r7,4	;r7 += 4" + NEWLINE
				+ "subi r1,r1,1	;r1--" + NEWLINE 
				+ "next:" + NEWLINE
				+ "addi r2,r2,1	;r2++" + NEWLINE 
				+ "j    begin" + NEWLINE
				+ "end:" + NEWLINE 
				+ "halt" + NEWLINE; 
		System.out.println(s);
		System.out.println(stand.check(s));
		System.out.println(stand.flags);
		System.out.println(stand.stand("sw   0(r7),r2   ;store r2 in 0(r7)"));
	}
}
