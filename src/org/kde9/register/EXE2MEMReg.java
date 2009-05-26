package org.kde9.register;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class EXE2MEMReg {
	Signals signal;
	Signals next;
	
	int ALUValIn;
	int ALUValOut;
	
	int RegValIn2;  //输入的第二个寄存器值
	int RegValOut2;  //输出的第二个寄存器值
	
	boolean TIn; 
	boolean TOut; 
	
	int RegWAddrIn;  //输入的要写入的寄存器地址
	int RegWAddrOut;  //输出的要写入的寄存器地址
	
	boolean MemWEIn;  //输入的Mem写使能
	boolean MemWEOut;  //输出的Mem写使能
	
	boolean RegWEIn;  //输入的Reg写使能
	boolean RegWEOut;  //输出的Reg写使能
	
	int CChoRegWValIn;  //输入的寄存器数据写回选择器控制码
	int CChoRegWValOut;
	
	boolean islwswIn;
	boolean islwswOut;
	
	boolean reset;  //异步???唬?1'为清零
	boolean nreset;  //同步复?唬敝由仙厍?为'1'??清零
	
	public void start(boolean r) {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check(r);
		run();
		set();
	}
	
	public void check(boolean r) {
		ALUValIn = signal.getRet_ALU();
		RegValIn2 = signal.getRegVal2_CALU2();
		TIn = signal.isT_ALU();
		RegWAddrIn = signal.getRegWAddrOut_ID();
		MemWEIn = signal.isMemWEOut_ID();
		RegWEIn = signal.isRegWEOut_ID();
		CChoRegWValIn = signal.getCChoRegWValOut_ID();
		islwswIn = signal.isIslwswOut_ID();
		reset = r;
		nreset = false;
	}
	
	private void run() {
		if(reset) {
			RegValOut2 = 0;
			RegWAddrOut = 0;
			MemWEOut = false;
			RegWEOut = false;
			CChoRegWValOut = 0;
			islwswOut = false;
		} else {
			RegValOut2 = RegValIn2;
			RegWAddrOut = RegWAddrIn;
			MemWEOut = MemWEIn;
			RegWEOut = RegWEIn;
			CChoRegWValOut =CChoRegWValIn;
			islwswOut = islwswIn;
		}
	}
	
	private void set() {
		next.setALUValOut_EXE(ALUValOut);
		next.setRegValOut2_EXE(RegValOut2);
		next.setTOut_EXE(TOut);
		next.setRegWAddrOut_EXE(RegWAddrOut);
		next.setMemWEOut_EXE(MemWEOut);
		next.setRegWEOut_EXE(RegWEOut);
		next.setCChoRegWValOut_EXE(CChoRegWValOut);
		next.setIslwswOut_EXE(islwswOut);
	}
	
	public static void main(String args[]) {

	}
}
