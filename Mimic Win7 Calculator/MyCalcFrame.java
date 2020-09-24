import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.BorderLayout;
import java.awt.event.*;

public class MyCalcFrame extends JFrame implements ActionListener{

	public MyCalcFrame(){				
		ResultPanel panel1 = new ResultPanel();
			panel1.setPreferredSize(new Dimension(300,75));
			add(panel1, BorderLayout.NORTH);
			
		ButtonPanel panel2 = new ButtonPanel();
			panel2.setPreferredSize(new Dimension(100,75));
			add(panel2, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel();
			panel3.setPreferredSize(new Dimension(75,100));
			panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

			JRadioButton Hex = new JRadioButton("Hex");
			JRadioButton Dec = new JRadioButton("Dec");
			JRadioButton Oct = new JRadioButton("Oct");
			JRadioButton Bin = new JRadioButton("Bin");

			panel3.add(Hex);
				Hex.setActionCommand("Hex");
				Hex.addActionListener(this);
			panel3.add(Dec);
				Dec.setActionCommand("Dec");
				Dec.addActionListener(this);
				Dec.setSelected(true);
			panel3.add(Oct);
				Oct.setActionCommand("Oct");
				Oct.addActionListener(this);
			panel3.add(Bin);
				Bin.setActionCommand("Bin");
				Bin.addActionListener(this);
			
			ButtonGroup radioSet1 = new ButtonGroup();
			radioSet1.add(Hex);
			radioSet1.add(Dec);
			radioSet1.add(Oct);
			radioSet1.add(Bin);
			
			JRadioButton Qword = new JRadioButton("Qword");
			Qword.setEnabled(false);
			JRadioButton Dword = new JRadioButton("Dword");
			Dword.setEnabled(false);
			JRadioButton Word = new JRadioButton("Word");
			Word.setEnabled(false);
			JRadioButton Byte = new JRadioButton("Byte");
			Byte.setEnabled(false);
			
			panel3.add(Qword);
			Qword.setSelected(true);
			panel3.add(Dword);
			panel3.add(Word);
			panel3.add(Byte);
			
			ButtonGroup radioSet2 = new ButtonGroup();
			radioSet2.add(Qword);
			radioSet2.add(Dword);
			radioSet2.add(Word);
			radioSet2.add(Byte);
			
			add(panel3, BorderLayout.WEST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Hex"){
			JOptionPane.showMessageDialog(null, "Note:"
					+ "\nHex specific functionalities are not implemented.", "Hex Mode Selected", JOptionPane.INFORMATION_MESSAGE);
			ButtonPanel.hexSelected();
		}
		
		if (e.getActionCommand() == "Dec")
			ButtonPanel.decSelected();
		
		if (e.getActionCommand() == "Oct"){
			JOptionPane.showMessageDialog(null, "Note:"
					+ "\nOct specific functionalities are not implemented.", "Oct Mode Selected", JOptionPane.INFORMATION_MESSAGE);
			ButtonPanel.octSelected();
		}
		
		if (e.getActionCommand() == "Bin"){
			JOptionPane.showMessageDialog(null, "Note:"
					+ "\nBin specific functionalities are not implemented.", "Bin Mode Selected", JOptionPane.INFORMATION_MESSAGE);
			ButtonPanel.binSelected();
		}
			    
	    if (e.getActionCommand() == "Hide Calculator")
			JOptionPane.showMessageDialog(null, "Currently has no functionality.", "Hide Calculator Selected", JOptionPane.INFORMATION_MESSAGE);

	    if (e.getActionCommand() == "Copy")
	    {
	    	StringSelection stringSelection = new StringSelection(ResultPanel.num1Field.getText());
	    	Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard ();
	    	clpbrd.setContents (stringSelection, null);
	    }
	    if (e.getActionCommand() == "View Help")
	    {
	    	JOptionPane.showMessageDialog(null, "Non-Working Operations:"
	         		+ "\n- Hex/Dec/Oct/Bin Conversion"
	         		+ "\n- Binary Conversion for Negative Numbers"
	         		+ "\n- Hide Calculator"
	         		+ "\n- A-F, Mod Buttons"
	         		+ "\n"
	         		+ "\nWorking Operations:"
	         		+ "\n- Everything else"
	         		+ "\n"
	         		+ "\nNotes:"
	         		+ "\n- Disabled user input in textfield to eliminate validation necessity."
	         		+ "\n- The Hex/Dec/Oct/Bin Radio Buttons are only useful for enabling/disabling"
	         		+ "\n  buttons used for each mode. They have no other functionality."
	         		+ "\n- Only 'Dec' works 100%."
	         		+ "\n"
	         		+ "\nKnown Bugs:"
	         		+ "\n- N/A", "For the grader...", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
}