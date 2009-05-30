package org.kde9.control;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.util.Constants;

public class Control 
implements Constants {
	Signals signal;
	Signals next;
	
	// in
	int Ins;  //����ָ�����
	
	// out
	int RegAddr1;  //�����һ���Ĵ�����
	boolean RegAddrE1;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
	int RegAddr2;  //����ڶ����Ĵ�����
	boolean RegAddrE2;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
	int RegWAddr;  //���Ҫд��ļĴ�����
	int Im;  //���ָ���е�������	
	boolean CChoALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
	int CChoRegWVal;  //�Ĵ�����д���� ����ѡ����������
	int CChoPCCtrl;  //PC�ӷ� ����ѡ����??����
	int CALU;  //ALU ����
	boolean CRegWE;  //��??����дʹ�� ���ƣ�'1'Ϊд
	boolean CMemWE;  //�ڴ�д???? ���ƣ�'1'Ϊд
	boolean storePC;  //�Ƿ���Ҫ�洢PC
	boolean needStop;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
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
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Add_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
		} else {
			RegAddr1 = 0;  //�����һ���Ĵ�����
			RegAddrE1 = Unable;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = 0;  //���Ҫд��ļĴ�����
			Im = 0;  //���ָ���е�������	
			CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Zero_ALU;  //ALU ����
			CRegWE = Unable;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
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
