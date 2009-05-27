package org.kde9.register;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class MEM2WBReg {
	Signals signal;
	Signals next;
	
	int ALUValIn;
	int ALUValOut;
	
	int MemValIn;
	int MemValOut;
	
	boolean TIn; 
	boolean TOut; 
	
	int RegWAddrIn;  //�����Ҫд��ļĴ�����ַ
	int RegWAddrOut;  //�����Ҫд��ļĴ�����ַ
	
	boolean RegWEIn;  //�����Regдʹ��
	boolean RegWEOut;  //�����Regдʹ��
	
	int CChoRegWValIn;  //����ļĴ�������д��ѡ����������
	int CChoRegWValOut;
	
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
		ALUValIn = signal.getALUValOut_EXE();
		TIn = signal.isTOut_EXE();
		RegWAddrIn = signal.getRegWAddrOut_EXE();
		RegWEIn = signal.isRegWEOut_EXE();
		CChoRegWValIn = signal.getCChoRegWValOut_EXE();
		reset = r;
		nreset = false;
	}
	
	private void run() {
		if(reset) {
			ALUValOut = 0;
			TOut = false;
			RegWAddrOut = 0;
			RegWEOut = false;
			CChoRegWValOut = 0;
		} else {
			ALUValOut = ALUValIn;
			TOut = TIn;
			RegWAddrOut = RegWAddrIn;
			RegWEOut = RegWEIn;
			CChoRegWValOut =CChoRegWValIn;
		}
	}
	
	private void set() {
		next.setALUValOut_MEM(ALUValOut);
		next.setTOut_MEM(TOut);
		next.setRegWAddrOut_MEM(RegWAddrOut);
		next.setRegWEOut_MEM(RegWEOut);
		next.setCChoRegWValOut_MEM(CChoRegWValOut);
	}
	
	public static void main(String args[]) {

	}
}
