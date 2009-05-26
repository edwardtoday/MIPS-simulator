package org.ked9.others;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.pcunit.PCUnit;

public class Stop2Period {
	Signals signal;
	Signals next;
	
	// in 
	boolean needStop;
	boolean islwsw;
	
	// out
	boolean hold;
	boolean nreset;
	
	boolean temp = false;
	
	public void start(boolean r) {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check(r);
		run();
		set();
	}
	
	public void check(boolean r) {
		needStop = signal.isNeedStop_Ctrl();
		islwsw = signal.isIslwswOut_EXE();
	}
	
	private void run() {
		if(needStop) {
			hold = needStop;
			nreset = needStop;
			temp = needStop;
		} else if(islwsw) {
			hold = islwsw;
			nreset = islwsw;
			temp = needStop;
		} else {
			hold = temp;
			nreset = temp;
			temp = needStop;
		}
	}
	
	private void set() {
		next.setHold_Stop(hold);
		next.setNreset_Stop(nreset);
	}
	
	public static void main(String args[]) {
		Stop2Period s = new Stop2Period();
	}
}
