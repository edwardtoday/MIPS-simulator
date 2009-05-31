package org.kde9.view.menubar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.kde9.view.Factory;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class Menubar
extends JMenuBar 
implements ActionListener {
	JFileChooser jfc;
	
	JMenu file;
	JMenu run;
	JMenu help;
	
	JMenuItem newFile;
	JMenuItem openFile;
	JMenuItem saveFile;
	JMenuItem saveFileAs;
	JMenuItem quit;
	
	JMenuItem runOneStep;
	JMenuItem runAll;
	
	JMenuItem about;
	
	public Menubar() {
		file = new JMenu("File");
		run = new JMenu("Run");
		help = new JMenu("Help");
		
		newFile = new JMenuItem("New");
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		saveFileAs = new JMenuItem("Save As");
		quit = new JMenuItem("Quit");
		
		runOneStep = new JMenuItem("Run One Step");
		runAll = new JMenuItem("Run");
		
		about = new JMenuItem("About");
		
		add(file);	
		file.add(newFile);
		file.addSeparator();
		file.add(openFile);
		file.add(saveFile);
		file.add(saveFileAs);
		file.addSeparator();
		file.add(quit);
		
		add(run);
		run.add(runOneStep);
		run.add(runAll);
		
		add(help);
		help.add(about);
		
		newFile.addActionListener(this);
		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		saveFileAs.addActionListener(this);
		quit.addActionListener(this);
		
		runOneStep.addActionListener(this);
		runAll.addActionListener(this);
		
		about.addActionListener(this);
	}
	
	public void saveFile() {
		jfc = new JFileChooser();
		jfc.setPreferredSize(new Dimension(700, 400));
		jfc.setAcceptAllFileFilterUsed(true);
		int result = jfc.showSaveDialog(Factory.getMain());
		String path = jfc.getSelectedFile().getAbsolutePath();
		if(result == JFileChooser.APPROVE_OPTION) {
			// TODO
		}
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
		}else if(e.getSource() == openFile) {
			System.out.println("open a new file!");
		}else if(e.getSource() == saveFile) {
			System.out.println("save a new file!");
		}else if(e.getSource() == saveFileAs) {
			saveFile();
			System.out.println("save a file as...");
		}else if(e.getSource() == quit) {
			System.exit(0);
		}else if(e.getSource() == runOneStep) {
			System.out.println("run ont step!");
		}else if(e.getSource() == runAll) {
			System.out.println("run All!");
		}else if(e.getSource() == about) {
			JOptionPane.showMessageDialog(Factory.getMain(), 
					"****KDE9 นคื๗สา****\n" +
					"[ kfirst @9# ]\n" +
					"[ deepsolo @9# ]\n" +
					"[ edwardtoday @9# ] ", 
					"About", 
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println("about!!");
		}
	}
}
