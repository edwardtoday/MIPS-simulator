package org.kde9.view;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.kde9.view.detailpanel.DetailPanel;
import org.kde9.view.detailpanel.Mem;
import org.kde9.view.detailpanel.Reg;
import org.kde9.view.editpanel.EditPanel;

public class Factory {
	static Reg reg = new Reg();
	static Mem mem = new Mem();
	static JPanel cache = new JPanel();
	static JPanel summary = new JPanel();
	static private DetailPanel detail = new DetailPanel();
	static private EditPanel edit = new EditPanel();
	static private JMenuBar menu = new JMenuBar();
	static private Main main;
	
	public static Main getMain() {
		return main;
	}
	public static void setMain(Main main) {
		Factory.main = main;
	}
	public static Reg getReg() {
		return reg;
	}
	public static Mem getMem() {
		return mem;
	}
	public static JPanel getCache() {
		return cache;
	}
	public static JPanel getSummary() {
		return summary;
	}
	public static EditPanel getEdit() {
		return edit;
	}
	public static DetailPanel getDetail() {
		return detail;
	}
	public static JMenuBar getMenu() {
		return menu;
	}
	
	
}
