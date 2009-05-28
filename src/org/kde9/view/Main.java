package org.kde9.view;

import java.awt.BorderLayout;
import java.security.AccessControlException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.kde9.view.detailpanel.DetailPanel;
import org.kde9.view.editpanel.EditPanel;
import org.kde9.view.menubar.Menubar;

import ch.randelshofer.quaqua.QuaquaManager;
import ch.randelshofer.quaqua.util.Methods;

public class Main 
extends JFrame {
	private EditPanel edit;
	private DetailPanel detail;
	private JMenuBar menu;
	
	public Main() {
		edit = new EditPanel();
		detail = new DetailPanel();
		menu = new Menubar();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add("West", edit);
		panel.add("Center", detail);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
		
		setContentPane(panel);
		setJMenuBar(menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);
	}
	
	public static void main(String args[]) {
		final java.util.List argList = Arrays.asList(args);
		
        System.setProperty("sun.java2d.noddraw", "true");
		
		if (System.getProperty("os.name").toLowerCase().indexOf("mac") == -1) {
			System.setProperty("Quaqua.Debug.crossPlatform", "true");
			System.setProperty("swing.aatext", "true");
			//System.setProperty("JButton.style", "bevel");
		}
		try {
			// System.setProperty("Quaqua.TabbedPane.design", "jaguar");
			String lafClassName = QuaquaManager.getLookAndFeelClassName();
			System.out.println(lafClassName);
			UIManager.setLookAndFeel(lafClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (System.getProperty("apple.laf.useScreenMenuBar") == null
					&& System.getProperty("com.apple.macos.useScreenMenuBar") == null) {
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				System.setProperty("com.apple.macos.useScreenMenuBar", "true");
			}
		} catch (AccessControlException e) {
			// can't do anything about this
		}

		boolean useDefaultLookAndFeelDecoration = !System
				.getProperty("os.name").toLowerCase().startsWith("mac")
				&& !System.getProperty("os.name").toLowerCase().startsWith(
						"darwin");
		int index = argList.indexOf("-decoration");
		if (index != -1 && index < argList.size() - 1) {
			useDefaultLookAndFeelDecoration = argList.get(index + 1).equals(
					"true");
		}

		if (useDefaultLookAndFeelDecoration) {
			try {
				Methods.invokeStatic(JFrame.class,
						"setDefaultLookAndFeelDecorated", Boolean.TYPE,
						Boolean.TRUE);
				Methods.invokeStatic(JDialog.class,
						"setDefaultLookAndFeelDecorated", Boolean.TYPE,
						Boolean.TRUE);
			} catch (NoSuchMethodException e) {
				// can't do anything about this
				e.printStackTrace();
			}
		}

		new Main();
	}
}