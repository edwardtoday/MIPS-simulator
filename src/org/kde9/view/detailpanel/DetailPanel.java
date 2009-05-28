package org.kde9.view.detailpanel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DetailPanel 
extends JTabbedPane {
	JPanel reg;
	JPanel mem;
	JPanel cache;
	JPanel summary;
	
	public DetailPanel() {
		super();//JTabbedPane.LEFT);
		reg = new JPanel();
		mem = new JPanel();
		cache = new JPanel();
		summary = new JPanel();
				
		add("Summary", summary);
		add("Register", reg);
		add("Memory", mem);
		add("Cache",cache);
	}
}
