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
//		int aa = (int) Math.pow(2, a);
//		int bb = (int) Math.pow(2, b+1);
//		int cc = (int) Math.pow(2, b+1-a);
		return (ins >>> a) - ((ins >>> b)/2 << (b+1-a));
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
			// add
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
			// addu
			else if(cutInt(Ins, 0, 10) == 0x21) {
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
			// sub
			else if(cutInt(Ins, 0, 10) == 0x22) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Sub_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// subu
			else if(cutInt(Ins, 0, 10) == 0x23) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Sub_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// sll
			else if(cutInt(Ins, 0, 10) == 0x4) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Sll_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// srl
			else if(cutInt(Ins, 0, 10) == 0x6) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Srl_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// sra
			else if(cutInt(Ins, 0, 10) == 0x7) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Sra_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// and
			else if(cutInt(Ins, 0, 10) == 0x24) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = And_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// or
			else if(cutInt(Ins, 0, 10) == 0x25) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Or_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// xor
			else if(cutInt(Ins, 0, 10) == 0x26) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Xor_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// seq
			else if(cutInt(Ins, 0, 10) == 0x28) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Seq_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// sne
			else if(cutInt(Ins, 0, 10) == 0x29) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Sne_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// slt
			else if(cutInt(Ins, 0, 10) == 0x2a) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Slt_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// sgt
			else if(cutInt(Ins, 0, 10) == 0x2b) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Sgt_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// sle
			else if(cutInt(Ins, 0, 10) == 0x2c) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Sle_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			} 
			// nop
			else {
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
		} else if(cutInt(Ins, 26, 31) == 1) {
			// mult
			if(cutInt(Ins, 0, 10) == 0xe) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Mult_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// div
			else if(cutInt(Ins, 0, 10) == 0xf) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Div_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// multu
			else if(cutInt(Ins, 0, 10) == 0x16) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Mult_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// divu
			else if(cutInt(Ins, 0, 10) == 0x17) {
				RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
				RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
				RegAddr2 = cutInt(Ins, 16, 20);  //输出第二个寄存器号
				RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
				RegWAddr = cutInt(Ins, 11, 15);  //输出要写入的寄存器号
				Im = 0;  //输出指令中的立即数	
				CChoALU2 = Reg_ALU2;  //ALU第二输入 数据选择器的控制码
				CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
				CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
				CALU = Div_ALU;  //ALU 控制
				CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
				CMemWE = Unable;  //内存写???? 控制，'1'为写
				storePC = No;  //是否需要存储PC
				needStop = No;  //是否需要暂停，'1'为是
				islwsw = No;
			}
			// nop
			else {
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
		// addi
		else if(cutInt(Ins, 26, 31) == 0x8) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Add_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// addui
		else if(cutInt(Ins, 26, 31) == 0x9) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Add_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// subi
		else if(cutInt(Ins, 26, 31) == 0xa) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Sub_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// subui
		else if(cutInt(Ins, 26, 31) == 0xb) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Sub_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// andi
		else if(cutInt(Ins, 26, 31) == 0xc) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = And_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// ori
		else if(cutInt(Ins, 26, 31) == 0xd) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Or_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// xori
		else if(cutInt(Ins, 26, 31) == 0xe) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Xor_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// slli
		else if(cutInt(Ins, 26, 31) == 0x14) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp & 0x1f;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Sll_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// srli
		else if(cutInt(Ins, 26, 31) == 0x16) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp & 0x1f;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Srl_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// srai
		else if(cutInt(Ins, 26, 31) == 0x17) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp & 0x1f;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Sra_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// seqi
		else if(cutInt(Ins, 26, 31) == 0x18) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Seq_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// snei
		else if(cutInt(Ins, 26, 31) == 0x19) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Sne_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// slti
		else if(cutInt(Ins, 26, 31) == 0x1a) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Slt_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// sgti
		else if(cutInt(Ins, 26, 31) == 0x1b) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Sgt_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// slei
		else if(cutInt(Ins, 26, 31) == 0x1c) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Sle_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// j
		else if(cutInt(Ins, 26, 31) == 0x2) {
			RegAddr1 = 0;  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = 0;  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 25);
			Im = temp > 0x1ffffff ? temp|0xfe000000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = E0PCIm1PC;  //PC加法 数据选择器??制码
			CALU = B_ALU;  //ALU 控制
			CRegWE = Unable;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// beqz
		else if(cutInt(Ins, 26, 31) == 0x4) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = 0;  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 25);
			Im = temp > 0x1ffffff ? temp|0xfe000000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = E0PCIm1PC;  //PC加法 数据选择器??制码
			CALU = B_ALU;  //ALU 控制
			CRegWE = Unable;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// bnez
		else if(cutInt(Ins, 26, 31) == 0x5) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = 0;  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 25);
			Im = temp > 0x1ffffff ? temp|0xfe000000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = E0PC1PCIm;  //PC加法 数据选择器??制码
			CALU = B_ALU;  //ALU 控制
			CRegWE = Unable;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = No;  //是否需要暂停，'1'为是
			islwsw = No;
		}
		// lw
		else if(cutInt(Ins, 26, 31) == 0x23) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = 0;  //输出第二个寄存器号
			RegAddrE2 = Unable;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = Mem_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Add_ALU;  //ALU 控制
			CRegWE = Able;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Unable;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = Yes;  //是否需要暂停，'1'为是
			islwsw = Yes;
		}
		// sw
		else if(cutInt(Ins, 26, 31) == 0x2b) {
			RegAddr1 = cutInt(Ins, 21, 25);  //输出第一个寄存器号
			RegAddrE1 = Able;  //输出第一个寄存器号是否有效，'1'为有效
			RegAddr2 = cutInt(Ins, 16, 20);;  //输出第二个寄存器号
			RegAddrE2 = Able;  //输??第二个寄存器号是否有效，'1'为有??
			RegWAddr = cutInt(Ins, 16, 20);  //输出要写入的寄存器号
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //输出指令中的立即数	
			CChoALU2 = Im_ALU2;  //ALU第二输入 数据选择器的控制码
			CChoRegWVal = ALU_RegWVal;  //寄存器的写数据 数据选择器控制码
			CChoPCCtrl = PC_PC;  //PC加法 数据选择器??制码
			CALU = Add_ALU;  //ALU 控制
			CRegWE = Unable;  //寄??器堆写使能 控制，'1'为写
			CMemWE = Able;  //内存写???? 控制，'1'为写
			storePC = No;  //是否需要存储PC
			needStop = Yes;  //是否需要暂停，'1'为是
			islwsw = Yes;
		}
		// nop || halt
		else {
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
		System.out.println(Integer.toBinaryString(Control.cutInt(-1400635391, 0, 18)));
		// 10101100100001000000000000000001
	}
}
