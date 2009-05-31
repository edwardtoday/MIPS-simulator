package org.kde9.cpu;

import org.kde9.alu.ALU;
import org.kde9.control.Control;
import org.kde9.exceptions.DonotExist;
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

import sun.misc.Signal;

public class CPU {
	static int circle = 0;
	String[] T = {"Õý³£", "", "MEM", "WB"};
	
	PCUnit pcUnit;
	MemInterface memInterface;
	ALU alu;
	Control control;
	ChoALU1 choALU1;
	ChoALU2 choALU2;
	ChoPCCtrl choPCCtrl;
	ChoRegWVal choRegWVal;
	Stop2Period stop2Period;
	Transfer transfer;
	TransferChoPCVal transferChoPCVal;
	IF2IDReg if2id;
	ID2EXEReg id2exe;
	EXE2MEMReg exe2mem;
	RegisterHeap reg;
	MEM2WBReg mem2wb;
	
	public CPU() {
		pcUnit = UnitPool.getPc();
		memInterface = UnitPool.getMemInterface();
		alu = UnitPool.getAlu();
		control = UnitPool.getControl();
		choALU1 = UnitPool.getChoALU1();
		choALU2 = UnitPool.getChoALU2();
		choPCCtrl = UnitPool.getChoPCCtrl();
		choRegWVal = UnitPool.getChoRegWVal();
		stop2Period = UnitPool.getStop2Period();
		transfer = UnitPool.getTransfer();
		transferChoPCVal = UnitPool.getTransferChoPCVal();
		if2id = UnitPool.getIf2id();
		id2exe = UnitPool.getId2exe();
		exe2mem = UnitPool.getExe2mem();
		reg = UnitPool.getReg();
		mem2wb = UnitPool.getMem2wb();
		UnitPool.setMemory(memInterface.getMem());
		UnitPool.setDataCache(memInterface.getDataCache());
		UnitPool.setInsCache(memInterface.getInsCache());
		UnitPool.setRegisters(reg.getRs());
	}
	
	public boolean circle(boolean reset) {
		Integer ins = SignalPool.getCurrentSignals().getIns_Mem();
		boolean islwsw = SignalPool.getCurrentSignals().isIslwswOut_EXE();
		if(!islwsw && ins != null && ins == 0xfc000000) {
			System.out.println("Halt!!!!!!!");
			return false;
		}
		pcUnit.start(reset);
		if2id.start(reset);
		id2exe.start(reset);
		exe2mem.start(reset);
		mem2wb.start(reset);
		control.start();
		memInterface.start();
		stop2Period.start(reset);
		choRegWVal.start();
		reg.start(reset);
		transfer.start();
		choALU1.start();
		choALU2.start();
		alu.start();
		transferChoPCVal.start();
		choPCCtrl.start();
		SignalPool.next();
		
		Signals s = SignalPool.getCurrentSignals();
		System.out.print("IF:  ");
		System.out.println("PC " + s.getPC_PC() + "  LastPc " + s.getLastPC_CPC() + "  Ins " + s.getIns_Mem() + "/" + s.isReady_Mem());
		System.out.print("ID:  ");
		System.out.print("Ins " + s.getInsOut_IF());
		System.out.println("  Reg" + " [1 " + s.getRegAddr1_Ctrl() + "/" + s.getRegVal1_Reg() + "]"
				+ " [2 " + s.getRegAddr2_Ctrl() + "/" + s.getRegVal2_Reg() + "]" + "  Im " + s.getIm_Ctrl());
		System.out.println("     ChoPcCtrl " + s.getCChoPCCtrl_Ctrl() + "  Val " + s.getVal_T());
		System.out.print("EXE: ");
		System.out.print("Reg" + " [1 " + s.getRegAddrOut1_ID() + "/" + s.getRegValOut1_ID() + " " + s.isRegEOut1_ID() + "]"
				+ " [2 " + s.getRegAddrOut2_ID() + "/" + s.getRegValOut2_ID() + " " + s.isRegEOut2_ID() + "]  ");
		System.out.println("ALU" + "(" + s.getALUCtrlOut_ID() + ")" + " [" + s.getA_CALU1() + " " + s.getB_CALU2() + "] " + s.getRet_ALU());
		System.out.println("     transfer" + " [1 " + T[s.getTChoALU1_T()] + "] [2 " + T[s.getTChoALU2_T()] + "]" + "  Im " + s.getImOut_ID());
		System.out.print("MEM: ");
		System.out.println("RegW [" + s.getRegWAddrOut_EXE() + " " + s.isRegWEOut_EXE() + "]" + "  Mem [" + s.getRegValOut2_EXE() + "/" + s.getMemValOut_MEM() + "]");
		System.out.print("WB:  ");
		System.out.println("RegW [" + s.getRegWAddrOut_MEM() + "/" + s.getRegWVal_CReg() + " " + s.isRegWEOut_MEM() + "]");
		System.out.println("***************************************************");
		
		return true;
	}
	
	public static void main(String args[]) {
		CPU cpu = new CPU();
		try {
			UnitPool.getRegisters().write("0", 0);
			UnitPool.getRegisters().write("1", 3);
			UnitPool.getRegisters().write("2", -4);
//			UnitPool.getMemory().write("data", 0, 0x00011020);
			UnitPool.getInsCache().write(0, 0x00011020);
//			UnitPool.getMemory().write("data", 1, 0x00410020);
			UnitPool.getInsCache().write(1, 0x00401822);
			UnitPool.getInsCache().write(2, 0x2000000a);
			UnitPool.getInsCache().write(3, 0x0bffffff);
//			UnitPool.getMemory().write("data", 2, 0x00011020);
//			UnitPool.getMemory().write("data", 3, 0x00410020);
			for(int i = 0; i < 15; i++) {
				cpu.circle(false);
			}
			System.out.println("r0 " + UnitPool.getRegisters().read("0"));
			System.out.println("r1 " + UnitPool.getRegisters().read("1"));
			System.out.println("r2 " + UnitPool.getRegisters().read("2"));
			System.out.println("r3 " + UnitPool.getRegisters().read("3"));
			System.out.println(Integer.toBinaryString(2));
		} catch (DonotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
