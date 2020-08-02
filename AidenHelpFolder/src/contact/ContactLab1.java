package contact;

import javax.swing.JOptionPane;

public class ContactLab1 { 

	public static String phoneSummary(Contact x) {
		String summary = "";
		summary = summary + x.getFirstName() + " " + x.getLastName();
		summary = summary + "\nHome: " + x.getHomePhoneNum();
		summary = summary + "\nMobile: " + x.getMobilePhoneNum();
		return summary;
	}

	public static String addressLabel(Contact x) {

		String summary = "";
		summary = summary + x.getFirstName() + " " + x.getLastName();
		summary = summary + "\n" + x.getStreetAddress();
		summary = summary + "\n" + x.getCityAddress() + ", " + x.getStateAddress() + " " + x.getZipCode();
		return summary;

	}

	public static void main(String args[]) {
		Contact person1;
		Contact person2;
		Contact person3;

		String input;
		String fName;
		String lName;

		// you must use (and re-use) only the 6 variables listed above.

		fName = JOptionPane.showInputDialog("Enter the first name for contact #1");
		lName = JOptionPane.showInputDialog("Enter the last name for contact #1");

		person1 = new Contact(fName, lName); // Make SURE you understand this line!!!!

		input = JOptionPane.showInputDialog("Enter the home phone number for " + person1.getFirstName());
		person1.changeHomePhoneNum(input);

		input = JOptionPane.showInputDialog("Enter the mobile phone number for " + person1.getFirstName());
		person1.changeMobilePhoneNum(input);
		JOptionPane.showMessageDialog(null, phoneSummary(person1));
		//uncomment the following line and see if you can figure out what it does...
		//JOptionPane.showMessageDialog(null,person1);
		//This displays the object reference variable   -->  Alan Wang Told me    (he helped me, he told me to put this in) 
		
		//second contact
		fName = JOptionPane.showInputDialog("Enter the first name for contact #2");
		lName = JOptionPane.showInputDialog("Enter the last name for contact #2");
		person2 = new Contact(fName, lName); 
		input = JOptionPane.showInputDialog("Enter the street address where " + person2.getFirstName() + " lives");
		person2.changeStreetAddress(input); 
		input = JOptionPane.showInputDialog("Enter the city where " + person2.getFirstName() + " lives");
		person2.changeCityAddress(input);
		input = JOptionPane.showInputDialog("Enter the state where " + person2.getFirstName() + " lives");
		person2.changeStateAddress(input);
		input = JOptionPane.showInputDialog("Enter the zip code where " + person2.getFirstName() + " lives");
		person2.changeZipCode(input);
		JOptionPane.showMessageDialog(null, addressLabel(person2));
		
		
		//third contact
		fName = JOptionPane.showInputDialog("Enter the first name for contact #3");
		lName = JOptionPane.showInputDialog("Enter the last name for contact #3");
		person3 = new Contact(fName, lName); 
		if(person1.getFirstName().equals(person3.getFirstName()) == false) {
			if(person1.getLastName().equals(person3.getLastName()) == false) {
				JOptionPane.showMessageDialog(null, "Contacts 1 and 3 are not the same");
			}
			else {
				input = JOptionPane.showInputDialog("Enter the street address where " + person3.getFirstName() + " lives");
				person3.changeStreetAddress(input); 
				input = JOptionPane.showInputDialog("Enter the city where " + person3.getFirstName() + " lives");
				person3.changeCityAddress(input);
				input = JOptionPane.showInputDialog("Enter the state where " + person3.getFirstName() + " lives");
				person3.changeStateAddress(input);
				input = JOptionPane.showInputDialog("Enter the zip code where " + person3.getFirstName() + " lives");
				person3.changeZipCode(input);
				JOptionPane.showMessageDialog(null, addressLabel(person3));
			}
		}
		else {
			input = JOptionPane.showInputDialog("Enter the street address where " + person3.getFirstName() + " lives");
			person3.changeStreetAddress(input); 
			input = JOptionPane.showInputDialog("Enter the city where " + person3.getFirstName() + " lives");
			person3.changeCityAddress(input);
			input = JOptionPane.showInputDialog("Enter the state where " + person3.getFirstName() + " lives");
			person3.changeStateAddress(input);
			input = JOptionPane.showInputDialog("Enter the zip code where " + person3.getFirstName() + " lives");
			person3.changeZipCode(input);
			JOptionPane.showMessageDialog(null, addressLabel(person3));
		}
	}

}