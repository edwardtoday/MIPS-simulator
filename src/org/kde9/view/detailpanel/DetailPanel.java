package org.kde9.view.detailpanel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.kde9.view.Factory;

public class DetailPanel 
extends JTabbedPane {
	JPanel reg;
	JPanel mem;
	JPanel cache;
	JPanel summary;
	
	public DetailPanel() {
		super();//JTabbedPane.LEFT);
		reg = Factory.getReg();
		mem = Factory.getMem();
		cache = Factory.getCac();
		summary = Factory.getSummary();
				
		add("Summary", summary);
		add("Register", reg);
		add("Memory", mem);
		add("Cache",cache);
	}
}
