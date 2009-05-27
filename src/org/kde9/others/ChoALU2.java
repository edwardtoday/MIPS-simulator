package org.kde9.others;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class ChoALU2 {
	Signals signal;
	Signals next;
	
	// in
	int regVal;
	int imVal;
	int aluVal1;
	int aluVal2;
	boolean cChoALU2;
	int tChoALU2;
	
	// out
	int b;
	int regVal2;
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		regVal = signal.getRegValOut2_ID();
		imVal = signal.getImOut_ID();
		aluVal1 = signal.getALUValOut_EXE();
		aluVal2 = signal.getALUValOut_MEM();
		cChoALU2 = signal.isCChoALUOut2_ID();
		tChoALU2 = signal.getTChoALU2_T();
	}
	
	private void run() {
		if(cChoALU2)
			b = imVal;
		else if(tChoALU2 == 2)
			b = aluVal1;
		else if(tChoALU2 == 3)
			b = aluVal2;
		else
			b = regVal;
		if(tChoALU2 == 2)
			regVal2 = aluVal1;
		else if(tChoALU2 == 3)
			regVal2 = aluVal2;
		else
			regVal2 = regVal;
	}
	
	private void set() {
		next.setB_CALU2(b);
		next.setRegVal2_CALU2(regVal2);
	}
	
	public static void main(String args[]) {
		ChoALU2 c = new ChoALU2();
	}
}
