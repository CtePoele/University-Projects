import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;

public class ButtonPanel extends JPanel implements ActionListener{
	static JButton zeroButton, oneButton, twoButton, threeButton,
					fourButton, fiveButton, sixButton, sevenButton,
					eightButton, nineButton, modButton,
					aButton, bButton, cButton, dButton,
					eButton, fButton, backButton, ceButton,
					clearButton, signButton, sqrtButton, percentButton,
					fractionButton, decimalButton, equalsButton, addButton,
					subButton, multButton, divButton;
	
	static JButton blankButton1, blankButton2, blankButton3,
					blankButton4, blankButton5, blankButton6,
					blankButton7, blankButton8, blankButton9,
					blankButton10, blankButton11, blankButton12,
					blankButton13, blankButton14, blankButton15,
					blankButton16, blankButton17, blankButton18;
	
	static JLabel bin1, bin2, bin3, bin4, bin5, bin6,  bin7, bin8,
				   bin9, bin10, bin11, bin12, bin13, bin14, bin15, bin16;

	public ButtonPanel()
	{
		zeroButton = new JButton("0");
			zeroButton.addActionListener(this);
			add(zeroButton);
		oneButton = new JButton("1");
			oneButton.addActionListener(this);
			add(oneButton);
		twoButton = new JButton("2");
			twoButton.addActionListener(this);
			add(twoButton);
		threeButton = new JButton("3");
			threeButton.addActionListener(this);
			add(threeButton);
		fourButton = new JButton("4");
			fourButton.addActionListener(this);
			add(fourButton);
		fiveButton = new JButton("5");
			fiveButton.addActionListener(this);
			add(fiveButton);
		sixButton = new JButton("6");
			sixButton.addActionListener(this);
			add(sixButton);
		sevenButton = new JButton("7");
			sevenButton.addActionListener(this);
			add(sevenButton);
		eightButton = new JButton("8");
			eightButton.addActionListener(this);
			add(eightButton);
		nineButton = new JButton("9");
			nineButton.addActionListener(this);
			add(nineButton);
		
		modButton = new JButton("Mod");
			modButton.addActionListener(this);
			add(modButton);
			
		aButton = new JButton("A");
			aButton.addActionListener(this);
			aButton.setEnabled(false);
			add(aButton);
		bButton = new JButton("B");
			bButton.addActionListener(this);
			bButton.setEnabled(false);
			add(bButton);
		cButton = new JButton("C");
			cButton.addActionListener(this);
			cButton.setEnabled(false);
			add(cButton);
		dButton = new JButton("D");
			dButton.addActionListener(this);
			dButton.setEnabled(false);
			add(dButton);
		eButton = new JButton("E");
			eButton.addActionListener(this);
			eButton.setEnabled(false);
			add(eButton);
		fButton = new JButton("F");
			fButton.addActionListener(this);
			fButton.setEnabled(false);
			add(fButton);
		
		backButton = new JButton("←");
			backButton.addActionListener(this);
			add(backButton);
		ceButton = new JButton("CE");
			ceButton.addActionListener(this);
			add(ceButton);
		clearButton = new JButton("C");
			clearButton.addActionListener(this);
			add(clearButton);
		
		signButton = new JButton("±");
			signButton.addActionListener(this);
			add(signButton);
		sqrtButton = new JButton("√");
			sqrtButton.addActionListener(this);
			sqrtButton.setEnabled(false);
			add(sqrtButton);
		percentButton = new JButton("%");
			percentButton.addActionListener(this);
			percentButton.setEnabled(false);
			add(percentButton);
		fractionButton = new JButton("1/x");
			fractionButton.addActionListener(this);
			fractionButton.setEnabled(false);
			add(fractionButton);
		decimalButton = new JButton(".");
			decimalButton.addActionListener(this);
			decimalButton.setEnabled(false);
			add(decimalButton);
		equalsButton = new JButton("=");
			equalsButton.addActionListener(this);
			add(equalsButton);
		addButton = new JButton("+");
			addButton.addActionListener(this);
			add(addButton);
		subButton = new JButton("-");
			subButton.addActionListener(this);
			add(subButton);
		multButton = new JButton("*");
			multButton.addActionListener(this);
			add(multButton);
		divButton = new JButton("/");
			divButton.addActionListener(this);
			add(divButton);
		
		blankButton1 = new JButton(" ");
			blankButton1.setEnabled(false);
		blankButton2 = new JButton(" ");
			blankButton2.setEnabled(false);
			add(blankButton2);
		blankButton3 = new JButton(" ");
			blankButton3.setEnabled(false);
			add(blankButton3);
		blankButton4 = new JButton(" ");
			blankButton4.setEnabled(false);
			add(blankButton4);
		blankButton5 = new JButton(" ");
			blankButton5.setEnabled(false);
			add(blankButton5);
		blankButton6 = new JButton(" ");
			blankButton6.setEnabled(false);
			add(blankButton6);
		blankButton7 = new JButton(" ");
			blankButton7.setEnabled(false);
			add(blankButton7);
		blankButton8 = new JButton(" ");
			blankButton8.setEnabled(false);
			add(blankButton8);
		blankButton9 = new JButton(" ");
			blankButton9.setEnabled(false);
			add(blankButton9);
		blankButton10 = new JButton(" ");
			blankButton10.setEnabled(false);
			add(blankButton10);
		blankButton11 = new JButton(" ");
			blankButton11.setEnabled(false);
			add(blankButton11);
		blankButton12 = new JButton(" ");
			blankButton12.setEnabled(false);
			add(blankButton12);
		blankButton13 = new JButton(" ");
			blankButton13.setEnabled(false);
			add(blankButton13);
		blankButton14 = new JButton(" ");
			blankButton14.setEnabled(false);
			add(blankButton14);
		blankButton15 = new JButton(" ");
			blankButton15.setEnabled(false);
			add(blankButton15);
		blankButton16 = new JButton(" ");
			blankButton16.setEnabled(false);
			add(blankButton16);
		blankButton17= new JButton(" ");
			blankButton17.setEnabled(false);
			add(blankButton17);
		blankButton18= new JButton(" ");
			blankButton18.setEnabled(false);
			add(blankButton18);
			
		bin1 = new JLabel("[0, 0, 0, 0]");
			add(bin1);
		bin2 = new JLabel("[0, 0, 0, 0]");
			add(bin2);
		bin3 = new JLabel("[0, 0, 0, 0]");
			add(bin3);
		bin4 = new JLabel("[0, 0, 0, 0]");
			add(bin4);
		bin5 = new JLabel("[0, 0, 0, 0]");
			add(bin5);
		bin6 = new JLabel("[0, 0, 0, 0]");
			add(bin6);
		bin7 = new JLabel("[0, 0, 0, 0]");
			add(bin7);
		bin8 = new JLabel("[0, 0, 0, 0]");
			add(bin8);
		bin9 = new JLabel("[0, 0, 0, 0]");
			add(bin9);
		bin10 = new JLabel("[0, 0, 0, 0]");
			add(bin10);
		bin11 = new JLabel("[0, 0, 0, 0]");
			add(bin11);
		bin12 = new JLabel("[0, 0, 0, 0]");
			add(bin12);
		bin13 = new JLabel("[0, 0, 0, 0]");
			add(bin13);
		bin14 = new JLabel("[0, 0, 0, 0]");
			add(bin14);
		bin15 = new JLabel("[0, 0, 0, 0]");
			add(bin15);
		bin16 = new JLabel("[0, 0, 0, 0]");
			add(bin16);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		int min = 61, pref = 61, max = 61;
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin16)
						.addComponent(bin8)
						.addComponent(blankButton18, min, pref, max)
						.addComponent(blankButton1, min, pref, max)
						.addComponent(blankButton2, min, pref, max)
						.addComponent(blankButton3, min, pref, max)
						.addComponent(blankButton4, min, pref, max)
						.addComponent(blankButton5, min, pref, max))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin15)
						.addComponent(bin7)
						.addComponent(modButton, min, pref, max)
						.addComponent(blankButton6, min, pref, max)
						.addComponent(blankButton7, min, pref, max)
						.addComponent(blankButton8, min, pref, max)
						.addComponent(blankButton9, min, pref, max)
						.addComponent(blankButton10, min, pref, max))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin14)
						.addComponent(bin6)
						.addComponent(aButton, min, pref, max)
						.addComponent(bButton, min, pref, max)
						.addComponent(cButton, min, pref, max)
						.addComponent(dButton, min, pref, max)
						.addComponent(eButton, min, pref, max)
						.addComponent(fButton, min, pref, max))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin13)
						.addComponent(bin5)
						.addComponent(blankButton11, min, pref, max)
						.addComponent(backButton, min, pref, max)
						.addComponent(sevenButton, min, pref, max)
						.addComponent(fourButton, min, pref, max)
						.addComponent(oneButton, min, pref, max)
						.addComponent(zeroButton, min, pref, max))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin12)
						.addComponent(bin4)
						.addComponent(blankButton12, min, pref, max)
						.addComponent(ceButton, min, pref, max)
						.addComponent(eightButton, min, pref, max)
						.addComponent(fiveButton, min, pref, max)
						.addComponent(twoButton, min, pref, max)
						.addComponent(blankButton13, min, pref, max))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin11)
						.addComponent(bin3)
						.addComponent(blankButton14, min, pref, max)
						.addComponent(clearButton, min, pref, max)
						.addComponent(nineButton, min, pref, max)
						.addComponent(sixButton, min, pref, max)
						.addComponent(threeButton, min, pref, max)
						.addComponent(decimalButton, min, pref, max))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin10)
						.addComponent(bin2)
						.addComponent(blankButton15, min, pref, max)
						.addComponent(signButton, min, pref, max)
						.addComponent(divButton, min, pref, max)
						.addComponent(multButton, min, pref, max)
						.addComponent(subButton, min, pref, max)
						.addComponent(addButton, min, pref, max))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin9)
						.addComponent(bin1)
						.addComponent(blankButton16, min, pref, max)
						.addComponent(sqrtButton, min, pref, max)
						.addComponent(percentButton, min, pref, max)
						.addComponent(fractionButton, min, pref, max)
						.addComponent(equalsButton, min, pref, max)
						.addComponent(blankButton17, min, pref, max))
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin16)
						.addComponent(bin15)
						.addComponent(bin14)
						.addComponent(bin13)
						.addComponent(bin12)
						.addComponent(bin11)
						.addComponent(bin10)
						.addComponent(bin9))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bin8)
						.addComponent(bin7)
						.addComponent(bin6)
						.addComponent(bin5)
						.addComponent(bin4)
						.addComponent(bin3)
						.addComponent(bin2)
						.addComponent(bin1))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(blankButton18)
						.addComponent(modButton)
						.addComponent(aButton)
						.addComponent(blankButton11)
						.addComponent(blankButton12)
						.addComponent(blankButton14)
						.addComponent(blankButton15)
						.addComponent(blankButton16))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(blankButton1)
						.addComponent(blankButton6)
						.addComponent(bButton)
						.addComponent(backButton)
						.addComponent(ceButton)
						.addComponent(clearButton)
						.addComponent(signButton)
						.addComponent(sqrtButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(blankButton2)
						.addComponent(blankButton7)
						.addComponent(cButton)
						.addComponent(nineButton)
						.addComponent(eightButton)
						.addComponent(sevenButton)
						.addComponent(divButton)
						.addComponent(percentButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(blankButton3)
						.addComponent(blankButton8)
						.addComponent(dButton)
						.addComponent(sixButton)
						.addComponent(fiveButton)
						.addComponent(fourButton)
						.addComponent(multButton)
						.addComponent(fractionButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(blankButton4)
						.addComponent(blankButton9)
						.addComponent(eButton)
						.addComponent(threeButton)
						.addComponent(twoButton)
						.addComponent(oneButton)
						.addComponent(subButton)
						.addComponent(blankButton17))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(blankButton5)
						.addComponent(blankButton10)
						.addComponent(fButton)
						.addComponent(zeroButton)
						.addComponent(blankButton13)
						.addComponent(decimalButton)
						.addComponent(addButton)
						.addComponent(equalsButton))
						
		);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == zeroButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "0");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == oneButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "1");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == twoButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "2");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == threeButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "3");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == fourButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "4");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == fiveButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "5");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == sixButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "6");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == sevenButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "7");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == eightButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "8");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == nineButton){
			ResultPanel.num1Field.setText(ResultPanel.num1Field.getText() + "9");
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == modButton){
			JOptionPane.showMessageDialog(null, "Currently has no functionality.", "Mod Button Pushed", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == aButton){
			JOptionPane.showMessageDialog(null, "Currently has no functionality.", "A Button Pushed", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == bButton){
			JOptionPane.showMessageDialog(null, "Currently has no functionality.", "B Button Pushed", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == cButton){
			JOptionPane.showMessageDialog(null, "Currently has no functionality.", "C Button Pushed", JOptionPane.INFORMATION_MESSAGE);
		}
																											// A-F No Functionality
		if (e.getSource() == dButton){
			JOptionPane.showMessageDialog(null, "Currently has no functionality.", "D Button Pushed", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == eButton){
			JOptionPane.showMessageDialog(null, "Currently has no functionality.", "E Button Pushed", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == fButton){
			JOptionPane.showMessageDialog(null, "Currently has no functionality.", "F Button Pushed", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == backButton){
			if (!(ResultPanel.num1Field.getText().length() == 0))
				ResultPanel.num1Field.setText(ResultPanel.num1Field.getText().substring (0, ResultPanel.num1Field.getText().length() - 1));
			if (ResultPanel.num1Field.getText().length() == 0)
				BinaryCalculation.calcBin("0");
			else
				BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == ceButton){
			ResultPanel.num1Field.setText("");
			BinaryCalculation.calcBin("0");
		}
		
		if (e.getSource() == clearButton){
			Calculations.clear();
			ResultPanel.num1Field.setText("");
			BinaryCalculation.calcBin("0");
		}
		
		if (e.getSource() == signButton){
			if (!(ResultPanel.num1Field.getText().length() == 0)){
				ResultPanel.num1Field.setText(Calculations.signChange());
				BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
			}
		}
		
		if (e.getSource() == equalsButton){
			ResultPanel.num1Field.setText(Calculations.equals());
			Calculations.flagReset();
			BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
		}
		
		if (e.getSource() == addButton){
			if (!(ResultPanel.num1Field.getText().length() == 0)){
				Calculations.addition();
				BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
				ResultPanel.num1Field.setText("");
			}
		}
		
		if (e.getSource() == subButton){
			if (!(ResultPanel.num1Field.getText().length() == 0)){
				Calculations.subtraction();
				BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
				ResultPanel.num1Field.setText("");
			}
		}
		
		if (e.getSource() == multButton){
			if (!(ResultPanel.num1Field.getText().length() == 0)){
				Calculations.multiplication();
				BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
				ResultPanel.num1Field.setText("");
			}
		}
	
		if (e.getSource() == divButton){
			if (!(ResultPanel.num1Field.getText().length() == 0)){
				Calculations.division();
				BinaryCalculation.calcBin(ResultPanel.num1Field.getText());
				ResultPanel.num1Field.setText("");
			}
		}
	}
	
	public static void hexSelected(){
		aButton.setEnabled(true);
		bButton.setEnabled(true);
		cButton.setEnabled(true);
		dButton.setEnabled(true);
		eButton.setEnabled(true);
		fButton.setEnabled(true);
		twoButton.setEnabled(true);
		threeButton.setEnabled(true);
		fourButton.setEnabled(true);
		fiveButton.setEnabled(true);
		sixButton.setEnabled(true);
		sevenButton.setEnabled(true);
		eightButton.setEnabled(true);
		nineButton.setEnabled(true);
	}
	
	public static void decSelected(){
		aButton.setEnabled(false);
		bButton.setEnabled(false);
		cButton.setEnabled(false);
		dButton.setEnabled(false);
		eButton.setEnabled(false);
		fButton.setEnabled(false);
		twoButton.setEnabled(true);
		threeButton.setEnabled(true);
		fourButton.setEnabled(true);
		fiveButton.setEnabled(true);
		sixButton.setEnabled(true);
		sevenButton.setEnabled(true);
		eightButton.setEnabled(true);
		nineButton.setEnabled(true);
	}
	
	public static void octSelected(){
		aButton.setEnabled(false);
		bButton.setEnabled(false);
		cButton.setEnabled(false);
		dButton.setEnabled(false);
		eButton.setEnabled(false);
		fButton.setEnabled(false);
		twoButton.setEnabled(true);
		threeButton.setEnabled(true);
		fourButton.setEnabled(true);
		fiveButton.setEnabled(true);
		sixButton.setEnabled(true);
		sevenButton.setEnabled(true);
		eightButton.setEnabled(false);
		nineButton.setEnabled(false);
	}
	
	public static void binSelected(){
		aButton.setEnabled(false);
		bButton.setEnabled(false);
		cButton.setEnabled(false);
		dButton.setEnabled(false);
		eButton.setEnabled(false);
		fButton.setEnabled(false);
		twoButton.setEnabled(false);
		threeButton.setEnabled(false);
		fourButton.setEnabled(false);
		fiveButton.setEnabled(false);
		sixButton.setEnabled(false);
		sevenButton.setEnabled(false);
		eightButton.setEnabled(false);
		nineButton.setEnabled(false);
	}
	
	public static void binAssign(int num, String text){
		if (num == 16)
			bin16.setText(text);
		if (num == 15)
			bin15.setText(text);
		if (num == 14)
			bin14.setText(text);
		if (num == 13)
			bin13.setText(text);
		if (num == 12)
			bin12.setText(text);
		if (num == 11)
			bin11.setText(text);
		if (num == 10)
			bin10.setText(text);
		if (num == 9)
			bin9.setText(text);
		if (num == 8)
			bin8.setText(text);
		if (num == 7)
			bin7.setText(text);
		if (num == 6)
			bin6.setText(text);
		if (num == 5)
			bin5.setText(text);
		if (num == 4)
			bin4.setText(text);
		if (num == 3)
			bin3.setText(text);
		if (num == 2)
			bin2.setText(text);
		if (num == 1)
			bin1.setText(text);
	}
}