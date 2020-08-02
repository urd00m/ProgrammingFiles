package threedice;
import javax.swing.JOptionPane;

public class ThreeDice {
	public static void main(String args[]) {
		Die die1 = new Die(4); 
		Die die2 = new Die(5); 
		Die die3 = new Die(6); 
		JOptionPane.showMessageDialog(null, "Press 'OK' to roll 4, 5, and 6 sided dice");
		int roll1 = die1.roll(); 
		int roll2 = die2.roll(); 
		int roll3 = die3.roll();
		JOptionPane.showMessageDialog(null, "You rolled a " + roll1 + " on the 4-sided die," + "\na " + roll2 + " on the 5-sided die," + "\nand a " + roll3 + " on the 6-sided die");
		if(roll1 != roll2 && roll2 != roll3 && roll3 != roll1) { 
			JOptionPane.showMessageDialog(null, "All of them show different numbers.");
		}  
		if( (roll1 == roll2 && roll1 != roll3) || (roll2 == roll3 && roll2 != roll1) || (roll1 == roll3 && roll1 != roll2)) {
			JOptionPane.showMessageDialog(null, "Exactly two of them show the same number.");
		}
		if(roll1 == roll2 && roll1 == roll3) {
			JOptionPane.showMessageDialog(null, "All 3 dice show the same number!");
		}
		
	}
}
