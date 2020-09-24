import javax.swing.JOptionPane;

public class Calculations {
	static int num1 = 0;
	static int num2 = 0;
	static int changeSignNum;
	static String result;
	static String changeSignString;
	static String error;
	static Boolean firstNum = true;
	static int equalsFlag = 0; //1 = add, 2 = sub, 3 = mult, 4 = div, 5 = mod
	static int conversionFlag = 0; // 1 = Hex, 2 = Dec, 3 = Oct, 4 = Bin
	
	public static void addition(){
		num2 = Integer.parseInt(ResultPanel.num1Field.getText());
		if(firstNum){
			num1 = num2;
			firstNum = false;
		}
		else
		{
			num1 = num1 + num2;
		}
		equalsFlag = 1;
		return;
	}
	
	public static void subtraction(){
		num2 = Integer.parseInt(ResultPanel.num1Field.getText());
		if(firstNum){
			num1 = num2;
			firstNum = false;
		}
		else
		{
			num1 = num1 - num2;
		}
		equalsFlag = 2;
		return;
	}
	
	public static void multiplication(){
		num2 = Integer.parseInt(ResultPanel.num1Field.getText());
		if(firstNum){
			num1 = num2;
			firstNum = false;
		}
		else
		{
			num1 = num1 * num2;
		}
		equalsFlag = 3;
		return;
	}
	
	public static void division(){
		num2 = Integer.parseInt(ResultPanel.num1Field.getText());
		if(num2 == 0){
			ResultPanel.num1Field.setText("Error. Cannot divide by zero.");
			clear();
			return;
		}
		if(firstNum){
			num1 = num2;
			firstNum = false;
		}
		else
		{
			num1 = num1 / num2;
		}

		equalsFlag = 4;
		return;
	}
	
	public static void modulus(){
		num2 = Integer.parseInt(ResultPanel.num1Field.getText());
		if(firstNum){
			num1 = num2;
			firstNum = false;
		}
		else																							// Needs fixing
		{
			num1 = num1 % num2;
		}
		equalsFlag = 5;
		return;
	}
	
	public static String equals(){
		if(equalsFlag == 1){
			num2 = Integer.parseInt(ResultPanel.num1Field.getText());
			num1 = num1 + num2;
		}
		if(equalsFlag == 2){
			num2 = Integer.parseInt(ResultPanel.num1Field.getText());
			num1 = num1 - num2;
		}
		if(equalsFlag == 3){
			num2 = Integer.parseInt(ResultPanel.num1Field.getText());
			num1 = num1 * num2;
		}
		if(equalsFlag == 4){
			num2 = Integer.parseInt(ResultPanel.num1Field.getText());
			if(num2 == 0){
				JOptionPane.showMessageDialog(null, "Cannot divide by zero.", "Error", JOptionPane.ERROR_MESSAGE);
				return "0";
			}
			else
			{
				num1 = num1 / num2;
			}
		}
		if(equalsFlag == 5){
			num2 = Integer.parseInt(ResultPanel.num1Field.getText());
			num1 = num1 % num2;																			// Needs fixing
		}
		result = Integer.toString(num1);

		return result;
	}
	
	public static void flagReset(){
		equalsFlag = 0;
		firstNum = true;
	}
	
	public static void clear(){
		num1 = num2 = 0;
		result = Integer.toString(0);
		return;
	}
	
	public static String signChange(){
		changeSignString = ResultPanel.num1Field.getText();
		changeSignNum = Integer.parseInt(changeSignString);
		if(changeSignNum > 0)
			changeSignString = "-" + changeSignString;
		else if(changeSignNum < 0)
			changeSignString = changeSignString.substring(1);
		return changeSignString;
	}
}