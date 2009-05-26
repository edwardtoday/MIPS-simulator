package org.kde9.alu;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class ALU {
	Signals signal;
	Signals next;
	
	// in
	int a;
	int b;
	int q;
	int cin;
	
	// out
	int ret;
	boolean t = false;
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		a = signal.getA_CALU1();
		b = signal.getB_CALU2();
		q = signal.getALUCtrlOut_ID();
		cin = 0;
	}
	
	private void run() {
		switch(q) {
		case 0:
			ret = 0;
			break;
		case 1:
			ret = a + b + cin;
			break;
		case 2:
			ret = a - b - cin;
			break;
		case 3:
			ret = a & b;
			break;
		case 4:
			ret = a | b;
			break;
		case 5:
			ret = a^b;
			break;
		case 6:
			ret = ~a;
		case 7:
			break;
		default:
			ret = 0;
			t = false;
		}
	}
	
	private void set() {
		next.setRet_ALU(ret);
		next.setT_ALU(t);
	}
}
