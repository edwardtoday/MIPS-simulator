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
	
	public String makeConflict(Vector<Integer> c) {
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
	
	public void update() {
		f1.setText("���ݳ�ͻ����  " + fpga.getConflictSum());
		String temp = "";
		for(Vector<Integer> c : fpga.getConflict()) {
			temp += makeConflict(c);
			temp += NEWLINE;
			temp += NEWLINE;
		}
//		System.out.println(temp);
		a1.setText(temp);
	}
}
