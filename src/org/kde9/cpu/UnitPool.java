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
import org.kde9.pcunit.PCUnit;
import org.kde9.register.EXE2MEMReg;
import org.kde9.register.ID2EXEReg;
import org.kde9.register.IF2IDReg;
import org.kde9.register.MEM2WBReg;
import org.kde9.register.RegisterHeap;
import org.kde9.register.Registers;

public class UnitPool {
	static PCUnit pc = new PCUnit();
	static MemInterface memInterface = new MemInterface();
	static Memory memory;
	static Cache insCache;
	static Cache dataCache;
	static Registers registers;
	static ALU alu = new ALU();
	static Control control = new Control();
	static ChoALU1 choALU1 = new ChoALU1();
	static ChoALU2 choALU2 = new ChoALU2();
	static ChoPCCtrl choPCCtrl = new ChoPCCtrl();
	static ChoRegWVal choRegWVal = new ChoRegWVal();
	static Stop2Period stop2Period = new Stop2Period();
	static Transfer transfer = new Transfer();
	static TransferChoPCVal transferChoPCVal = new TransferChoPCVal();
	static IF2IDReg if2id = new IF2IDReg();
	static ID2EXEReg id2exe = new ID2EXEReg();
	static EXE2MEMReg exe2mem = new EXE2MEMReg();
	static RegisterHeap reg = new RegisterHeap();
	static MEM2WBReg mem2wb = new MEM2WBReg();
	static FPGA fpga = new FPGA();
	
	public static FPGA getFpga() {
		return fpga;
	}

	public static RegisterHeap getReg() {
		return reg;
	}
	
	public static void setMemory(Memory memory) {
		UnitPool.memory = memory;
	}

	public static void setInsCache(Cache insCache) {
		UnitPool.insCache = insCache;
	}

	public static void setDataCache(Cache dataCache) {
		UnitPool.dataCache = dataCache;
	}

	public static void setRegisters(Registers registers) {
		UnitPool.registers = registers;
	}
	
	public static Memory getMemory() {
		return memory;
	}

	public static Cache getInsCache() {
		return insCache;
	}

	public static Cache getDataCache() {
		return dataCache;
	}
	
	public static Registers getRegisters() {
		return registers;
	}

	public static IF2IDReg getIf2id() {
		return if2id;
	}

	public static ID2EXEReg getId2exe() {
		return id2exe;
	}

	public static EXE2MEMReg getExe2mem() {
		return exe2mem;
	}

//	public static RegisterHeap getReg() {
//		return reg;
//	}

	public static MEM2WBReg getMem2wb() {
		return mem2wb;
	}

	public static PCUnit getPc() {
		return pc;
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
