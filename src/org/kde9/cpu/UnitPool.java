package org.kde9.cpu;

import org.kde9.alu.ALU;
import org.kde9.control.Control;
import org.kde9.exceptions.AlreadyExist;
import org.kde9.memory.Cache;
import org.kde9.memory.MemInterface;
import org.kde9.memory.Memory;
import org.kde9.others.ChoALU1;
import org.kde9.others.ChoALU2;
import org.kde9.others.ChoPCCtrl;
import org.kde9.others.ChoRegWVal;
import org.kde9.others.Stop2Period;
import org.kde9.others.Transfer;
import org.kde9.others.TransferChoPCVal;

public class UnitPool {
	static Memory memory = new Memory();
	static Cache cache = new Cache();
	static MemInterface memInterface = new MemInterface();
	static ALU alu = new ALU();
	static Control control = new Control();
	static ChoALU1 choALU1 = new ChoALU1();
	static ChoALU2 choALU2 = new ChoALU2();
	static ChoPCCtrl choPCCtrl = new ChoPCCtrl();
	static ChoRegWVal choRegWVal = new ChoRegWVal();
	static Stop2Period stop2Period = new Stop2Period();
	static Transfer transfer = new Transfer();
	static TransferChoPCVal transferChoPCVal = new TransferChoPCVal();
	
	
	public static Memory getMemory() {
		return memory;
	}	
	public static Cache getCache() {
		return cache;
	}
	public static MemInterface getMemInterface() {
		return memInterface;
	}
	public static ALU getAlu() {
		return alu;
	}
	public static Control getControl() {
		return control;
	}
	public static ChoALU1 getChoALU1() {
		return choALU1;
	}
	public static ChoALU2 getChoALU2() {
		return choALU2;
	}
	public static ChoPCCtrl getChoPCCtrl() {
		return choPCCtrl;
	}
	public static ChoRegWVal getChoRegWVal() {
		return choRegWVal;
	}
	public static Stop2Period getStop2Period() {
		return stop2Period;
	}
	public static Transfer getTransfer() {
		return transfer;
	}
	public static TransferChoPCVal getTransferChoPCVal() {
		return transferChoPCVal;
	}
	
}
