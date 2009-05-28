package org.kde9.view.editpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class EditPanel 
extends JPanel {
	JToggleButton edit;
	JRadioButton forward;
	JRadioButton backward;
	JButton button1;
	JButton button2;
	JEditorPane editPane;
	JScrollPane scroll;
	JTextArea line;
	
	public EditPanel() {
		setLayout(new BorderLayout());
		
		JPanel innerPanel = new JPanel(new BorderLayout());
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT);
		JPanel inPanel = new JPanel(flow);
		button1 = new JButton(new ImageIcon("./img/add2.png"));
		button1.setPreferredSize(new Dimension(12,12));
		button1.putClientProperty("Quaqua.Button.style", "toolBarTab");
		button2 = new JButton(new ImageIcon("./img/sub2.png"));
		button2.setPreferredSize(new Dimension(12,12));
		button2.putClientProperty("Quaqua.Button.style", "toolBarTab");
		edit = new JToggleButton("Edit");
		edit.putClientProperty("Quaqua.Button.style", "toolBarRollover");
		innerPanel.add("Center", edit);
		inPanel.add(button1);
		inPanel.add(button2);
		inPanel.setOpaque(true);
		inPanel.setBackground(Color.WHITE);
		innerPanel.add("North", inPanel);
		add("South", innerPanel);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		line = new JTextArea("aa");
		line.setEditable(false);
		line.setSelectionColor(Color.WHITE);
		editPane = new JEditorPane();
		editPane.setText("aa");
		centerPanel.add("West", line);
		centerPanel.add("Center", editPane);
		
		scroll = new JScrollPane(centerPanel);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add("Center", scroll);
		
		JPanel upPanel = new JPanel(new GridLayout(0, 2));
		forward = new JRadioButton("Forward");
		backward = new JRadioButton("Backward");
		upPanel.add(forward);
		upPanel.add(backward);
		add("North", upPanel);
		

		
		setBorder(new TitledBorder(""));
	}
}
