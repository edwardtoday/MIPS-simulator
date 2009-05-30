package org.kde9.control;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.util.Constants;

public class Control 
implements Constants {
	Signals signal;
	Signals next;
	
	// in
	int Ins;  //输入指令编码
	
	// out
	int RegAddr1;  //输出第一个寄存器号
	boolean RegAddrE1;  //输出第一个寄存器号是否有效，'1'为有效
	int RegAddr2;  //输出第二个寄存器号
	boolean RegAddrE2;  //输??第二个寄存器号是否有效，'1'为有??
	int RegWAddr;  //输出要写入的寄存器号
	int Im;  //输出指令中的立即数	
	boolean CChoALU2;  //ALU第二输入 数据选择器的控制码
	int CChoRegWVal;  //寄存器的写数据 数据选择器控制码
	int CChoPCCtrl;  //PC加法 数据选择器??制码
	int CALU;  //ALU 控制
	boolean CRegWE;  //寄??器堆写使能 控制，'1'为写
	boolean CMemWE;  //内存写???? 控制，'1'为写
	boolean storePC;  //是否需要存储PC
	boolean needStop;  //是否需要暂停，'1'为是
	boolean islwsw;
	
	private static int cutInt(int ins, int a, int b) {
		int aa = (int) Math.pow(2, a);
		int bb = (int) Math.pow(2, b+1);
		int cc = (int) Math.pow(2, b+1-a);
		return ins/aa - ins/bb*cc;
	}
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		Ins = next.getInsOut_IF();
	}
	
	private void run() {
		if(cutInt(Ins, 26, 31) == 0) {
			if(cutInt(Ins, 0, 10) == 0x20) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Add_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
		} else {
			RegAddr1 = 0;  //输出第一个寄存器号
			RegAddrE1 = Unable;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = 0;  //输出要写入的寄存器号
			Im = 0;  //输出指令中的立即数	
			CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Zero_ALU;  //ALU 控制
			CRegWE = Unable;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
	}
	
	private void set() {
		next.setRegAddr1_Ctrl(RegAddr1);
		next.setRegAddrE1_Ctrl(RegAddrE1);
		next.setRegAddr2_Ctrl(RegAddr2);
		next.setRegAddrE2_Ctrl(RegAddrE2);
		next.setRegWAddr_Ctrl(RegWAddr);
		next.setIm_Ctrl(Im);
		next.setCChoALU2_Ctrl(CChoALU2);
		next.setCChoRegWVal_Ctrl(CChoRegWVal);
		next.setCChoPCCtrl_Ctrl(CChoPCCtrl);
		next.setCALU_Ctrl(CALU);
		next.setCRegWE_Ctrl(CRegWE);
		next.setCMemWE_Ctrl(CMemWE);
		next.setStorePC_Ctrl(storePC);
		next.setNeedStop_Ctrl(needStop);
		next.setIslwsw_Ctrl(islwsw);
	}
	
	public static void main(String args[]) {
		System.out.println(Control.cutInt(0x00011020, 16, 20));
	}
}
