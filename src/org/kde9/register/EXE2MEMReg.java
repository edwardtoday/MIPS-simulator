package org.kde9.register;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class EXE2MEMReg {
	Signals signal;
	Signals next;
	
	int ALUValIn;
	int ALUValOut;
	
	int RegValIn2;  //����ĵڶ����Ĵ���ֵ
	int RegValOut2;  //����ĵڶ����Ĵ���ֵ
	
	boolean TIn; 
	boolean TOut; 
	
	int RegWAddrIn;  //�����Ҫд��ļĴ�����ַ
	int RegWAddrOut;  //�����Ҫд��ļĴ�����ַ
	
	boolean MemWEIn;  //�����Memдʹ��
	boolean MemWEOut;  //�����Memдʹ��
	
	boolean RegWEIn;  //�����Regдʹ��
	boolean RegWEOut;  //�����Regдʹ��
	
	int CChoRegWValIn;  //����ļĴ�������д��ѡ����������
	int CChoRegWValOut;
	
	boolean islwswIn;
	boolean islwswOut;
	
	boolean reset;  //�첽???��?1'Ϊ����
	boolean nreset;  //ͬ����?���ʱ���������?Ϊ'1'??����
	
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
