import javax.swing.*;

public class ResultPanel extends JPanel {
	JLabel num1Label, num2Label;
	static JTextField num1Field;
	static JTextField num2Field;
	
	public ResultPanel(){
		num1Field = new JTextField();
		num1Field.setEditable(false);
		num1Field.setHorizontalAlignment(SwingConstants.RIGHT);
		add(num1Field);
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(num1Field))
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(num1Field))
		);
	}
}