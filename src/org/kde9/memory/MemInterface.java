package org.kde9.memory;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.cpu.UnitPool;
import org.kde9.exceptions.AlreadyExist;
import org.kde9.exceptions.DonotExist;
import org.kde9.util.Constants;

public class MemInterface
implements Constants {
	Signals signal;
	Signals next;
	
	Cache insCache;
	Cache dataCache;
	int addr;
	
	// in
	int pc;
	int memAddr;
	boolean we;
	int memWVal;
	boolean islwsw;
	
	// out
	int ins;
	boolean Ready;
	
	public MemInterface() {
		insCache = new Cache();
		dataCache = new Cache();
	}
	
	public Cache getInsCache() {
		return insCache;
	}

	public Cache getDataCache() {
		return dataCache;
	}
	
	public Memory getMem() {
		return Cache.getMem();
	}

	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		try {
			run();
		} catch (DonotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		set();
	}
	
	public void check() {
		pc = next.getPC_PC();
		memAddr = next.getALUValOut_EXE();
		we = next.isMemWEOut_EXE();
		memWVal = next.getRegValOut2_EXE();
		islwsw = next.isIslwswOut_EXE();
	}
	
	private void run() 
	throws DonotExist {
		if(we)
			dataCache.write(memAddr, memWVal);
		Integer i;
		if (!islwsw) {
			addr = pc;
			i = insCache.read(addr);
		} else {
			addr = memAddr;
			i = dataCache.read(addr);
		}
		if (i == null) {
			Ready = false;
			ins = 0;
		} else {
			Ready = true;
			ins = i;
		}
	}
	
	private void set() {
		next.setIns_Mem(ins);
		next.setReady_Mem(Ready);
	}
}
