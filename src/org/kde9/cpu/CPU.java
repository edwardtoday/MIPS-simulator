package org.kde9.cpu;

import org.kde9.pcunit.PCUnit;

import sun.misc.Signal;

public class CPU {
	static int circle = 0;
	
	PCUnit pcUnit;
	
	public CPU() {
		// TODO Auto-generated constructor stub
		pcUnit = new PCUnit();
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
