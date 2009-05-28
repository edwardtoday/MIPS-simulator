package org.kde9.view.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Menubar
extends JMenuBar {
	JMenu file;
	
	public Menubar() {
		file = new JMenu("file");
		add(file);
	}
}
