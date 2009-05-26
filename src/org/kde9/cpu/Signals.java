package org.kde9.cpu;

public class Signals {
	// PCUnit
	private int PC_PC;
	
	// MemInterface
	private int Ins_Mem;
	
	// IF2IDReg
	private int PCOut_IF;
	private int InsOut_IF;
	
	// control
	private int RegAddr1_Ctrl;
	private boolean RegAddrE1_Ctrl;
	private int RegAddr2_Ctrl;
	private boolean RegAddrE2_Ctrl;
	private int RegWAddr_Ctrl;
	private int Im_Ctrl;
	private boolean CChoALU2_Ctrl;
	private int CChoRegWVal_Ctrl;
	private int CChoPCCtrl_Ctrl;
	private int CALU_Ctrl;
	private boolean CRegWE_Ctrl;
	private boolean CMemWE_Ctrl;
	private boolean storePC_Ctrl;
	private boolean needStop_Ctrl;
	private boolean islwsw_Ctrl;
	
	// Stop2Period
	private boolean nreset_Stop;
	private boolean hold_Stop;
	
	// RegHeap
	private int RegVal1_Reg;
	private int RegVal2_Reg;
	
	// ID2EXEReg
	private int RegValOut1_ID;
	private int RegValOut2_ID;
	private int RegAddrOut1_ID;
	private int RegAddrOut2_ID;
	private boolean RegEOut1_ID;
	private boolean RegEOut2_ID;  
	private int ImOut_ID;	
	private int RegWAddrOut_ID;
	private int ALUCtrlOut_ID;
	private boolean MemWEOut_ID;
	private boolean RegWEOut_ID;
	private boolean CChoALUOut2_ID;
	private int CChoRegWValOut_ID;
	private boolean islwswOut_ID;
	
	// ALU
	private int ret_ALU;
	private boolean T_ALU;
	
	// ChoALU1
	private int a_CALU1;
	
	// ChoALU2
	private int b_CALU2;
	private int RegVal2_CALU2;
	
	// EXE2MEMReg
	private int ALUValOut_EXE;
	private int RegValOut2_EXE;
	private boolean TOut_EXE;
	private int RegWAddrOut_EXE;
	private boolean MemWEOut_EXE;
	private boolean RegWEOut_EXE;
	private int CChoRegWValOut_EXE;
	private boolean islwswOut_EXE;
	
	// MEM2WBReg
	private int ALUValOut_MEM;	
	private int MemValOut_MEM;
	private boolean TOut_MEM;
	private int RegWAddrOut_MEM;
	private boolean RegWEOut_MEM;
	private int CChoRegWValOut_MEM;
	
	// ChoRegWVal
	private int RegWVal_CReg;
	
	// transfer
	private int TChoALU1_T;
	private int TChoALU2_T;
	
	// transferChoPCVal
	private int Val_T;
	
	// ChoPCCtrl
	private int lastPC_CPC;

	public int getPC_PC() {
		return PC_PC;
	}

	public void setPC_PC(int pc_pc) {
		PC_PC = pc_pc;
	}

	public int getIns_Mem() {
		return Ins_Mem;
	}

	public void setIns_Mem(int ins_Mem) {
		Ins_Mem = ins_Mem;
	}

	public int getPCOut_IF() {
		return PCOut_IF;
	}

	public void setPCOut_IF(int out_IF) {
		PCOut_IF = out_IF;
	}

	public int getInsOut_IF() {
		return InsOut_IF;
	}

	public void setInsOut_IF(int insOut_IF) {
		InsOut_IF = insOut_IF;
	}

	public int getRegAddr1_Ctrl() {
		return RegAddr1_Ctrl;
	}

	public void setRegAddr1_Ctrl(int regAddr1_Ctrl) {
		RegAddr1_Ctrl = regAddr1_Ctrl;
	}

	public boolean isRegAddrE1_Ctrl() {
		return RegAddrE1_Ctrl;
	}

	public void setRegAddrE1_Ctrl(boolean regAddrE1_Ctrl) {
		RegAddrE1_Ctrl = regAddrE1_Ctrl;
	}

