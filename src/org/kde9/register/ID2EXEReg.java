package org.kde9.register;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.util.Constants;

public class ID2EXEReg 
implements Constants {
	Signals signal;
	Signals next;
	
	int RegValIn1;  //输入的第一个寄存器值
	int RegValOut1;  //输出的第一个寄存器值
	
	int RegValIn2;  //输入的第二个寄存器值
	int RegValOut2;  //输出的第二个寄存器值
	
	int RegAddrIn1;  //输入??第一个寄存器??址
	int RegAddrOut1;  //输出的第一个寄存器地址
	
	int RegAddrIn2;  //输入的第二个寄存器地址
	int RegAddrOut2;  //输出的第二个寄存器地址
	
	boolean RegEIn1;  //输入的第一个寄存器是否有效
	boolean RegEOut1;  //输出的第一个寄存器是否有效
	
	boolean RegEIn2;  //输入的第二个寄存器是否有效
	boolean RegEOut2;  //输出的第二个寄存器是否有效
	
	int ImIn;  //输入??立即数
	int ImOut;  //输出的立即数
	
	int RegWAddrIn;  //输入的要写入的寄存器地址
	int RegWAddrOut;  //输出的要写入的寄存器地址
	
	int ALUCtrlIn;  //输入的ALU控制码
	int ALUCtrlOut;  //输出的ALU控制码
	
	boolean MemWEIn;  //输入的Mem写使能
	boolean MemWEOut;  //输出的Mem写使能
	
	boolean RegWEIn;  //输入的Reg写使能
	boolean RegWEOut;  //输出的Reg写使能
	
	boolean CChoALUIn2;  //输入的ALU第二输入选择器控制码
	boolean CChoALUOut2;  //输出的ALU第二输入选择器控制码
	
	int CChoRegWValIn;  //输入的寄存器数据写回选择器控制码
	int CChoRegWValOut;
	
	boolean islwswIn;
	boolean islwswOut;
	
	boolean reset;  //异步???唬?1'为清零
	boolean nreset;  //同步复?唬敝由仙厍?为'1'??清零
	
	int pcin;
	int pcout;
	
	public void start(boolean r) {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check(r);
		run();
		set();
	}
	
	public void check(boolean r) {
		if(signal.getInsOut_IF() == NOP_INS)
			pcin = -1;
		else
			pcin = signal.getPCOut_IF();
		RegValIn1 = signal.getRegVal1_Reg();
		RegValIn2 = signal.getRegVal2_Reg();
		RegAddrIn1 = signal.getRegAddr1_Ctrl();
		RegAddrIn2 = signal.getRegAddr2_Ctrl();
		RegEIn1 = signal.isRegAddrE1_Ctrl();
		RegEIn2 = signal.isRegAddrE2_Ctrl();
		ImIn = signal.getIm_Ctrl();
		RegWAddrIn = signal.getRegWAddr_Ctrl();
		ALUCtrlIn = signal.getCALU_Ctrl();
		MemWEIn = signal.isCMemWE_Ctrl();
		RegWEIn = signal.isCRegWE_Ctrl();
		CChoALUIn2 = signal.isCChoALU2_Ctrl();
		CChoRegWValIn = signal.getCChoRegWVal_Ctrl();
		islwswIn = signal.isIslwsw_Ctrl();
		reset = r;
		nreset = false;
	}
	
	private void run() {
		if(reset) {
			pcout = -1;
			RegValOut1 = 0;
			RegValOut2 = 0;
			RegEOut1 = false;
			RegEOut2 = false;
			RegAddrOut1 = 0;
			RegAddrOut2 = 0;
			ImOut = 0;
			RegWAddrOut = 0;
			ALUCtrlOut = 0;
			MemWEOut = false;
			RegWEOut = false;
			CChoALUOut2 = false;
			CChoRegWValOut = 0;
			islwswOut = false;
		} else {
			pcout = pcin;
			RegValOut1 = RegValIn1;
			RegValOut2 = RegValIn2;
			RegEOut1 = RegEIn1;
			RegEOut2 = RegEIn2;
			RegAddrOut1 = RegAddrIn1;
			RegAddrOut2 = RegAddrIn2;
			ImOut = ImIn;
			RegWAddrOut = RegWAddrIn;
			ALUCtrlOut = ALUCtrlIn;
			MemWEOut = MemWEIn;
			RegWEOut = RegWEIn;
			CChoALUOut2 = CChoALUIn2;
			CChoRegWValOut =CChoRegWValIn;
			islwswOut = islwswIn;
		}
	}
	
	private void set() {
		next.setPCOut_ID(pcout);
		next.setRegValOut1_ID(RegValOut1);
		next.setRegValOut2_ID(RegValOut2);
		next.setRegEOut1_ID(RegEOut1);
		next.setRegEOut2_ID(RegEOut2);
		next.setRegAddrOut1_ID(RegAddrOut1);
		next.setRegAddrOut2_ID(RegAddrOut2);
		next.setImOut_ID(ImOut);
		next.setRegWAddrOut_ID(RegWAddrOut);
		next.setALUCtrlOut_ID(ALUCtrlOut);
		next.setMemWEOut_ID(MemWEOut);
		next.setRegWEOut_ID(RegWEOut);
		next.setCChoALUOut2_ID(CChoALUOut2);
		next.setCChoRegWValOut_ID(CChoRegWValOut);
		next.setIslwswOut_ID(islwswOut);
	}
	
	public static void main(String args[]) {

	}
}
