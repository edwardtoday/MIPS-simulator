package org.kde9.memory;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.cpu.UnitPool;
import org.kde9.exceptions.AlreadyExist;
import org.kde9.exceptions.DonotExist;

public class MemInterface {
	Signals signal;
	Signals next;
	
	Memory mem;
	String DATA = "data";
	int addr;
	
	// in
	int pc;
	int memAddr;
	boolean we;
	int memWVal;
	boolean islwsw;
	
	// out
	int ins;
	
	public MemInterface() {
		mem = UnitPool.getMemory();
		try {
			mem.addMem(DATA);
		} catch (AlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() 
	throws DonotExist {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		pc = signal.getPC_PC();
		memAddr = signal.getALUValOut_EXE();
		we = signal.isMemWEOut_EXE();
		memWVal = signal.getRegValOut2_EXE();
		islwsw = signal.isIslwswOut_EXE();
	}
	
	private void run() 
	throws DonotExist {
		if(we)
			mem.write(DATA, memAddr, memWVal);
		if(!islwsw)
			addr = pc;
		ins = mem.read(DATA, addr);
	}
	
	private void set() {
		next.setIns_Mem(ins);
	}
}
