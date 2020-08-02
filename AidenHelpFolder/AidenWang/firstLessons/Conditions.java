package firstLessons;
import java.util.*;

import javax.swing.JOptionPane;

public class Conditions {
	public static void main(String args[]) {
		String a = JOptionPane.showInputDialog("what is your name?"); 
		if(a.equals("alan")) System.out.println("your name is alan"); 
		else if(a.equals("aiden")) System.out.println("Seriously it's an honor to meet you");
			
	}
}
