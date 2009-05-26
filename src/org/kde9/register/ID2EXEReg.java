package org.kde9.register;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class ID2EXEReg {
	Signals signal;
	Signals next;
	
	int RegValIn1;  //����ĵ�һ���Ĵ���ֵ
	int RegValOut1;  //����ĵ�һ���Ĵ���ֵ
	
	int RegValIn2;  //����ĵڶ����Ĵ���ֵ
	int RegValOut2;  //����ĵڶ����Ĵ���ֵ
	
	int RegAddrIn1;  //����??��һ���Ĵ���??ַ
	int RegAddrOut1;  //����ĵ�һ���Ĵ�����ַ
	
	int RegAddrIn2;  //����ĵڶ����Ĵ�����ַ
	int RegAddrOut2;  //����ĵڶ����Ĵ�����ַ
	
	boolean RegEIn1;  //����ĵ�һ���Ĵ����Ƿ���Ч
	boolean RegEOut1;  //����ĵ�һ���Ĵ����Ƿ���Ч
	
	boolean RegEIn2;  //����ĵڶ����Ĵ����Ƿ���Ч
	boolean RegEOut2;  //����ĵڶ����Ĵ����Ƿ���Ч
	
	int ImIn;  //����??������
	int ImOut;  //�����������
	
	int RegWAddrIn;  //�����Ҫд��ļĴ�����ַ
	int RegWAddrOut;  //�����Ҫд��ļĴ�����ַ
	
	int ALUCtrlIn;  //�����ALU������
	int ALUCtrlOut;  //�����ALU������
	
	boolean MemWEIn;  //�����Memдʹ��
	boolean MemWEOut;  //�����Memдʹ��
	
	boolean RegWEIn;  //�����Regдʹ��
	boolean RegWEOut;  //�����Regдʹ��
	
	boolean CChoALUIn2;  //�����ALU�ڶ�����ѡ����������
	boolean CChoALUOut2;  //�����ALU�ڶ�����ѡ����������
	
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
			RegValOut1 = 0;
			RegValOut2 = 0;
			RegEOut1 = false;
			RegEOut2 = false;
			ImOut = 0;
			RegWAddrOut = 0;
			ALUCtrlOut = 0;
			MemWEOut = false;
			RegWEOut = false;
			CChoALUOut2 = false;
			CChoRegWValOut = 0;
			islwswOut = false;
		} else {
			RegValOut1 = RegValIn1;
			RegValOut2 = RegValIn2;
			RegEOut1 = RegEIn1;
			RegEOut2 = RegEIn2;
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
		next.setRegValOut1_ID(RegValOut1);
		next.setRegValOut2_ID(RegValOut2);
		next.setRegEOut1_ID(RegEOut1);
		next.setRegEOut2_ID(RegEOut2);
		next.setImOut_ID(ImOut);
		next.setRegWAddrOut_ID(RegWAddrOut);
		next.setALUCtrlOut_ID(ALUCtrlOut);
		next.setMemWEOut_ID(MemWEOut);
		next.setRegWEOut_ID(RegWEOut);
		next.setCChoALU2_Ctrl(CChoALUOut2);
		next.setCChoRegWValOut_ID(CChoRegWValOut);
		next.setIslwswOut_ID(islwswOut);
	}
	
	public static void main(String args[]) {

	}
}
