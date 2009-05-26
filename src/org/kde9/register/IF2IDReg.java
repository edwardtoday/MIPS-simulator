package org.kde9.register;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.util.Constants;

public class IF2IDReg
implements Constants {
	Signals signal;
	Signals next;
	
	// in
	int insIn;
	int pcIn;
	boolean reset;
	
	// out
	int insOut;
	int pcOut;
	
	public void start(boolean r) {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check(r);
		run();
		set();
	}
	
	public void check(boolean r) {
		insIn = signal.getIns_Mem();
		pcIn = signal.getPC_PC();
		reset = r;
	}
	
	private void run() {
		if(reset == true) {
			insOut = NOP_INS;
			pcOut = 0;
		} else {
			insOut = insIn;
			pcOut = pcIn;
		}
	}
	
	private void set() {
		next.setPCOut_IF(pcOut);
		next.setInsOut_IF(insOut);
	}
	
	public static void main(String args[]) {
		SignalPool.a.setIns_Mem(12344);
		SignalPool.a.setPC_PC(4321);
		new IF2IDReg().start(true);
		System.out.println(SignalPool.b.getPCOut_IF());
		System.out.println(SignalPool.b.getInsOut_IF());
	}
}
