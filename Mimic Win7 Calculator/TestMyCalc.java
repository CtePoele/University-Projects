import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;

public class TestMyCalc {

	public static void main(String[] args) {
		MyCalcFrame calc = new MyCalcFrame();
		calc.setSize(645,390);
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calc.setTitle("Calculator");
		
		JMenuBar menuBar = new JMenuBar();
		
	    JMenu viewMenu = new JMenu("View");
	    menuBar.add(viewMenu);
	    JMenuItem viewMenuItem = new JMenuItem("Hide Calculator");
	    viewMenu.add(viewMenuItem);
	    viewMenuItem.addActionListener(calc);
	    
	    JMenu editMenu = new JMenu("Edit");
	    menuBar.add(editMenu);
	    JMenuItem copyMenuItem = new JMenuItem("Copy");
	    editMenu.add(copyMenuItem);
	    copyMenuItem.addActionListener(calc);
	    
	    JMenu helpMenu = new JMenu("Help");
	    menuBar.add(helpMenu);
	    JMenuItem helpMenuItem = new JMenuItem("View Help");
	    helpMenu.add(helpMenuItem);
	    helpMenuItem.addActionListener(calc);

	    calc.setJMenuBar(menuBar);
	    calc.setVisible(true);
	}
}