package org.ked9.others;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class ChoPCCtrl {
	Signals signal;
	Signals next;
	
	// in
	int im;
	int val;
	int pc;
	int pcx;
	int cChoPCCtrl;
	
	// out
	int lastPC;
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		im = signal.getIm_Ctrl();
		val = signal.getVal_T();
		pc = signal.getPC_PC();
		pcx = signal.getPCOut_IF();
		cChoPCCtrl = signal.getCChoPCCtrl_Ctrl();
	}
	
	private void run() {
		if((cChoPCCtrl == 2 && val != 0) ||
				(cChoPCCtrl == 3 && val == 0))
			lastPC = pcx + im;
		else if(cChoPCCtrl == 1)
			lastPC = val - 1;
		else
			lastPC = pc;
	}
	
	private void set() {
		next.setLastPC_CPC(lastPC);
	}
	
	public static void main(String args[]) {

	}
}
