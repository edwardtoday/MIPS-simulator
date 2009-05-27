package org.kde9.others;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class TransferChoPCVal {
	Signals signal;
	Signals next;
	
	// in
	int regVal;
	int aluVal1;
	int aluVal2;
	int regWAddr1;
	boolean regWE1;
	int regWAddr2;
	boolean regWE2;
	int regAddr;
	
	// out
	int valx;
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		regVal = signal.getRegVal1_Reg();
		aluVal1 = signal.getRet_ALU();
		aluVal2 = signal.getALUValOut_EXE();
		regWAddr1 = signal.getRegWAddrOut_ID();
		regWE1 = signal.isRegWEOut_ID();
		regWAddr2 = signal.getRegWAddrOut_EXE();
		regWE2 = signal.isRegWEOut_EXE();
		regAddr = signal.getRegAddr1_Ctrl();
	}
	
	private void run() {
		if(regAddr == regWAddr1 && regWE1)
			valx = aluVal1;
		else if(regAddr == regWAddr2 && regWE2)
			valx = aluVal2;
		else
			valx = regVal;	
	}
	
	private void set() {
		next.setVal_T(valx);
	}
	
	public static void main(String args[]) {

	}
}
