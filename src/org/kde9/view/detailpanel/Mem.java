package org.kde9.view.detailpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.kde9.cpu.UnitPool;
import org.kde9.memory.Memory;

public class Mem 
extends JPanel {
	Memory mem = UnitPool.getMemory();
	
	JButton clear;
	JButton save;
	JComboBox restore;
	JLabel restoreLabel;
	JRadioButton cache;
	JTable table;
	JLabel inputLabel;
	JTextField input;
	
	public Mem() {
		JPanel up = new JPanel();
		inputLabel = new JLabel("查看内存地址：");
		input = new JTextField();
		input.setPreferredSize(new Dimension(100, 30));
		cache = new JRadioButton("同时修改cache");
		((FlowLayout)up.getLayout()).setAlignment(FlowLayout.LEFT);
		up.add(inputLabel);
		up.add(input);
		up.add(new JLabel());
		up.add(cache);
		
		table = new JTable();
		table.setBorder(BorderFactory.createTitledBorder(""));
		
		JPanel down = new JPanel();
		clear = new JButton("Clear Memory");
		save = new JButton("Save Memory");
		restoreLabel = new JLabel("Restore Memory to");
		restore = new JComboBox();
		restore.setPreferredSize(new Dimension(100, 30));
		down.add(clear);
		down.add(save);
		down.add(restoreLabel);
		down.add(restore);
		
		setLayout(new BorderLayout());
		add("North", up);
		add("Center", table);
		add("South", down);
	}
}
