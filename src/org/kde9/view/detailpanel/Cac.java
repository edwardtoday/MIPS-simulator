package org.kde9.view.detailpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		Constants{
	Memory mem = UnitPool.getMemory();
	Cache insCache = UnitPool.getInsCache();
	Cache dataCache = UnitPool.getDataCache();
	
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
	
	
	
	public Cac() {
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
		
		insTable = new JTable(CACHE_SIZE, 3) {
			
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
		dataTable = new JTable(CACHE_SIZE, 3) {
			
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
		restoreIns.setPreferredSize(new Dimension(100, 30));
		restoreData = new JComboBox();
		restoreData.setPreferredSize(new Dimension(100, 30));
		
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
		
		setLayout(new GridLayout(0, 2));
		TitledBorder t1 = new TitledBorder("Instruction Cache");
		TitledBorder t2 = new TitledBorder("Data Cache");
		t1.setTitleJustification(TitledBorder.CENTER);
		t2.setTitleJustification(TitledBorder.CENTER);
		insPanel.setBorder(t1);
		dataPanel.setBorder(t2);
		add(insPanel);
		add(dataPanel);
		
		update();
	}
	
	public void update() {
		DefaultTableModel insModel = ((DefaultTableModel)insTable.getModel());
		DefaultTableModel dataModel = ((DefaultTableModel)dataTable.getModel());
		while(insTable.getRowCount() > 0)
			insModel.removeRow(0);
		while(dataTable.getRowCount() > 0)
			dataModel.removeRow(0);
		HashMap<Integer, Integer> inscache = UnitPool.getInsCache().getCache();
		HashMap<Integer, Integer> datacache = UnitPool.getDataCache().getCache();
		for(int i = 0; i < CACHE_SIZE; i++) {
			Vector<String> row = new Vector<String>();
			row.add("0x" + Integer.toHexString(i));
			insModel.addRow(row);
		}
		for(int i = 0; i < CACHE_SIZE; i++) {
			Vector<String> row = new Vector<String>();
			row.add("0x" + Integer.toHexString(i));
			dataModel.addRow(row);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
}
