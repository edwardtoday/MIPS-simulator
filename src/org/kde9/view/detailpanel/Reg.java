package org.kde9.view.detailpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.kde9.cpu.UnitPool;
import org.kde9.exceptions.DonotExist;
import org.kde9.register.Registers;
import org.kde9.view.editpanel.EditPanel;

import ch.randelshofer.quaqua.JBrowser;

public class Reg 
extends JPanel 
implements KeyListener, ActionListener {
	Vector<JLabel> labels;
	Vector<JTextField> values;
	Vector<JTextField> his1;
	Vector<JTextField> his2;
	
	boolean dec = false;
	
	JButton showdec;
	JButton showhex;
	
	int[] his = new int[32];
	
	public Reg() {
		JPanel p1 = new JPanel(new GridLayout(16,2));
		p1.setPreferredSize(new Dimension(150, 300));
		JPanel p2 = new JPanel(new GridLayout(16,2));
		p2.setPreferredSize(new Dimension(150, 300));
		JPanel p3 = new JPanel(new GridLayout(16,2));
		p3.setPreferredSize(new Dimension(150, 300));
		JPanel p4 = new JPanel(new GridLayout(16,2));
		p4.setPreferredSize(new Dimension(150, 300));
		his1 = new Vector<JTextField>();
		his2 = new Vector<JTextField>();
		labels = new Vector<JLabel>();
		values = new Vector<JTextField>();
		
		for(int i = 0; i < 32; i++) {
			JLabel l = new JLabel("第" + i + "号寄存器");
			l.setFont(new Font("", 0, 12));
			labels.add(l);
			JTextField t = new JTextField();
			if(i == 31)
				t.setEnabled(false);
			else
				t.addKeyListener(this);
			values.add(t);
			JTextField tt = new JTextField();
			tt.setEnabled(false);
			tt.setBackground(new Color(245, 245, 245));
			his1.add(tt);
			JTextField ttt = new JTextField();
			ttt.setBackground(new Color(245, 245, 245));
			ttt.setEnabled(false);
			his2.add(ttt);
			if(i < 16) {
				p1.add(l);
				p1.add(t);
				p3.add(tt);
				p3.add(ttt);
			} else {
				p2.add(l);
				p2.add(t);
				p4.add(tt);
				p4.add(ttt);
			}
		}
		
		JPanel pp1 = new JPanel(new BorderLayout());
		TitledBorder tt1 = new TitledBorder("当前寄存器值");
		tt1.setTitleJustification(TitledBorder.CENTER);
		p1.setBorder(tt1);
		pp1.add("Center", p1);
		TitledBorder tt3 = new TitledBorder("寄存器值历史值");
		tt3.setTitleJustification(TitledBorder.CENTER);
		p3.setBorder(tt3);
		pp1.add("East", p3);
		TitledBorder t1 = new TitledBorder("0-15号寄存器");
		t1.setTitleJustification(TitledBorder.CENTER);
		pp1.setBorder(t1);
		
		JPanel pp2 = new JPanel(new BorderLayout());
		TitledBorder tt2 = new TitledBorder("当前寄存器值");
		tt2.setTitleJustification(TitledBorder.CENTER);
		p2.setBorder(tt2);
		pp2.add("Center", p2);
		TitledBorder tt4 = new TitledBorder("寄存器值历史值");
		tt4.setTitleJustification(TitledBorder.CENTER);
		p4.setBorder(tt4);
		pp2.add("East", p4);
		TitledBorder t2 = new TitledBorder("16-32号寄存器");
		t2.setTitleJustification(TitledBorder.CENTER);
		pp2.setBorder(t2);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		panel.add(pp1);
		panel.add(pp2);
		
		JPanel down = new JPanel();
		showdec = new JButton(" 十进制显示  ");
		showdec.addActionListener(this);
		showhex = new JButton("十六进制显示");
		showhex.addActionListener(this);
		down.add(showdec);
		down.add(showhex);
		((FlowLayout)down.getLayout()).setVgap(0);
		
		setLayout(new BorderLayout());
		add("Center", panel);
		add("South", down);
	}
	
	public void clear() {
		for(int i = 0; i < 32; i++) {
			values.get(i).setText("");
			his1.get(i).setText("");
			his2.get(i).setText("");
		}
	}
	
	public void update() {
		Registers r = UnitPool.getRegisters();
		for (int i = 0; i < 32; i++) {
			values.get(i).setBackground(Color.WHITE);
			if(r.getRegisters().get(String.valueOf(i)) != his[i]) {
				his[i] = r.getRegisters().get(String.valueOf(i));
				values.get(i).setBackground(new Color(200, 0, 0, 20));
			}
			if (dec) {
				values.get(i).setText(
						String.valueOf(r.getRegisters().get(
								String.valueOf(i))));
				his1.get(i).setText(
						String.valueOf(r.getHis1().get(
								String.valueOf(i))));
				his2.get(i).setText(
						String.valueOf(r.getHis1().get(
								String.valueOf(i))));
			} else {
				values.get(i).setText(
						String.valueOf("0x" + Integer.toHexString(r.getRegisters().get(
								String.valueOf(i)))));
				his1.get(i).setText(
						String.valueOf("0x" + Integer.toHexString(r.getHis1().get(
								String.valueOf(i)))));
				his2.get(i).setText(
						String.valueOf("0x" + Integer.toHexString(r.getHis2().get(
								String.valueOf(i)))));
			}
		}
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int i = 0;
		for(; i < 32; i++) {
			if(e.getSource() == values.get(i))
				break;
		}
		Registers r = UnitPool.getRegisters();
		String str = values.get(i).getText();
		int val;
		try {
			if (str.startsWith("0x")) {
				val = Integer.valueOf(str.substring(2, str.length()), 16);
			} else {
				val = Integer.valueOf(str);
			}
			System.out.println(i + " " + val);
			r.getRegisters().put(String.valueOf(i), val);
		} catch (NumberFormatException ex) {}
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == showdec) {
			dec = true;
		} else {
			dec = false;
		}
		update();
	}
}
