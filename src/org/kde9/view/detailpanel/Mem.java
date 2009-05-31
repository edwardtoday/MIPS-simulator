package org.kde9.view.detailpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.kde9.cpu.UnitPool;
import org.kde9.exceptions.DonotExist;
import org.kde9.memory.Cache;
import org.kde9.memory.Memory;
import org.kde9.util.Constants;
import org.kde9.util.Functions;

public class Mem 
extends JPanel 
implements ActionListener, KeyListener,
		MouseListener, TableModelListener, 
		Constants {
	Memory mem = UnitPool.getMemory();
	Cache insCache = UnitPool.getInsCache();
	Cache dataCache = UnitPool.getDataCache();
	
	JButton clear;
	JButton save;
	JComboBox restore;
	JLabel restoreLabel;
	JRadioButton cache;
	JRadioButton keepMem;
	JTable table;
	JScrollPane pane;
	JList read;
	JList write;
	JLabel detail;
	JLabel inputLabel;
	JTextField input;
	DefaultListModel d1;
	DefaultListModel d2;
	
	boolean connectCache = false;
	boolean keep = false;
	int addr = 0;
	int[] bound = {0, 127};
	int i = -1, j = -1;
	boolean valueChanfed = true;
	
	public Mem() {
		JPanel up = new JPanel();
		inputLabel = new JLabel("查看内存地址：");
		input = new JTextField();
		input.setPreferredSize(new Dimension(100, 30));
		input.addKeyListener(this);
		keepMem = new JRadioButton("自动跟踪");
		cache = new JRadioButton("同时修改cache");
		cache.addActionListener(this);
		//((FlowLayout)up.getLayout()).setAlignment(FlowLayout.LEFT);
		up.add(inputLabel);
		up.add(input);
		up.add(new JLabel());
		up.add(keepMem);
		up.add(cache);
		
		table = new JTable(0,9) {
			public void paint(Graphics g) {
				super.paint(g);
				if(i != -1 && j != -1) {
					int x = 0, xx = 0;
					g.setColor(new Color(11, 11, 77, 40));
					for(int index = 0; index < i; index++) {
						x += getColumnModel().getColumn(index).getWidth();
						xx = getColumnModel().getColumn(index).getWidth();
					}
					g.fillRect(x-1, getRowHeight()*j+1, 
							getColumnModel().getColumn(i).getWidth(), getRowHeight()-2);
//					g.drawImage(new ImageIcon("./img/warning.png").getImage(), 
//							x+xx-20, getRowHeight()*j+1, null);
					for(int j = 0; j < 32; j++) {
						int xxx = 0;
						for(int i = 0; i < 8; i++) {
							xxx += getColumnModel().getColumn(i).getWidth();
							xx = getColumnModel().getColumn(i).getWidth();
							int addr = bound[0] + j*8 + i - 1;
							try {
								if(insCache.getCache().get(addr) != null &&
										mem.read(DATA, addr, false) == insCache.getCache().get(addr))
									g.drawImage(new ImageIcon("./img/okI.png").getImage(), 
									xxx-15, getRowHeight()*j+1, null);
								else if(insCache.getCache().get(addr) != null &&
										mem.read(DATA, addr, false) != insCache.getCache().get(addr))
									g.drawImage(new ImageIcon("./img/warningI.png").getImage(), 
									xxx-15, getRowHeight()*j+1, null);
								else if(dataCache.getCache().get(addr) != null &&
										mem.read(DATA, addr, false) == dataCache.getCache().get(addr))
									g.drawImage(new ImageIcon("./img/okD.png").getImage(), 
									xxx-30, getRowHeight()*j+1, null);
								else if(dataCache.getCache().get(addr) != null &&
										mem.read(DATA, addr, false) != dataCache.getCache().get(addr))
									g.drawImage(new ImageIcon("./img/warningD.png").getImage(), 
									xxx-30, getRowHeight()*j+1, null);
							} catch (DonotExist e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		};
		table.setCellSelectionEnabled(true);
		table.setRowHeight(20);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setHeaderValue("地址");
		table.getColumnModel().getColumn(1).setHeaderValue("+0");
		table.getColumnModel().getColumn(2).setHeaderValue("+1");
		table.getColumnModel().getColumn(3).setHeaderValue("+2");
		table.getColumnModel().getColumn(4).setHeaderValue("+3");
		table.getColumnModel().getColumn(5).setHeaderValue("+4");
		table.getColumnModel().getColumn(6).setHeaderValue("+5");
		table.getColumnModel().getColumn(7).setHeaderValue("+6");
		table.getColumnModel().getColumn(8).setHeaderValue("+7");
//		table.getColumnModel().getColumn(9).setHeaderValue("+8");
//		table.getColumnModel().getColumn(10).setHeaderValue("+A");
//		table.getColumnModel().getColumn(11).setHeaderValue("+B");
//		table.getColumnModel().getColumn(12).setHeaderValue("+C");
//		table.getColumnModel().getColumn(13).setHeaderValue("+D");
//		table.getColumnModel().getColumn(14).setHeaderValue("+E");
//		table.getColumnModel().getColumn(15).setHeaderValue("+F");
		table.addMouseListener(this);
		table.getModel().addTableModelListener(this);
		pane = new JScrollPane(table);
		pane.setBorder(BorderFactory.createEtchedBorder());
		detail = new JLabel(" ");
		read = new JList();
		read.setPreferredSize(new Dimension(80, 100));
		read.setBorder(BorderFactory.createEtchedBorder());
		d1 = new DefaultListModel();
		read.setModel(d1);
		read.addMouseListener(this);
		write = new JList();
		write.setPreferredSize(new Dimension(80, 100));
		write.setBorder(BorderFactory.createEtchedBorder());
		write.addMouseListener(this);
		d2 = new DefaultListModel();
		write.setModel(d2);
		JPanel center = new JPanel(new BorderLayout());
		center.add("Center", pane);
		center.add("South", detail);
		center.add("West", read);
		center.add("East", write);
		
		JPanel down = new JPanel();
		clear = new JButton("Clear Memory");
		save = new JButton("Save Memory");
		restoreLabel = new JLabel("Restore Memory to");
		restore = new JComboBox();
		restore.setPreferredSize(new Dimension(120, 30));
		down.add(clear);
		down.add(save);
		down.add(restoreLabel);
		down.add(restore);
		
		setLayout(new BorderLayout());
		add("North", up);
		add("Center", center);
		add("South", down);
		
		updateList();
	}

	public void updateList() {
		d1.removeAllElements();
		Vector<Integer> r = UnitPool.getMemory().getRead();
		for(int addr : r)
			d1.add(0, Integer.toHexString(addr));
		d1.add(0, "最近读取地址");
		d2.removeAllElements();
		Vector<Integer> w = UnitPool.getMemory().getWrite();
		for(int addr : w)
			d2.add(0, Integer.toHexString(addr));
		d2.add(0, "最近写入地址");
	}
	
	public void update(int addr) {
		if(keep) {
			input.setText(String.valueOf(addr));
		}
		input.dispatchEvent(new KeyEvent(input, KeyEvent.KEY_RELEASED,
				System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'c'));
	}
	
	public void update() {
		keyReleased(new KeyEvent(input, KeyEvent.KEY_RELEASED,
				System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'c'));
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cache) {
			connectCache = cache.isSelected();
		} else if(e.getSource() == keepMem) {
			keep = keepMem.isSelected();
		}
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == input) {
			String temp = input.getText().toLowerCase();
			int addr = 0;
			try {
				if (temp.startsWith("0x")) {
					String int16str = temp.substring(2, temp.length());
					addr = Integer.valueOf(int16str, 16);
					bound = Functions.memAddr(addr, 256);
					System.out.println(addr + " " + bound[0] + " " + bound[1]);
				} else {
					addr = Integer.valueOf(temp);
					bound = Functions.memAddr(addr, 256);
					System.out.println(addr + " " + bound[0] + " " + bound[1]);
				}
				DefaultTableModel model = ((DefaultTableModel)table.getModel());
				while(table.getRowCount() > 0)
					model.removeRow(0);
				for(int i = 0; i < 32; i++) {
					Vector<String> row = new Vector<String>();
					row.add("0x" + Integer.toHexString((bound[0]+i*8)));
					for(int j = 0; j < 8; j++) {
						if(bound[0]+i*8+j == addr) {
							this.j = i;
							this.i = j+1;
						}
						int value = UnitPool.getMemory().read(DATA, bound[0]+i*8+j, false);
						row.add(Integer.toHexString(value));
					}
					model.addRow(row);
				}
			} catch (NumberFormatException ex) {	
			} catch (DonotExist e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == table) {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			int addr = bound[0] + row * 8 + col - 1;
			int value = 0;
			try {
				value = UnitPool.getMemory().read(DATA, addr, false);
			} catch (DonotExist ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			if (col > 0) {
				String str = "addr：[hex: 0x" + Integer.toHexString(addr) + " ]";
				str += "[dec: " + String.valueOf(addr) + " ]    ";
				str += "value：[hex: 0x" + Integer.toHexString(value) + " ]";
				str += "[dec: " + value + " ]";
				str += "[ins: " + "not support yet!" + " ]";
				detail.setText(str);
			} else
				detail.setText(" ");
		} else if((e.getSource() == read && read.getSelectedIndex() > 0) ||
				(e.getSource() == write && write.getSelectedIndex() > 0)) {
			String addr;
			if(e.getSource() == read)
				addr = (String) d1.getElementAt(read.getSelectedIndex());
			else
				addr = (String) d2.getElementAt(write.getSelectedIndex());
			input.setText("0x" + addr);
			input.dispatchEvent(new KeyEvent(input, KeyEvent.KEY_RELEASED,
					System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'c'));
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int row = e.getFirstRow();
		int col = e.getColumn();
		if(row >= 0 && col > 0 && valueChanfed) {
			int addr = bound[0] + row*8 + col - 1;
			try {
				String str = (String)table.getValueAt(row, col);
				int value;
				if(str.startsWith("0x"))
					value = Integer.valueOf(str.substring(2, str.length()), 16);
				else
					value = Integer.valueOf(str);
				try {
					UnitPool.getMemory().write(DATA, addr, value, true);
				} catch (DonotExist e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				valueChanfed = false;
				table.setValueAt(Integer.toHexString(value), row, col);
				valueChanfed = true;
				System.out.println(value);
			} catch (NumberFormatException ex) {
				int value = 0;
				try {
					value = UnitPool.getMemory().read(DATA, addr, false);
				} catch (DonotExist e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				valueChanfed = false;
				table.setValueAt(Integer.toHexString(value), row, col);
				valueChanfed = true;
			}
			mousePressed(new MouseEvent(table, 0, 0, 0, 0, 0, 0, false));
			updateList();
		}
	}
}