	public int getRegAddr2_Ctrl() {
		return RegAddr2_Ctrl;
	}

	public void setRegAddr2_Ctrl(int regAddr2_Ctrl) {
		RegAddr2_Ctrl = regAddr2_Ctrl;
	}

	public boolean isRegAddrE2_Ctrl() {
		return RegAddrE2_Ctrl;
	}

	public void setRegAddrE2_Ctrl(boolean regAddrE2_Ctrl) {
		RegAddrE2_Ctrl = regAddrE2_Ctrl;
	}

	public int getRegWAddr_Ctrl() {
		return RegWAddr_Ctrl;
	}

	public void setRegWAddr_Ctrl(int regWAddr_Ctrl) {
		RegWAddr_Ctrl = regWAddr_Ctrl;
	}

	public int getIm_Ctrl() {
		return Im_Ctrl;
	}

	public void setIm_Ctrl(int im_Ctrl) {
		Im_Ctrl = im_Ctrl;
	}

	public boolean isCChoALU2_Ctrl() {
		return CChoALU2_Ctrl;
	}

	public void setCChoALU2_Ctrl(boolean choALU2_Ctrl) {
		CChoALU2_Ctrl = choALU2_Ctrl;
	}

	public int getCChoRegWVal_Ctrl() {
		return CChoRegWVal_Ctrl;
	}

	public void setCChoRegWVal_Ctrl(int choRegWVal_Ctrl) {
		CChoRegWVal_Ctrl = choRegWVal_Ctrl;
	}

	public int getCChoPCCtrl_Ctrl() {
		return CChoPCCtrl_Ctrl;
	}

	public void setCChoPCCtrl_Ctrl(int choPCCtrl_Ctrl) {
		CChoPCCtrl_Ctrl = choPCCtrl_Ctrl;
	}

	public int getCALU_Ctrl() {
		return CALU_Ctrl;
	}

	public void setCALU_Ctrl(int ctrl) {
		CALU_Ctrl = ctrl;
	}

	public boolean isCRegWE_Ctrl() {
		return CRegWE_Ctrl;
	}

	public void setCRegWE_Ctrl(boolean regWE_Ctrl) {
		CRegWE_Ctrl = regWE_Ctrl;
	}

	public boolean isCMemWE_Ctrl() {
		return CMemWE_Ctrl;
	}

	public void setCMemWE_Ctrl(boolean memWE_Ctrl) {
		CMemWE_Ctrl = memWE_Ctrl;
	}

	public boolean isStorePC_Ctrl() {
		return storePC_Ctrl;
	}

	public void setStorePC_Ctrl(boolean storePC_Ctrl) {
		this.storePC_Ctrl = storePC_Ctrl;
	}

	public boolean isNeedStop_Ctrl() {
		return needStop_Ctrl;
	}

	public void setNeedStop_Ctrl(boolean needStop_Ctrl) {
		this.needStop_Ctrl = needStop_Ctrl;
	}

	public boolean isIslwsw_Ctrl() {
		return islwsw_Ctrl;
	}

	public void setIslwsw_Ctrl(boolean islwsw_Ctrl) {
		this.islwsw_Ctrl = islwsw_Ctrl;
	}

	public boolean isNreset_Stop() {
		return nreset_Stop;
	}

	public void setNreset_Stop(boolean nreset_Stop) {
		this.nreset_Stop = nreset_Stop;
	}

	public boolean isHold_Stop() {
		return hold_Stop;
	}

	public void setHold_Stop(boolean hold_Stop) {
		this.hold_Stop = hold_Stop;
	}

	public int getRegVal1_Reg() {
		return RegVal1_Reg;
	}

	public void setRegVal1_Reg(int regVal1_Reg) {
		RegVal1_Reg = regVal1_Reg;
	}

	public int getRegVal2_Reg() {
		return RegVal2_Reg;
	}

	public void setRegVal2_Reg(int regVal2_Reg) {
		RegVal2_Reg = regVal2_Reg;
	}

