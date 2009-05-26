package org.ked9.others;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class Transfer {
	Signals signal;
	Signals next;
	
	// in
	int regWaddr1;
	int regWaddr2;
	boolean regWE1;
	boolean regWE2;
	int regAddr1;
	boolean regAddrE1;
	int regAddr2;
	boolean regAddrE2;
	
	// out
	int tChoALU1;
	int tChoALU2;
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		regWaddr1 = signal.getRegWAddrOut_EXE();
		regWaddr2 = signal.getRegWAddrOut_MEM();
		regWE1 = signal.isRegWEOut_EXE();
		regWE2 = signal.isRegWEOut_MEM();
		regAddr1 = signal.getRegAddrOut1_ID();
		regAddrE1 = signal.isRegEOut1_ID();
		regAddr2 = signal.getRegAddrOut2_ID();
		regAddrE2 = signal.isRegEOut2_ID();
	}
	
	private void run() {
		if(regAddr1 == regWaddr1 && regAddrE1 && regWE1)
			tChoALU1 = 2;
		else if(regAddr1 == regWaddr2 && regAddrE1 && regWE2)
			tChoALU1 = 3;
		else
			tChoALU1 = 0;
		if(regAddr2 == regWaddr1 && regAddrE2 && regWE1)
			tChoALU2 = 2;
		else if(regAddr2 == regWaddr2 && regAddrE2 && regWE2)
			tChoALU2 = 3;
		else
			tChoALU2 = 0;
	}
	
	private void set() {
		next.setTChoALU1_T(tChoALU1);
		next.setTChoALU2_T(tChoALU2);
	}
	
	public static void main(String args[]) {

	}
}
