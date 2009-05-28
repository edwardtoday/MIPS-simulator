package org.kde9.view.editpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

import org.kde9.util.Constants;

public class EditPanel 
extends JPanel 
implements ActionListener, KeyListener, Constants {
	JToggleButton edit;
	JRadioButton forward;
	JRadioButton backward;
	JButton button1;
	JButton button2;
	JTextArea editPane;
	JScrollPane scroll;
	JTextArea line;
	
	boolean editable = true;
	
	int rowCount = 1;
	String[] items = {"IF", "ID", "EXE", "MEM", "WB"};
	
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
		edit.addActionListener(this);
		edit.setSelected(true);
		edit.putClientProperty("Quaqua.Button.style", "toolBarRollover");
		innerPanel.add("Center", edit);
		inPanel.add(button1);
		inPanel.add(button2);
		inPanel.setOpaque(true);
		inPanel.setBackground(Color.WHITE);
		innerPanel.add("North", inPanel);
		add("South", innerPanel);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		line = new JTextArea("1");
		//line.setEditable(false);
		line.setSelectionColor(Color.WHITE);
		line.setEnabled(false);
		line.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, 
				new Color(0, 0, 255, 20)));
		line.setPreferredSize(new Dimension(30, 200));
		editPane = new JTextArea() {
			public void paint(Graphics g) {
				super.paint(g);
				if (!editable) {
					int w = getWidth();
					int h = getLineCount();
					int s = w/5;
					int c = getRowHeight();
					g.setColor(new Color(0, 0, 255, 40));
					for (int i = 0; i < 5; i++)
						for(int j = 0; j < h; j++) {
							g.drawRoundRect(i*s+1, j*c+1, w/5-2, c-2, 10, 15);
							g.drawString(items[i], i*s+7, j*c+15);
						}
				}
			}
		};
		editPane.addKeyListener(this);
		editPane.setDisabledTextColor(Color.BLACK);
		centerPanel.add("West", line);
		centerPanel.add("Center", editPane);
		
		scroll = new JScrollPane(centerPanel);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add("Center", scroll);
		
		JPanel upPanel = new JPanel(new GridLayout(0, 2));
		forward = new JRadioButton("Forward");
		backward = new JRadioButton("Backward");
		upPanel.add(forward);
		upPanel.add(backward);
		add("North", upPanel);
		
		setPreferredSize(new Dimension(300, 200));
		TitledBorder title = new TitledBorder("edit area");
		title.setTitleFont(new Font("", 0, 10));
		title.setTitleJustification(TitledBorder.CENTER);
		setBorder(title);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == edit) {
			if(edit.isSelected()) {
				editable = true;
				editPane.setEnabled(true);
			} else {
				editable = false;
				editPane.setEnabled(false);
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER || 
				e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			System.out.println(editPane.getLineCount());
			int row = editPane.getLineCount();
			if (row != rowCount) {
				rowCount = row;
				String str = "";
				for (int i = 0; i < row; i++) {
					str += (i + 1);
					str += NEWLINE;
				}
				line.setText(str);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER || 
				e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			System.out.println(editPane.getLineCount());
			int row = editPane.getLineCount();
			int space = line.getColumns();
			if (row != rowCount) {
				rowCount = row;
				String str = "";
				for (int i = 0; i < row; i++) {
					str += (i + 1);
					str += NEWLINE;
				}
				line.setText(str);
			}
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
