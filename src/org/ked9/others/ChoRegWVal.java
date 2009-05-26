package org.ked9.others;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class ChoRegWVal {
	Signals signal;
	Signals next;
	
	// in
	int aluVal;
	int memVal;
	boolean t;
	int cChoRegWVal;
	
	// out
	int regWVal;
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		aluVal = signal.getALUValOut_MEM();
		memVal = signal.getMemValOut_MEM();
		t = signal.isTOut_MEM();
		cChoRegWVal = signal.getRegWVal_CReg();
	}
	
	private void run() {
		if(cChoRegWVal == 1)
			regWVal = aluVal;
		else if(cChoRegWVal == 0)
			regWVal = memVal;
		else {
			if(t)
				regWVal = 1;
			else
				regWVal = 0;
		}
	}
	
	private void set() {
		next.setRegWVal_CReg(regWVal);
	}
	
	public static void main(String args[]) {

	}
}
