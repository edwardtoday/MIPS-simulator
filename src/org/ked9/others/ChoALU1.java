package org.ked9.others;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class ChoALU1 {
	Signals signal;
	Signals next;
	
	// in 
	int regVal;
	int aluVal1;
	int aluVal2;
	int tChoALU1;
	
	// out
	int a;
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		regVal = signal.getRegValOut1_ID();
		aluVal1 = signal.getALUValOut_EXE();
		aluVal2 = signal.getALUValOut_MEM();
		tChoALU1 = signal.getTChoALU1_T();
	}
	
	private void run() {
		if(tChoALU1 == 2)
			a = aluVal1;
		else if(tChoALU1 == 3)
			a = aluVal2;
		else
			a = regVal;
	}
	
	private void set() {
		next.setA_CALU1(a);
	}
	
	public static void main(String args[]) {
		ChoALU1 c = new ChoALU1();
	}
}
