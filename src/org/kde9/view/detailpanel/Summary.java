package org.kde9.view.detailpanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.kde9.cpu.FPGA;
import org.kde9.cpu.UnitPool;
import org.kde9.util.Constants;

public class Summary 
extends JPanel 
implements Constants {
	JLabel f1;
	JLabel f2;
	JLabel f3;
	JTextArea a1;
	JTextArea a2;
	JTextArea a3;
	FPGA fpga;
	
	public Summary() {
		fpga = UnitPool.getFpga();
		
		f1 = new JLabel(" ");
		a1 = new JTextArea();
		a1.setEditable(false);
		TitledBorder t1 = new TitledBorder("���ݳ�ͻ���ͳ��");
		t1.setTitleJustification(TitledBorder.CENTER);
		JPanel p1 = new JPanel(new BorderLayout());
		p1.setBorder(t1);
		p1.add("North", f1);
		p1.add("Center", new JScrollPane(a1));
						
		f2 = new JLabel(" ");
		a2 = new JTextArea();
		a2.setEditable(false);
		TitledBorder t2 = new TitledBorder("��ˮ��ͣ���ͳ��");
		t2.setTitleJustification(TitledBorder.CENTER);
		JPanel p2 = new JPanel(new BorderLayout());
		p2.setBorder(t2);
		p2.add("North", f2);
		p2.add("Center", new JScrollPane(a2));
		
		f3 = new JLabel(" ");
		a3 = new JTextArea();
		a3.setEditable(false);
		TitledBorder t3 = new TitledBorder("Cacheȱʧ���ͳ��");
		t3.setTitleJustification(TitledBorder.CENTER);
		JPanel p3 = new JPanel(new BorderLayout());
		p3.setBorder(t3);
		p3.add("North", f3);
		p3.add("Center", new JScrollPane(a3));
		
		setLayout(new GridLayout(0,3));
		add(p1);
		add(p2);
		add(p3);
	}
	
	private String makeConflict(Vector<Integer> c) {
		String temp = "";
		if(c.get(0) == 2)
			temp = "circle = " + (c.get(4)+1) + "\tpc = " + c.get(5) + NEWLINE + 
				"��" + (c.get(1)+1) + "��ָ������Ĵ���" + c.get(3) + "��ֵ����" + NEWLINE +
				"��MEM�׶�[��" + (c.get(2)+1) + "��ָ��]ת��";
		else
			temp = "circle = " + (c.get(4)+1) + "\tpc = " + c.get(5) + NEWLINE + 
				"��" + (c.get(1)+1) + "��ָ������Ĵ���" + c.get(3) + "��ֵ����" + NEWLINE +
				"��WB�׶�[��" + (c.get(2)+1) + "��ָ��]ת��";
		return temp;
	}
	
	private String makePause(Vector<Integer> c) {
		String temp = "";
		if(c.get(2) == 0) {
			temp = "circle = " + (c.get(0)+1) + "\tpc = " + c.get(1) + NEWLINE + 
				"��" + (c.get(3)+1) + "��ָ��Ϊ�ڴ��дָ��" + NEWLINE +
				"Ϊ������ܵ����ݳ�ͻ��ͣ2������";
			if(c.size() == 5)
				temp += NEWLINE + "(ע�⣺����ͣ����һ����ͣ�ص�����������һ����ͣ)";
		}
		else if(c.get(2) == 1)
			temp = "circle = " + (c.get(0)+1) + "\tpc = " + c.get(1) + NEWLINE + 
				"��" + (c.get(3)+1) + "��ָ�����ȡ�ڴ�����" + NEWLINE +
				"��ṹ��ͻ��ͣ1������";
		else if(c.get(2) == 3)
			temp = "circle = " + (c.get(0)+1) + "\tpc = " + c.get(1) + NEWLINE + 
				"��" + (c.get(3)+1) + "��ָ�����Cache����ȱʧ" + NEWLINE +
				"���ڴ��ж�ȡ���ݣ���ͣ1������";
		else
			temp = "circle = " + (c.get(0)+1) + "\tpc = " + c.get(1) + NEWLINE + 
				"��" + (c.get(3)+1) + "��ָ�ָ��Cache����ȱʧ" + NEWLINE +
				"���ڴ��ж�ȡָ���ͣ1������";
		return temp;
	}
	
	synchronized public void update() {
		f1.setText("���ݳ�ͻ����  " + fpga.getConflictSum() + " [��ʾǰ500��]");
		String temp = "";
		int ii = 0;
		for(Vector<Integer> c : fpga.getConflict()) {
			if(ii > 500)
				break;
			temp += makeConflict(c);
			temp += NEWLINE;
			temp += NEWLINE;
			ii++;
		}
		a1.setText(temp);
		
		f2.setText("��ˮ����ͣ������  " + fpga.getPauseSum() + " [��ʾǰ500��]");
		temp = "";
		String tempx = "";
		int cacheSum = 0;
		ii = 0;
		for(Vector<Integer> c : fpga.getPause()) {
			if(ii > 500)
				break;
			String str = makePause(c);
			temp += str;
			temp += NEWLINE;
			temp += NEWLINE;
			ii++;
		}
		a2.setText(temp);
		ii = 0;
		for(Vector<Integer> c : fpga.getPause()) {
			if(ii > 500)
				break;
			if(c.get(2) == 2 || c.get(2) == 3) {
				String str = makePause(c);
				cacheSum++;
				tempx += str;
				tempx += NEWLINE;
				tempx += NEWLINE;
			}
			ii++;
		}
		f3.setText("Cacheȱʧ����  " + cacheSum + " [��ʾǰ500��]");
		a3.setText(tempx);
	}
}
