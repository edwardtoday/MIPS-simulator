package org.kde9.view.menubar;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

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
	JMenu project;
	JMenu help;
	
	JMenuItem newFile;
	JMenuItem openFile;
	JMenuItem saveFile;
	JMenuItem saveFileAs;
	JMenuItem quit;
	
	JMenuItem runOneStep;
	JMenuItem runAll;
	JMenuItem stop;
	
	JMenuItem compile;
	JMenuItem code;
	
	JMenuItem about;
	
	public Menubar() {
		file = new JMenu("File");
		run = new JMenu("Run");
		help = new JMenu("Help");
		project = new JMenu("Project");
		
		newFile = new JMenuItem("New");
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		saveFileAs = new JMenuItem("Save As");
		quit = new JMenuItem("Quit");
		
		runOneStep = new JMenuItem("Run One Step");
		runAll = new JMenuItem("Run");
		stop = new JMenuItem("Stop");
		
		compile = new JMenuItem("Binary");
		code = new JMenuItem("Code");
		
		about = new JMenuItem("About");
		
		add(file);
		file.setMnemonic('f');
		file.add(newFile);
		file.addSeparator();
		file.add(openFile);
		file.add(saveFile);
		file.add(saveFileAs);
		file.addSeparator();
		file.add(quit);
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		
		add(run);
		run.setMnemonic('r');
		run.add(runOneStep);
		run.add(runAll);
		run.add(stop);
		runOneStep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		stop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		run.addMenuListener(new MenuListener() {

			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				setMenu();
			}
			
		});
		
		add(project);
		project.setMnemonic('p');
		project.add(compile);
		project.add(code);
		compile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
		code.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		project.addMenuListener(new MenuListener() {

			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				setMenu();
			}
			
		});
		
		add(help);
		help.setMnemonic('h');
		help.add(about);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		
		newFile.addActionListener(this);
		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		saveFileAs.addActionListener(this);
		quit.addActionListener(this);
		
		runOneStep.addActionListener(this);
		runAll.addActionListener(this);
		stop.addActionListener(this);
		
		compile.addActionListener(this);
		code.addActionListener(this);
		
		about.addActionListener(this);
	}
	
	public void setMenu() {
		if(Factory.getEdit().isEditable()) {
			runOneStep.setEnabled(false);
			runAll.setEnabled(false);
			stop.setEnabled(false);
			compile.setEnabled(false);
			code.setEnabled(false);
		}else {
			runOneStep.setEnabled(true);
			runAll.setEnabled(true);
			stop.setEnabled(true);
			compile.setEnabled(true);
			code.setEnabled(true);
		}
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
			JButton b4 = Factory.getEdit().getButton4();
			if(!Factory.getEdit().isEditable())
				b4.getActionListeners()[0].actionPerformed(
					new ActionEvent(b4, ActionEvent.ACTION_PERFORMED, ""));
			System.out.println("run ont step!");
		}else if(e.getSource() == runAll) {
			JButton b3 = Factory.getEdit().getButton3();
			if(!Factory.getEdit().isEditable())
				b3.getActionListeners()[0].actionPerformed(
					new ActionEvent(b3, ActionEvent.ACTION_PERFORMED, ""));
			System.out.println("run All!");
		}else if(e.getSource() == stop) {
			JButton b5 = Factory.getEdit().getButton5();
			if(!Factory.getEdit().isEditable())
				b5.getActionListeners()[0].actionPerformed(
					new ActionEvent(b5, ActionEvent.ACTION_PERFORMED, ""));
			System.out.println("stop");
		}else if(e.getSource() == compile){
			JButton b2 = Factory.getEdit().getButton2();
			if(!Factory.getEdit().isEditable())
				b2.getActionListeners()[0].actionPerformed(
					new ActionEvent(b2, ActionEvent.ACTION_PERFORMED, ""));
			System.out.println("compile");
		}else if(e.getSource() == code) {
			JButton b1 = Factory.getEdit().getButton1();
			if(!Factory.getEdit().isEditable())
				b1.getActionListeners()[0].actionPerformed(
					new ActionEvent(b1, ActionEvent.ACTION_PERFORMED, ""));
			System.out.println("code");
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
