package org.kde9.register;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.exceptions.AlreadyExist;
import org.kde9.exceptions.DonotExist;
import org.kde9.util.Constants;

public class RegisterHeap
implements Constants {
	Signals signal;
	Signals next;
	
	Registers rs;
	
	// in
	int RegAddr1;
	int RegAddr2;
	boolean WE;
	int RegWAddr;
	int RegWVal;
	boolean storePC;
	int PC;
	boolean reset;
	
	// out
	int RegVal1;
	int RegVal2;
	
	public RegisterHeap() 
	throws AlreadyExist {
		rs = new Registers();
		for(int i = 0; i < 15; i++)
			rs.addReg(String.valueOf(i));
	}
	
	public void start(boolean r) 
	throws DonotExist {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check(r);
		run();
		set();
	}
	
	public void check(boolean r) {
		reset = r;
		RegAddr1 = signal.getRegAddr1_Ctrl();
		RegAddr2 = signal.getRegAddr2_Ctrl();
		WE = signal.isRegWEOut_MEM();
		RegWAddr = signal.getRegWAddrOut_MEM();
		RegWVal = signal.getRegWVal_CReg();
		storePC = signal.isStorePC_Ctrl();
		PC = signal.getPCOut_IF();
	}
	
	private void run() 
	throws DonotExist {
		if(reset) {
			rs.clear();
			RegVal1 = REGISTER_INITIAL_VALUE;
			RegVal2 = REGISTER_INITIAL_VALUE;
		} else { 
			if(WE) {
				rs.write(String.valueOf(RegWAddr), RegWVal);
			}
			RegVal1 = rs.read(String.valueOf(RegAddr1));
			RegVal2 = rs.read(String.valueOf(RegAddr2));
		}
		if(storePC)
			rs.write("13", PC);
		if(RegAddr1 == 9)
			RegVal1 = PC;
	}
	
	private void set() {
		next.setRegVal1_Reg(RegVal1);
		next.setRegVal2_Reg(RegVal2);
	}
	
	public static void main(String args[]) {
		
	}
}
