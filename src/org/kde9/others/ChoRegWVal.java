package org.kde9.others;

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
		aluVal = next.getALUValOut_MEM();
		memVal = next.getMemValOut_MEM();
		t = next.isTOut_MEM();
		cChoRegWVal = next.getCChoRegWValOut_MEM();
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
