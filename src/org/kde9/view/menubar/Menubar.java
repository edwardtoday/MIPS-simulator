package org.kde9.view.menubar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.kde9.cpu.UnitPool;
import org.kde9.util.Constants;
import org.kde9.view.Factory;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class Menubar
extends JMenuBar 
implements ActionListener, Constants {
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
		if(Factory.getEdit().getFilePathSaved() != null) {
			try {
				FileWriter fw = new FileWriter(Factory.getEdit().getFilePathSaved());
				BufferedWriter bw = new BufferedWriter(fw);
				String content = Factory.getEdit().getEditPane().getText();
				bw.write(content);
				Factory.getEdit().setSaved(true);
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			saveAsFile();
		}
	}
	
	public void saveAsFile() {
		jfc = new JFileChooser();
		jfc.setPreferredSize(new Dimension(700, 400));
		jfc.setAcceptAllFileFilterUsed(true);
		int result = jfc.showSaveDialog(Factory.getMain());
		File fileChosen = jfc.getSelectedFile();
		if(result == JFileChooser.APPROVE_OPTION) {
			if(fileChosen != null) {
				String path = fileChosen.getAbsolutePath();
				Factory.getEdit().setFilePathSaved(path);
				Factory.getEdit().setSaved(true);
				try {
					FileWriter fw = new FileWriter(path);
					BufferedWriter bw = new BufferedWriter(fw);
					String content = Factory.getEdit().getEditPane().getText();
					bw.write(content);
					bw.close();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void openFile() {
		jfc = new JFileChooser();
		jfc.setPreferredSize(new Dimension(700, 400));
		jfc.setAcceptAllFileFilterUsed(true);
		int result = jfc.showOpenDialog(Factory.getMain());
		File fileChosen = jfc.getSelectedFile();
		if(result == JFileChooser.OPEN_DIALOG) {
//			System.out.println("!!!!!!!!!!!!!!" + path);
			if (fileChosen != null) {
				String path = fileChosen.getAbsolutePath();
				Factory.getEdit().setFilePathSaved(path);
				Factory.getEdit().setSaved(true);
				try {
					FileReader fr = new FileReader(path);
					BufferedReader br = new BufferedReader(fr);
					String temp = br.readLine();
					String toPrint = "";
					String lineNo = "";
					int i = 1;
					while (temp != null) {
						toPrint += temp + NEWLINE;
						lineNo += String.valueOf(i) + NEWLINE;
						i++;
						temp = br.readLine();
					}
					Factory.getEdit().getEditPane().setText(toPrint);
					Factory.getEdit().getLine().setText(lineNo);
					Factory.getEdit().getEdit().setSelected(true);
					Factory.getEdit().actionPerformed(new ActionEvent(
							Factory.getEdit().getEdit(), ActionEvent.ACTION_PERFORMED, ""));
					br.close();
					fr.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == newFile) {
			String[] options = { "OK", "NO", "Cancel"};
			if(!Factory.getEdit().isSaved()) {
				int result = JOptionPane.showOptionDialog(null, "Do you want to save the file existed?", 
						"CA-Simulator", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						  null, options, options[0]);
				if(result == JOptionPane.OK_OPTION) {
					saveFile();
				} else if(result == JOptionPane.CANCEL_OPTION)
					return;
			}
			Factory.getEdit().getEditPane().setText("");
			Factory.getEdit().getLine().setText("1");
			Factory.getEdit().setSaved(false);
			Factory.getEdit().setFilePathSaved(null);
			System.out.println("a new file added!");
		}else if(e.getSource() == openFile) {
			openFile();
			System.out.println("open a new file!");
		}else if(e.getSource() == saveFile) {
			saveFile();
			System.out.println("save a new file!");
		}else if(e.getSource() == saveFileAs) {
			saveAsFile();
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
