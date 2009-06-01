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
	
	boolean hold;
	
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
		pcin = signal.getPCOut_EXE();
		ALUValIn = signal.getALUValOut_EXE();
		MemValIn = signal.getIns_Mem();
		TIn = signal.isTOut_EXE();
		RegWAddrIn = signal.getRegWAddrOut_EXE();
		RegWEIn = signal.isRegWEOut_EXE();
		CChoRegWValIn = signal.getCChoRegWValOut_EXE();
		reset = r;
		nreset = false;
		hold = signal.isHoldEXE_Stop();
	}
	
	private void run() {
		if(reset) {
			pcout = -1;
			ALUValOut = 0;
			MemValOut = 0;
			TOut = false;
			RegWAddrOut = 0;
			RegWEOut = false;
			CChoRegWValOut = 0;
		} else {
			if(!hold)
				pcout = pcin;
			else
				pcout = -1;
			ALUValOut = ALUValIn;
			MemValOut = MemValIn;
			TOut = TIn;
			RegWAddrOut = RegWAddrIn;
			RegWEOut = RegWEIn;
			CChoRegWValOut =CChoRegWValIn;
		}
	}
	
	private void set() {
		next.setPCOut_MEM(pcout);
		next.setALUValOut_MEM(ALUValOut);
		next.setMemValOut_MEM(MemValOut);
		next.setTOut_MEM(TOut);
		next.setRegWAddrOut_MEM(RegWAddrOut);
		next.setRegWEOut_MEM(RegWEOut);
		next.setCChoRegWValOut_MEM(CChoRegWValOut);
	}
	
	public static void main(String args[]) {

	}
}