	public int getRegValOut1_ID() {
		return RegValOut1_ID;
	}

	public void setRegValOut1_ID(int regValOut1_ID) {
		RegValOut1_ID = regValOut1_ID;
	}

	public int getRegValOut2_ID() {
		return RegValOut2_ID;
	}

	public void setRegValOut2_ID(int regValOut2_ID) {
		RegValOut2_ID = regValOut2_ID;
	}

	public int getRegAddrOut1_ID() {
		return RegAddrOut1_ID;
	}

	public void setRegAddrOut1_ID(int regAddrOut1_ID) {
		RegAddrOut1_ID = regAddrOut1_ID;
	}

	public int getRegAddrOut2_ID() {
		return RegAddrOut2_ID;
	}

	public void setRegAddrOut2_ID(int regAddrOut2_ID) {
		RegAddrOut2_ID = regAddrOut2_ID;
	}

	public boolean isRegEOut1_ID() {
		return RegEOut1_ID;
	}

	public void setRegEOut1_ID(boolean regEOut1_ID) {
		RegEOut1_ID = regEOut1_ID;
	}

	public boolean isRegEOut2_ID() {
		return RegEOut2_ID;
	}

	public void setRegEOut2_ID(boolean regEOut2_ID) {
		RegEOut2_ID = regEOut2_ID;
	}

	public int getImOut_ID() {
		return ImOut_ID;
	}

	public void setImOut_ID(int imOut_ID) {
		ImOut_ID = imOut_ID;
	}

	public int getRegWAddrOut_ID() {
		return RegWAddrOut_ID;
	}

	public void setRegWAddrOut_ID(int regWAddrOut_ID) {
		RegWAddrOut_ID = regWAddrOut_ID;
	}

	public int getALUCtrlOut_ID() {
		return ALUCtrlOut_ID;
	}

	public void setALUCtrlOut_ID(int ctrlOut_ID) {
		ALUCtrlOut_ID = ctrlOut_ID;
	}

	public boolean isMemWEOut_ID() {
		return MemWEOut_ID;
	}

	public void setMemWEOut_ID(boolean memWEOut_ID) {
		MemWEOut_ID = memWEOut_ID;
	}

	public boolean isRegWEOut_ID() {
		return RegWEOut_ID;
	}

	public void setRegWEOut_ID(boolean regWEOut_ID) {
		RegWEOut_ID = regWEOut_ID;
	}

	public boolean isCChoALUOut2_ID() {
		return CChoALUOut2_ID;
	}

	public void setCChoALUOut2_ID(boolean choALUOut2_ID) {
		CChoALUOut2_ID = choALUOut2_ID;
	}

	public int getCChoRegWValOut_ID() {
		return CChoRegWValOut_ID;
	}

	public void setCChoRegWValOut_ID(int choRegWValOut_ID) {
		CChoRegWValOut_ID = choRegWValOut_ID;
	}

	public boolean isIslwswOut_ID() {
		return islwswOut_ID;
	}

	public void setIslwswOut_ID(boolean islwswOut_ID) {
		this.islwswOut_ID = islwswOut_ID;
	}

	public int getRet_ALU() {
		return ret_ALU;
	}

	public void setRet_ALU(int ret_ALU) {
		this.ret_ALU = ret_ALU;
	}

	public boolean isT_ALU() {
		return T_ALU;
	}

	public void setT_ALU(boolean t_alu) {
		T_ALU = t_alu;
	}

	public int getA_CALU1() {
		return a_CALU1;
	}

	public void setA_CALU1(int a_calu1) {
		a_CALU1 = a_calu1;
	}

	public int getB_CALU2() {
		return b_CALU2;
	}

	public void setB_CALU2(int b_calu2) {
		b_CALU2 = b_calu2;
	}

	public int getRegVal2_CALU2() {
		return RegVal2_CALU2;
	}

	public void setRegVal2_CALU2(int regVal2_CALU2) {
		RegVal2_CALU2 = regVal2_CALU2;
	}

	public int getALUValOut_EXE() {
		return ALUValOut_EXE;
	}

