package org.kde9.view.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu.Separator;

public class Menubar
extends JMenuBar 
implements ActionListener {
	JMenu file;
	JMenu run;
	JMenu help;
	
	JMenuItem newFile;
	JMenuItem importFile;
	JMenuItem exportFile;
	JMenuItem quit;
	
	JMenuItem runOneStep;
	JMenuItem runAll;
	
	JMenuItem about;
	
	public Menubar() {
		file = new JMenu("File");
		run = new JMenu("Run");
		help = new JMenu("Help");
		
		newFile = new JMenuItem("New...");
		importFile = new JMenuItem("Import");
		exportFile = new JMenuItem("Export");
		quit = new JMenuItem("Quit");
		
		runOneStep = new JMenuItem("Run One Step");
		runAll = new JMenuItem("Run");
		
		about = new JMenuItem("About");
		
		add(file);	
		file.add(newFile);
		file.addSeparator();
		file.add(importFile);
		file.add(exportFile);
		file.addSeparator();
		file.add(quit);
		
		add(run);
		run.add(runOneStep);
		run.add(runAll);
		
		add(help);
		help.add(about);
		
		newFile.addActionListener(this);
		importFile.addActionListener(this);
		exportFile.addActionListener(this);
		quit.addActionListener(this);
		
		runOneStep.addActionListener(this);
		runAll.addActionListener(this);
		
		about.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == newFile) {
//			String[] options = { "OK", "Cancel", "NO" };
//			int result=JOptionPane.showOptionDialog(null, "Do you want to save the file existed?", 
//					"CA-Simulator", 
//					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
//					  null, options, options[0]);
			
			System.out.println("a new file added!");
		}else if(e.getSource() == importFile) {
			System.out.println("import a new file!");
		}else if(e.getSource() == exportFile) {
			System.out.println("export a new file!");
		}else if(e.getSource() == quit) {
			System.exit(0);
		}else if(e.getSource() == runOneStep) {
			System.out.println("run ont step!");
		}else if(e.getSource() == runAll) {
			System.out.println("run All!");
		}else if(e.getSource() == about) {
			System.out.println("about!!");
		}
	}
}
