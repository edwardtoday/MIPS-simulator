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
		TitledBorder t1 = new TitledBorder("数据冲突情况统计");
		t1.setTitleJustification(TitledBorder.CENTER);
		JPanel p1 = new JPanel(new BorderLayout());
		p1.setBorder(t1);
		p1.add("North", f1);
		p1.add("Center", new JScrollPane(a1));
						
		f2 = new JLabel(" ");
		a2 = new JTextArea();
		a2.setEditable(false);
		TitledBorder t2 = new TitledBorder("流水暂停情况统计");
		t2.setTitleJustification(TitledBorder.CENTER);
		JPanel p2 = new JPanel(new BorderLayout());
		p2.setBorder(t2);
		p2.add("North", f2);
		p2.add("Center", new JScrollPane(a2));
		
		f3 = new JLabel(" ");
		a3 = new JTextArea();
		a3.setEditable(false);
		TitledBorder t3 = new TitledBorder("Cache缺失情况统计");
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
				"第" + (c.get(1)+1) + "条指令所需寄存器" + c.get(3) + "的值过期" + NEWLINE +
				"从MEM阶段[第" + (c.get(2)+1) + "条指令]转发";
		else
			temp = "circle = " + (c.get(4)+1) + "\tpc = " + c.get(5) + NEWLINE + 
				"第" + (c.get(1)+1) + "条指令所需寄存器" + c.get(3) + "的值过期" + NEWLINE +
				"从WB阶段[第" + (c.get(2)+1) + "条指令]转发";
		return temp;
	}
	
	private String makePause(Vector<Integer> c) {
		String temp = "";
		if(c.get(2) == 0) {
			temp = "circle = " + (c.get(0)+1) + "\tpc = " + c.get(1) + NEWLINE + 
				"第" + (c.get(3)+1) + "条指令为内存读写指令" + NEWLINE +
				"为避免可能的数据冲突暂停2个周期";
			if(c.size() == 5)
				temp += NEWLINE + "(注意：此暂停与上一条暂停重叠，将覆盖上一条暂停)";
		}
		else if(c.get(2) == 1)
			temp = "circle = " + (c.get(0)+1) + "\tpc = " + c.get(1) + NEWLINE + 
				"第" + (c.get(3)+1) + "条指令需读取内存数据" + NEWLINE +
				"因结构冲突暂停1个周期";
		else if(c.get(2) == 3)
			temp = "circle = " + (c.get(0)+1) + "\tpc = " + c.get(1) + NEWLINE + 
				"第" + (c.get(3)+1) + "条指令，数据Cache发生缺失" + NEWLINE +
				"从内存中读取数据，暂停1个周期";
		else
			temp = "circle = " + (c.get(0)+1) + "\tpc = " + c.get(1) + NEWLINE + 
				"第" + (c.get(3)+1) + "条指令，指令Cache发生缺失" + NEWLINE +
				"从内存中读取指令，暂停1个周期";
		return temp;
	}
	
	synchronized public void update() {
		f1.setText("数据冲突总数  " + fpga.getConflictSum() + " [显示前500个]");
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
		
		f2.setText("流水线暂停周期数  " + fpga.getPauseSum() + " [显示前500个]");
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
		f3.setText("Cache缺失总数  " + cacheSum + " [显示前500个]");
		a3.setText(tempx);
	}
}
