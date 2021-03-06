package org.kde9.view.editpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import org.kde9.compile.Compiler;
import org.kde9.cpu.FPGA;
import org.kde9.cpu.UnitPool;
import org.kde9.exceptions.DonotExist;
import org.kde9.util.Constants;
import org.kde9.view.Factory;

public class EditPanel 
extends JPanel 
implements ActionListener, KeyListener, MouseListener, Constants {
	JToggleButton edit;
	JRadioButton forward;
	JRadioButton backward;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JTextArea editPane;
	JScrollPane scroll;
	JTextArea line;
	JLabel circle;
	JLabel pc;
	
	int rowHeight;
	Compiler compiler;
	FPGA fpga;
	
	boolean editable = true;
	boolean forw = true;
	HashMap<Integer, Integer> errors;
	Vector<Integer> result;
	String text;
	boolean error = false;
	boolean binary = false;
	boolean saved = false;
	String filePathSaved = null;
	HashMap<Integer, Integer> pc2rownum;
	HashMap<Integer, Integer> rownum2pc;
	int[] pcs = new int[] {-1,-1,-1,-1,-1};
	
	int rowCount = 1;
	String[] items = {"IF", "ID", "EXE", "MEM", "WB"};
	
	public EditPanel() {
		compiler = new Compiler();
		fpga = UnitPool.getFpga();
		
		setLayout(new BorderLayout());
		
		JPanel innerPanel = new JPanel(new BorderLayout());
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT);
		flow.setHgap(3);
		flow.setVgap(2);
		JPanel inPanel = new JPanel(flow);
		button1 = new JButton(new ImageIcon(getClass().getResource("/img/add2.png")));
		button1.setPreferredSize(new Dimension(19,14));
		button1.putClientProperty("Quaqua.Button.style", "toolBarTab");
		button1.addActionListener(this);
		button2 = new JButton(new ImageIcon(getClass().getResource("/img/sub2.png")));
		button2.setPreferredSize(new Dimension(19,14));
		button2.putClientProperty("Quaqua.Button.style", "toolBarTab");
		button2.addActionListener(this);
		button3 = new JButton(new ImageIcon(getClass().getResource("/img/circle.png")));
		button3.setPreferredSize(new Dimension(19,14));
		button3.putClientProperty("Quaqua.Button.style", "toolBarTab");
		button3.addActionListener(this);
		button4 = new JButton(new ImageIcon(getClass().getResource("/img/one.png")));
		button4.setPreferredSize(new Dimension(19,14));
		button4.putClientProperty("Quaqua.Button.style", "toolBarTab");
		button4.addActionListener(this);
		button5 = new JButton(new ImageIcon(getClass().getResource("/img/stop.png")));
		button5.setPreferredSize(new Dimension(19,14));
		button5.putClientProperty("Quaqua.Button.style", "toolBarTab");
		button5.addActionListener(this);
		
		button1.setEnabled(false);
		button2.setEnabled(false);
		button3.setEnabled(false);
		button4.setEnabled(false);
		button5.setEnabled(false);
		
		edit = new JToggleButton("Compile");
		edit.addActionListener(this);
		edit.setSelected(true);
		edit.putClientProperty("Quaqua.Button.style", "toolBarRollover");
		innerPanel.add("Center", edit);
		inPanel.add(button1);
		inPanel.add(button2);
		//inPanel.add(new JLabel("                                        "));
		inPanel.add(button3);
		inPanel.add(button4);
		inPanel.add(button5);
		inPanel.add(new JLabel(" circle:"));
		circle = new JLabel("");
		inPanel.add(circle);
		inPanel.add(new JLabel(" ins:"));
		pc = new JLabel("");
		inPanel.add(pc);
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
		line.setPreferredSize(new Dimension(50, 200));
		editPane = new JTextArea() {			
			public void paint(Graphics g) {
				super.paint(g);
				if (!editable && !binary) {
					int w = getWidth();
					int h = getLineCount();
					int s = w/5;
					int c = getRowHeight();
					rowHeight = c;
					g.setColor(new Color(0, 200, 25, 80));
					g.setFont(new Font("", 0, 10));
					pcs = fpga.getPcs();
					for (int i = 0; i < 5; i++) {
						for(int j = 0; j < h; j++) {
							if(rownum2pc.get(j+1) != null) {
								g.drawRoundRect(i*s+1, j*c+1, s-2, c-2, 10, 15);
								g.drawString(items[i], i*s+6, j*c+c-2);
							}
						}						
//						System.err.println("current " + i + " " + pcs[i]);//////////////////////////////
//						System.err.println("pc2rownum " + pc2rownum);
						if(pcs[i] != -1) {
							Integer row = pc2rownum.get(pcs[i]+1);
							if(row != null)
								g.fillRoundRect(i*s+1, (row-1)*c+1, s-2, c-2, 10, 15);
//							if(i == 0)
//								scrollRectToVisible(new Rectangle(i*s+1, (row-1)*c+1, 0, 0));
						}
					}
				}
				if(error) {
					int c = getRowHeight();
					g.setColor(new Color(255, 0, 0, 100));
					for (int row : errors.keySet()) {
						switch(errors.get(row)) {
						case 0:
							g.drawImage(new ImageIcon(getClass().getResource("/img/e1.png")).getImage(), 
									120, row*c-c, null);
							break;
						case 1:
							g.drawImage(new ImageIcon(getClass().getResource("/img/e2.png")).getImage(), 
									120, row*c-c, null);
							break;
						case 2:
							g.drawImage(new ImageIcon(getClass().getResource("/img/e3.png")).getImage(), 
									120, row*c-c, null);
							break;
						case 3:
							g.drawImage(new ImageIcon(getClass().getResource("/img/e4.png")).getImage(), 
									120, row*c-c, null);
							break;
						case 4:
							g.drawImage(new ImageIcon(getClass().getResource("/img/e5.png")).getImage(), 
									120, row*c-c, null);
							break;
						default:
							break;
						}
					}
				}
			}
		};
		editPane.addKeyListener(this);
		editPane.setDisabledTextColor(Color.BLACK);
		editPane.addMouseListener(this);
		centerPanel.add("West", line);
		centerPanel.add("Center", editPane);
		
		scroll = new JScrollPane(centerPanel);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add("Center", scroll);
		
		JPanel upPanel = new JPanel(new GridLayout(0, 2));
		forward = new JRadioButton("Forward");
		forward.addActionListener(this);
		forward.setSelected(true);
		backward = new JRadioButton("Backward");
		backward.addActionListener(this);
		upPanel.add(forward);
		upPanel.add(backward);
//		add("North", upPanel);
		
		setPreferredSize(new Dimension(320, 200));
		TitledBorder title = new TitledBorder("edit area");
		title.setTitleFont(new Font("", 0, 10));
		title.setTitleJustification(TitledBorder.CENTER);
		setBorder(title);
	}

	public HashMap<Integer, Integer> getPc2rownum() {
		return pc2rownum;
	}
	
	public HashMap<Integer, Integer> getRownum2pc() {
		return rownum2pc;
	}
	
	public JToggleButton getEdit() {
		return edit;
	}
	
	public void setCircle(int c) {
		circle.setText(String.valueOf(c));
	}
	
	public void setPc(int p) {
		pc.setText(String.valueOf(p));
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == edit) {
			if(edit.isSelected()) {
				fpga.stop();
				if(binary) {
					editPane.setText(text);
					binary = false;
				}
				editable = true;
				editPane.setEnabled(true);
				edit.setText("Compile");
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(false);
				button5.setEnabled(false);
				circle.setText("0");
				pc.setText("0");
				Factory.getReg().clear();
			} else {
				try {
//					fpga.stop();
					if (compiler.compile(editPane.getText())) {
						try {
							UnitPool.getMemory().clearMem(DATA);
							UnitPool.getInsCache().clear();
							UnitPool.getDataCache().clear();
						} catch (DonotExist e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						editable = false;
						editPane.setEnabled(false);
						edit.setText("Edit / Reset");
						button1.setEnabled(true);
						button2.setEnabled(true);
						button3.setEnabled(true);
						button4.setEnabled(true);
						button5.setEnabled(true);
						result = compiler.getRet();
						pc2rownum = compiler.getPc2rownum();
						rownum2pc = compiler.getRownum2pc();
						int i = 0;
						for (int ins : result) {
							try {
								UnitPool.getMemory().write(DATA, i, ins, false);		
							} catch (DonotExist e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							i++;
						}
						fpga.run(0, true);
						circle.setText("0");
						pc.setText("0");
						updateLine();
						//Factory.getMem().update();
					} else {
						error = true;
						edit.setSelected(true);
						errors = compiler.getErrRows();
						editPane.updateUI();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if(e.getSource() == forward) {
			forw = true;
			forward.setSelected(true);
			backward.setSelected(false);
		} else if(e.getSource() == backward) {
			forw = false;
			forward.setSelected(false);
			backward.setSelected(true);
		} else if(e.getSource() == button1) {
			if(binary) {
				editPane.setText(text);
				binary = false;
			}
		} else if(e.getSource() == button2) {
			if(!binary) {
				text = editPane.getText();
				String temp = "";
				for(int ins : result) {
					String i = Integer.toBinaryString(ins);
					while(i.length() < 32)
						i = "0" + i;
					temp += i;
					temp += NEWLINE;
				}
				editPane.setText(temp);
				binary = true;
			}
		} else if(e.getSource() == button3) {
			fpga.run(-1);
		} else if(e.getSource() == button4) {
			fpga.run(1);
			try {
				editPane.scrollRectToVisible(new Rectangle(
						0, rowHeight*pc2rownum.get(pcs[0]+1), 0, 0));
			} catch(NullPointerException ex) {}
//			editPane.repaint();
//			Factory.getMem().update();
		} else if(e.getSource() == button5) {
			fpga.stop();
		}
	}

	public void updateLine() {
		int row = editPane.getLineCount();
		int space = line.getColumns();
		String str = "";
		for (int i = 0; i < row; i++) {
			str += (i + 1);
			if(rownum2pc != null && rownum2pc.get(i+1) != null)
				str += "(" + rownum2pc.get(i+1) + ")";
			str += NEWLINE;
		}
		line.setText(str);
	}
	
	public void update() {
		editPane.repaint();
		if(fpga.getCount() > 0)
			circle.setText(String.valueOf(fpga.getCount()));
		else
			circle.setText("0");
		pc.setText(String.valueOf(fpga.getInsCount()));
	}
	
	public void keyPressed(KeyEvent e) {
		error = false;
		saved = false;
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
		saved = false;
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

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (!editable && !binary) {
			int w = editPane.getWidth() / 5;
			int h = rowHeight;
			Integer pc = rownum2pc.get(e.getY() / h + 1);
			int loc = e.getX() / w;
//			System.out.println(rownum2pc);
			if (pc != null && loc < 5) {
//				System.out.println(pc + "+++++++" + loc);
				if(forw) {
					fpga.run(pc - 1, loc);
				} else {
					UnitPool.getInsCache().clear();
					UnitPool.getDataCache().clear();
					Integer c = fpga.getPc2circle().get(pc-1);
					if(c != null && fpga.getWhichPart().get(pc-1) >= loc) {
						fpga.run(0, true);
						fpga.run(c);
						fpga.run(pc - 1, loc);
					}
				}
			}
		}
	}
	
	public JTextArea getEditPane() {
		return editPane;
	}
	public JTextArea getLine() {
		return line;
	}
	public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	public String getFilePathSaved() {
		return filePathSaved;
	}
	public void setFilePathSaved(String filePathSaved) {
		this.filePathSaved = filePathSaved;
	}
	/**
	 * @return the button1
	 */
	public JButton getButton1() {
		return button1;
	}

	/**
	 * @return the button2
	 */
	public JButton getButton2() {
		return button2;
	}

	/**
	 * @return the button3
	 */
	public JButton getButton3() {
		return button3;
	}

	/**
	 * @return the button4
	 */
	public JButton getButton4() {
		return button4;
	}

	/**
	 * @return the button5
	 */
	public JButton getButton5() {
		return button5;
	}
	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}
}
