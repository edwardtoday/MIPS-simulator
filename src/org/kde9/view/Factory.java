package org.kde9.view;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.kde9.view.detailpanel.Cac;
import org.kde9.view.detailpanel.DetailPanel;
import org.kde9.view.detailpanel.Mem;
import org.kde9.view.detailpanel.Reg;
import org.kde9.view.detailpanel.Summary;
import org.kde9.view.editpanel.EditPanel;
import org.kde9.view.menubar.Menubar;

public class Factory {
	static Reg reg = new Reg();
	static Mem mem = new Mem();
	static Cac cac = new Cac();
//	static JPanel cache = new JPanel();
	static Summary summary = new Summary();
	static private DetailPanel detail = new DetailPanel();
	static private EditPanel edit = new EditPanel();
	static private Menubar menu = new Menubar();
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
	public static Cac getCac() {
		return cac;
	}
//	public static JPanel getCache() {
//		return cache;
//	}
	public static Summary getSummary() {
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
