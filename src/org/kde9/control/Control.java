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
			// addu
			else if(cutInt(Ins, 0, 10) == 0x21) {
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
			// sub
			else if(cutInt(Ins, 0, 10) == 0x22) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Sub_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// subu
			else if(cutInt(Ins, 0, 10) == 0x23) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Sub_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// sll
			else if(cutInt(Ins, 0, 10) == 0x4) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Sll_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// srl
			else if(cutInt(Ins, 0, 10) == 0x6) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Srl_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// sra
			else if(cutInt(Ins, 0, 10) == 0x7) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Sra_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// and
			else if(cutInt(Ins, 0, 10) == 0x24) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = And_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// or
			else if(cutInt(Ins, 0, 10) == 0x25) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Or_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// xor
			else if(cutInt(Ins, 0, 10) == 0x26) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Xor_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// seq
			else if(cutInt(Ins, 0, 10) == 0x28) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Seq_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// sne
			else if(cutInt(Ins, 0, 10) == 0x29) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Sne_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// slt
			else if(cutInt(Ins, 0, 10) == 0x2a) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Slt_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// sgt
			else if(cutInt(Ins, 0, 10) == 0x2b) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Sgt_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// sle
			else if(cutInt(Ins, 0, 10) == 0x2c) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Sle_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			} 
			// nop
			else {
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
		} else if(cutInt(Ins, 26, 31) == 1) {
			// mult
			if(cutInt(Ins, 0, 10) == 0xe) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Mult_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// div
			else if(cutInt(Ins, 0, 10) == 0xf) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Div_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// multu
			else if(cutInt(Ins, 0, 10) == 0x16) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Mult_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// divu
			else if(cutInt(Ins, 0, 10) == 0x17) {
				RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
				RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
				RegAddr2 = cutInt(Ins, 16, 20);  //����ڶ����Ĵ�����
				RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
				RegWAddr = cutInt(Ins, 11, 15);  //���Ҫд��ļĴ�����
				Im = 0;  //���ָ���е�������	
				CChoALU2 = Reg_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
				CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
				CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
				CALU = Div_ALU;  //ALU ����
				CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
				CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
				storePC = No;  //�Ƿ���Ҫ�洢PC
				needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
				islwsw = No;
			}
			// nop
			else {
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
		// addi
		else if(cutInt(Ins, 26, 31) == 0x8) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Add_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// addui
		else if(cutInt(Ins, 26, 31) == 0x9) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Add_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// subi
		else if(cutInt(Ins, 26, 31) == 0xa) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Sub_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// subui
		else if(cutInt(Ins, 26, 31) == 0xb) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Sub_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// andi
		else if(cutInt(Ins, 26, 31) == 0xc) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = And_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// ori
		else if(cutInt(Ins, 26, 31) == 0xd) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Or_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// xori
		else if(cutInt(Ins, 26, 31) == 0xe) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Xor_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// slli
		else if(cutInt(Ins, 26, 31) == 0x14) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp & 0x1f;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Sll_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// srli
		else if(cutInt(Ins, 26, 31) == 0x16) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp & 0x1f;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Srl_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// srai
		else if(cutInt(Ins, 26, 31) == 0x17) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp & 0x1f;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Sra_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// seqi
		else if(cutInt(Ins, 26, 31) == 0x18) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Seq_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// snei
		else if(cutInt(Ins, 26, 31) == 0x19) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Sne_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// slti
		else if(cutInt(Ins, 26, 31) == 0x1a) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Slt_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// sgti
		else if(cutInt(Ins, 26, 31) == 0x1b) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Sgt_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// slei
		else if(cutInt(Ins, 26, 31) == 0x1c) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Sle_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// j
		else if(cutInt(Ins, 26, 31) == 0x2) {
			RegAddr1 = 0;  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = 0;  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 25);
			Im = temp > 0x1ffffff ? temp|0xfe000000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = E0PCIm1PC;  //PC�ӷ� ����ѡ����??����
			CALU = B_ALU;  //ALU ����
			CRegWE = Unable;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// beqz
		else if(cutInt(Ins, 26, 31) == 0x4) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = 0;  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 25);
			Im = temp > 0x1ffffff ? temp|0xfe000000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = E0PCIm1PC;  //PC�ӷ� ����ѡ����??����
			CALU = B_ALU;  //ALU ����
			CRegWE = Unable;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// bnez
		else if(cutInt(Ins, 26, 31) == 0x5) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = 0;  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 25);
			Im = temp > 0x1ffffff ? temp|0xfe000000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = E0PC1PCIm;  //PC�ӷ� ����ѡ����??����
			CALU = B_ALU;  //ALU ����
			CRegWE = Unable;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = No;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = No;
		}
		// lw
		else if(cutInt(Ins, 26, 31) == 0x23) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = 0;  //����ڶ����Ĵ�����
			RegAddrE2 = Unable;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = Mem_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Add_ALU;  //ALU ����
			CRegWE = Able;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Unable;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = Yes;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = Yes;
		}
		// sw
		else if(cutInt(Ins, 26, 31) == 0x2b) {
			RegAddr1 = cutInt(Ins, 21, 25);  //�����һ���Ĵ�����
			RegAddrE1 = Able;  //�����һ���Ĵ������Ƿ���Ч��'1'Ϊ��Ч
			RegAddr2 = cutInt(Ins, 16, 20);;  //����ڶ����Ĵ�����
			RegAddrE2 = Able;  //��??�ڶ����Ĵ������Ƿ���Ч��'1'Ϊ��??
			RegWAddr = cutInt(Ins, 16, 20);  //���Ҫд��ļĴ�����
			int temp = cutInt(Ins, 0, 15);
			Im = temp > 0x7fff ? temp|0xffff0000 : temp;  //���ָ���е�������	
			CChoALU2 = Im_ALU2;  //ALU�ڶ����� ����ѡ�����Ŀ�����
			CChoRegWVal = ALU_RegWVal;  //�Ĵ�����д���� ����ѡ����������
			CChoPCCtrl = PC_PC;  //PC�ӷ� ����ѡ����??����
			CALU = Add_ALU;  //ALU ����
			CRegWE = Unable;  //��??����дʹ�� ���ƣ�'1'Ϊд
			CMemWE = Able;  //�ڴ�д???? ���ƣ�'1'Ϊд
			storePC = No;  //�Ƿ���Ҫ�洢PC
			needStop = Yes;  //�Ƿ���Ҫ��ͣ��'1'Ϊ��
			islwsw = Yes;
		}
		// nop || halt
		else {
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
		System.out.println(Integer.toBinaryString(Control.cutInt(-1400635391, 0, 18)));
		// 10101100100001000000000000000001
	}
}
