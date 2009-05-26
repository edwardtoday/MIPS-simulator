package org.kde9.memory;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class MemInterface {
	Signals signal;
	Signals next;
	
	// in
	int pc;
	int memAddr;
	int we;
	int memWVal;
	boolean islwsw;
	
	// out
	int ins;
	int insAddr1;
	int insAddr2;
	
	// inout
	int insVal1;
	int insVal2;
	
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
	}
	
	private void run() {

	}
	
	private void set() {
	}
}
