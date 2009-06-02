package org.kde9.register;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.cpu.UnitPool;
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
	
	public RegisterHeap() {
		rs = new Registers();
		for(int i = 0; i < 32; i++)
			try {
				rs.addReg(String.valueOf(i));
			} catch (AlreadyExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public Registers getRs() {
		return rs;
	}
	
	public void start(boolean r) {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check(r);
		try {
			run();
		} catch (DonotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		set();
	}
	
	public void check(boolean r) {
		reset = r;
		RegAddr1 = next.getRegAddr1_Ctrl();
		RegAddr2 = next.getRegAddr2_Ctrl();
		WE = next.isRegWEOut_MEM();
		RegWAddr = next.getRegWAddrOut_MEM();
		RegWVal = next.getRegWVal_CReg();
		storePC = next.isStorePC_Ctrl();
		PC = next.getPCOut_IF();
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
			rs.write(String.valueOf(RA_Reg), PC);
		if(RegAddr1 == PC_Reg)
			RegVal1 = PC;
	}
	
	private void set() {
		next.setRegVal1_Reg(RegVal1);
		next.setRegVal2_Reg(RegVal2);
	}
	
	public static void main(String args[]) {
		
	}
}
