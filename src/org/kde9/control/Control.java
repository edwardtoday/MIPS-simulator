package org.kde9.control;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;

public class Control {
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
		Ins = signal.getInsOut_IF();
	}
	
	private void run() {
		
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
		System.out.println(Control.cutInt(0x8A1F, 0, 3));
	}
}
