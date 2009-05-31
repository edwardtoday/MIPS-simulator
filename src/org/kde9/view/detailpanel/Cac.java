package org.kde9.view.detailpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.kde9.cpu.UnitPool;
import org.kde9.memory.Cache;
import org.kde9.memory.Memory;
import org.kde9.util.Constants;

public class Cac 
extends JPanel 
implements ActionListener, KeyListener,
		MouseListener, TableModelListener, 
		ItemListener, 
		Constants{
	Cache insCache = UnitPool.getInsCache();
	Cache dataCache = UnitPool.getDataCache();
	
	JPanel cachePanel;
	JPanel insPanel;
	JPanel dataPanel;
	JLabel insLabel;
	JLabel dataLabel;
	JList insList1;
	JList insList2;
	JList dataList1;
	JList dataList2;
	DefaultListModel insListModel1;
	DefaultListModel insListModel2;
	DefaultListModel dataListModel1;
	DefaultListModel dataListModel2;
	JTable insTable;
	JTable dataTable;
	JScrollPane insPane;
	JScrollPane dataPane;
	JButton clearInsCache;
	JButton saveInsCache;
	JButton clearDataCache;
	JButton saveDataCache;
	JComboBox restoreIns;
	JComboBox restoreData;
	JButton hexb;
	JButton decb;
	JPanel upButton;
	
	String justBefore = "Just before clear";
	
	boolean hexOrNot = false;
	HashMap<String, HashMap<Integer, Integer>> savedInsCac;
	HashMap<String, HashMap<Integer, Integer>> savedDataCac;
	boolean combobox = true;
	
	public Cac() {
		savedInsCac = new HashMap<String, HashMap<Integer,Integer>>();
		savedDataCac = new HashMap<String, HashMap<Integer,Integer>>();
		insPanel = new JPanel(new BorderLayout());
		dataPanel = new JPanel(new BorderLayout());
		insLabel = new JLabel("Instruction Cache");
		dataLabel = new JLabel("Data Cache");
		insList1 = new JList();
		insList2 = new JList();
		dataList1 = new JList();
		dataList2 = new JList();
		clearInsCache = new JButton("Clear");
		saveInsCache = new JButton("Save");
		clearDataCache = new JButton("Clear");
		saveDataCache = new JButton("Save");
		
		clearInsCache.addActionListener(this);
		clearDataCache.addActionListener(this);
		saveInsCache.addActionListener(this);
		saveDataCache.addActionListener(this);
		
		insTable = new JTable(0, 3) {
			
		};
		insTable.setCellSelectionEnabled(true);
		insTable.setRowHeight(20);
		insTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		insTable.getColumnModel().getColumn(0).setHeaderValue("地址");
		insTable.getColumnModel().getColumn(1).setHeaderValue("指令Cache值");
		insTable.getColumnModel().getColumn(2).setHeaderValue("Memory地址");
		insTable.getColumnModel().getColumn(0).setWidth(250);
		insPane = new JScrollPane(insTable);
		insPane.setBorder(BorderFactory.createEtchedBorder());
		dataTable = new JTable(0, 3) {
			
		};
		dataTable.setCellSelectionEnabled(true);
		dataTable.setRowHeight(20);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dataTable.getColumnModel().getColumn(0).setHeaderValue("地址");
		dataTable.getColumnModel().getColumn(1).setHeaderValue("数据Cache值");
		dataTable.getColumnModel().getColumn(2).setHeaderValue("Memory地址");
		dataTable.getColumnModel().getColumn(0).setWidth(20);
		dataPane = new JScrollPane(dataTable);
		dataPane.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel restoreInsLabel = new JLabel("Restore to");
		JLabel restoreDataLabel = new JLabel("Restore to");
		restoreIns = new JComboBox();
		restoreIns.addItem("");
		restoreIns.setPreferredSize(new Dimension(100, 30));
		restoreData = new JComboBox();
		restoreData.addItem("");
		restoreData.setPreferredSize(new Dimension(100, 30));
		
		restoreIns.addItemListener(this);
		restoreData.addItemListener(this);
		
		insList1.setPreferredSize(new Dimension(80, 100));
		insList1.setBorder(BorderFactory.createEtchedBorder());
		insListModel1 = new DefaultListModel();
		insList1.setModel(insListModel1);
		
		insList2.setPreferredSize(new Dimension(80, 100));
		insList2.setBorder(BorderFactory.createEtchedBorder());
		insListModel2 = new DefaultListModel();
		insList2.setModel(insListModel2);
		
		dataList1.setPreferredSize(new Dimension(80, 100));
		dataList1.setBorder(BorderFactory.createEtchedBorder());
		dataListModel1 = new DefaultListModel();
		dataList1.setModel(dataListModel1);
		
		dataList2.setPreferredSize(new Dimension(80, 100));
		dataList2.setBorder(BorderFactory.createEtchedBorder());
		dataListModel2 = new DefaultListModel();
		dataList2.setModel(dataListModel2);
		
		JPanel tempIns = new JPanel(new GridLayout(2, 0));
		tempIns.add(insList1);
		tempIns.add(insList2);
		JPanel tempData = new JPanel(new GridLayout(2, 0));
		tempData.add(dataList1);
		tempData.add(dataList2);
		
		JPanel tempInsB = new JPanel();
		tempInsB.add(clearInsCache);
		tempInsB.add(saveInsCache);
		tempInsB.add(restoreInsLabel);
		tempInsB.add(restoreIns);
		JPanel tempDataB = new JPanel();
		tempDataB.add(clearDataCache);
		tempDataB.add(saveDataCache);
		tempDataB.add(restoreDataLabel);
		tempDataB.add(restoreData);
		
//		insPanel.add("North", insLabel);
		insPanel.add("West", tempIns);
		insPanel.add("Center", insPane);
		insPanel.add("South", tempInsB);
		
//		dataPanel.add("North", dataLabel);
		dataPanel.add("West", tempData);
		dataPanel.add("Center", dataPane);
		dataPanel.add("South", tempDataB);
		
		cachePanel = new JPanel(new GridLayout(0, 2));
		TitledBorder t1 = new TitledBorder("Instruction Cache");
		TitledBorder t2 = new TitledBorder("Data Cache");
		t1.setTitleJustification(TitledBorder.CENTER);
		t2.setTitleJustification(TitledBorder.CENTER);
		insPanel.setBorder(t1);
		dataPanel.setBorder(t2);
		
		cachePanel.add(insPanel);
		cachePanel.add(dataPanel);
		
		setLayout(new BorderLayout());
		
		upButton = new JPanel();
		((FlowLayout)upButton.getLayout()).setVgap(0);
		hexb = new JButton("十六进制显示");
		decb = new JButton(" 十进制显示  ");
		upButton.add(decb);
		upButton.add(hexb);
		decb.addActionListener(this);
		hexb.addActionListener(this);
		
		add("South", upButton);
		add("Center", cachePanel);
//		update();
	}
	
	public void clearIns() {
		DefaultTableModel insModel = ((DefaultTableModel)insTable.getModel());
		while(insTable.getRowCount() > 0)
			insModel.removeRow(0);
	}
	
	public void clearData() {
		DefaultTableModel dataModel = ((DefaultTableModel)dataTable.getModel());
		while(dataTable.getRowCount() > 0)
			dataModel.removeRow(0);
	}
	
	synchronized public void update() {
		DefaultTableModel insModel = ((DefaultTableModel)insTable.getModel());
		DefaultTableModel dataModel = ((DefaultTableModel)dataTable.getModel());
		while(insTable.getRowCount() > 0)
			insModel.removeRow(0);
		while(dataTable.getRowCount() > 0)
			dataModel.removeRow(0);
		HashMap<Integer, Integer> inscache = insCache.getCache();
		HashMap<Integer, Integer> datacache = dataCache.getCache();
		int []insIndex = insCache.getShowindex();
		int []dataIndex = dataCache.getShowindex();
		for(int i = 0; i < insIndex.length; i++) {
			Vector<String> row = new Vector<String>();
			if(hexOrNot) {
				if(insIndex[i] != -1) {
					row.add("0x" + Integer.toHexString(i));
					row.add("0x" + Integer.toHexString(inscache.get(insIndex[i])));
					row.add("0x" + Integer.toHexString(insIndex[i]));
				}else {
					row.add("0x" + Integer.toHexString(i));
					row.add("null");
					row.add("-1");
				}
			}else {
				row.add(String.valueOf(i));
				row.add(String.valueOf(inscache.get(insIndex[i])));
				row.add(String.valueOf(insIndex[i]));
			}
			insModel.addRow(row);
		}
		for(int i = 0; i < dataIndex.length; i++) {
			Vector<String> row = new Vector<String>();
			if(hexOrNot) {
				if(dataIndex[i] != -1) {
					row.add("0x" + Integer.toHexString(i));
					row.add("0x" + Integer.toHexString(datacache.get(dataIndex[i])));
					row.add("0x" + Integer.toHexString(dataIndex[i]));
				}else {
					row.add("0x" + Integer.toHexString(i));
					row.add("null");
					row.add("-1");
				}
			}else {
				row.add(String.valueOf(i));
				row.add(String.valueOf(datacache.get(dataIndex[i])));
				row.add(String.valueOf(dataIndex[i]));
			}
			dataModel.addRow(row);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == decb) {
			hexOrNot = false;
			update();
		}else if(e.getSource() == hexb) {
			hexOrNot = true;
			update();
		}else if(e.getSource() == clearInsCache) {
			saveInsCac(justBefore);
			insCache.clear();
			update();
			//clearIns();
		}else if(e.getSource() == clearDataCache) {
			saveDataCac(justBefore);
			dataCache.clear();
			update();
			//clearData();
		}else if(e.getSource() == saveInsCache) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String dateString = formatter.format(date);
			
			saveInsCac(dateString);
		}else if(e.getSource() == saveDataCache) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String dateString = formatter.format(date);
			
			saveDataCac(dateString);
		}
	}
	
	public void saveInsCac(String name) {
		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
		for(int addr : insCache.getCache().keySet()) {
			temp.put(addr, insCache.getCache().get(addr));
		}
		restoreIns.removeItem(name);
		restoreIns.addItem(name);
		savedInsCac.put(name, temp);
	}
	
	public void saveDataCac(String name) {
		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
		for(int addr : dataCache.getCache().keySet()) {
			temp.put(addr, dataCache.getCache().get(addr));
		}
		restoreData.removeItem(name);
		restoreData.addItem(name);
		savedDataCac.put(name, temp);
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == restoreIns) {
			if (combobox) {
				combobox = false;
				System.out.println(e.getItem());
				HashMap<Integer, Integer> m = insCache.getCache();
				HashMap<Integer, Integer> r = savedInsCac.get(e.getItem()
						.toString());
				if (r != null) {
					m.clear();
					for (int addr : r.keySet())
						m.put(addr, r.get(addr));
					restoreIns.setSelectedIndex(0);
					update();
				}
				combobox = true;
			}
		} else if (e.getSource() == restoreData) {
			if (combobox) {
				combobox = false;
				System.out.println(e.getItem());
				HashMap<Integer, Integer> m = dataCache.getCache();
				HashMap<Integer, Integer> r = savedDataCac.get(e.getItem()
						.toString());
				if (r != null) {
					m.clear();
					for (int addr : r.keySet())
						m.put(addr, r.get(addr));
					restoreData.setSelectedIndex(0);
					update();
				}
				combobox = true;
			}
		}
	}
}
