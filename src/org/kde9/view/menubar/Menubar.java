package org.kde9.view.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;

public class Menubar
extends JMenuBar 
implements ActionListener {
	JMenu file;
	
	JMenuItem newFile;
	JMenuItem importFile;
	JMenuItem exportFile;
	JMenuItem quit;
	
	public Menubar() {
		file = new JMenu("file");
		
		newFile = new JMenuItem("New...");
		importFile = new JMenuItem("Import");
		exportFile = new JMenuItem("Export");
		quit = new JMenuItem("Quit");
		
		add(file);	
		file.add(newFile);
		file.addSeparator();
		file.add(importFile);
		file.add(exportFile);
		file.addSeparator();
		file.add(quit);
		
		newFile.addActionListener(this);
		importFile.addActionListener(this);
		exportFile.addActionListener(this);
		quit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == newFile) {
			System.out.println("a new file added!");
		}else if(e.getSource() == importFile) {
			System.out.println("import a new file!");
		}else if(e.getSource() == exportFile) {
			System.out.println("export a new file!");
		}else if(e.getSource() == quit) {
			System.exit(0);
		}
	}
}
