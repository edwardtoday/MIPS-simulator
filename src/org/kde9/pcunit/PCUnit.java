package org.kde9.pcunit;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class PCUnit {
	Signals signal;
	Signals next;
	
	int temp = -5;
	
	// in
	private int lastPc;
	private boolean reset;
	private boolean hold;
	
	// out
	private int pc;
	
	public void start(boolean r) {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check(r);
		run();
		set();
	}
	
	public void check(boolean r) {
		lastPc = signal.getLastPC_CPC();
		hold = signal.isHold_Stop();
		reset = r;
	}
	
	private void run() {
		if(reset == true)
			pc = 0;
		else if(hold == false) {
			if(temp != -5) {
				pc = 1 + temp;
				temp = -5;
			} else
				pc = 1 + lastPc;		
		}
		else
			temp = lastPc;
	}
	
	private void set() {
		next.setPC_PC(pc);
	}
	
	public static void main(String args[]) {
		PCUnit p = new PCUnit();
		SignalPool.a.setLastPC_CPC(22);
		SignalPool.b.setLastPC_CPC(42);
		SignalPool.b.setHold_Stop(true);
		p.start(false);
		System.out.println(SignalPool.b.getPC_PC());
		SignalPool.next();
		p.start(false);
		System.out.println(SignalPool.a.getPC_PC());
	}
}