	public void setALUValOut_EXE(int valOut_EXE) {
		ALUValOut_EXE = valOut_EXE;
	}

	public int getRegValOut2_EXE() {
		return RegValOut2_EXE;
	}

	public void setRegValOut2_EXE(int regValOut2_EXE) {
		RegValOut2_EXE = regValOut2_EXE;
	}

	public boolean isTOut_EXE() {
		return TOut_EXE;
	}

	public void setTOut_EXE(boolean out_EXE) {
		TOut_EXE = out_EXE;
	}

	public int getRegWAddrOut_EXE() {
		return RegWAddrOut_EXE;
	}

	public void setRegWAddrOut_EXE(int regWAddrOut_EXE) {
		RegWAddrOut_EXE = regWAddrOut_EXE;
	}

	public boolean isMemWEOut_EXE() {
		return MemWEOut_EXE;
	}

	public void setMemWEOut_EXE(boolean memWEOut_EXE) {
		MemWEOut_EXE = memWEOut_EXE;
	}

	public boolean isRegWEOut_EXE() {
		return RegWEOut_EXE;
	}

	public void setRegWEOut_EXE(boolean regWEOut_EXE) {
		RegWEOut_EXE = regWEOut_EXE;
	}

	public int getCChoRegWValOut_EXE() {
		return CChoRegWValOut_EXE;
	}

	public void setCChoRegWValOut_EXE(int choRegWValOut_EXE) {
		CChoRegWValOut_EXE = choRegWValOut_EXE;
	}

	public boolean isIslwswOut_EXE() {
		return islwswOut_EXE;
	}

	public void setIslwswOut_EXE(boolean islwswOut_EXE) {
		this.islwswOut_EXE = islwswOut_EXE;
	}

	public int getALUValOut_MEM() {
		return ALUValOut_MEM;
	}

	public void setALUValOut_MEM(int valOut_MEM) {
		ALUValOut_MEM = valOut_MEM;
	}

	public int getMemValOut_MEM() {
		return MemValOut_MEM;
	}

	public void setMemValOut_MEM(int memValOut_MEM) {
		MemValOut_MEM = memValOut_MEM;
	}

	public boolean isTOut_MEM() {
		return TOut_MEM;
	}

	public void setTOut_MEM(boolean out_MEM) {
		TOut_MEM = out_MEM;
	}

	public int getRegWAddrOut_MEM() {
		return RegWAddrOut_MEM;
	}

	public void setRegWAddrOut_MEM(int regWAddrOut_MEM) {
		RegWAddrOut_MEM = regWAddrOut_MEM;
	}

	public boolean isRegWEOut_MEM() {
		return RegWEOut_MEM;
	}

	public void setRegWEOut_MEM(boolean regWEOut_MEM) {
		RegWEOut_MEM = regWEOut_MEM;
	}

	public int getCChoRegWValOut_MEM() {
		return CChoRegWValOut_MEM;
	}

	public void setCChoRegWValOut_MEM(int choRegWValOut_MEM) {
		CChoRegWValOut_MEM = choRegWValOut_MEM;
	}

	public int getRegWVal_CReg() {
		return RegWVal_CReg;
	}

	public void setRegWVal_CReg(int regWVal_CReg) {
		RegWVal_CReg = regWVal_CReg;
	}

	public int getTChoALU1_T() {
		return TChoALU1_T;
	}

	public void setTChoALU1_T(int choALU1_T) {
		TChoALU1_T = choALU1_T;
	}

	public int getTChoALU2_T() {
		return TChoALU2_T;
	}

	public void setTChoALU2_T(int choALU2_T) {
		TChoALU2_T = choALU2_T;
	}

	public int getVal_T() {
		return Val_T;
	}

	public void setVal_T(int val_T) {
		Val_T = val_T;
	}

	public int getLastPC_CPC() {
		return lastPC_CPC;
	}

	public void setLastPC_CPC(int lastPC_CPC) {
		this.lastPC_CPC = lastPC_CPC;
	}
}
