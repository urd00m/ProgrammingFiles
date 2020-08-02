package StringLab; 
/**
 * 
 * Name:
 * Date:
 * Got help from:
 */

import javax.swing.JOptionPane;

public class StringLab1  
{

    public static void main(String args[])
    {
    	String bigString = "abcdefghijklmnopqrstuvwxyz";   //This may be changed to ANYTHING
    	
    	int strLen=0;
    	int bigLen=0;
    	int loc=0;
    	String before="";
    	String after="";
    	
    	String strInput = JOptionPane.showInputDialog("Enter a string");
    	
    	
    	strLen = strInput.length(); 
    	
    	System.out.println("The string '"+strInput+"' has "+strLen+" characters.");
    	
    	bigLen = bigString.length(); 
    		
    	System.out.println("The string '"+bigString+"' has "+bigLen+" characters.");
    	
    	loc = bigString.indexOf(strInput); 
    	
    	if(loc==-1)
    		System.out.println(strInput+" does not appear in '"+bigString+"'.");	
    	else
    	{
    		System.out.println(strInput+" begins at position "+loc+" in '"+bigString+"'.");
    		
    		before = bigString.substring(0, loc); 
    		
    		System.out.println("The letters before "+strInput+" are '"+before+"'.");
    		
    		after = bigString.substring(loc+strLen, bigLen); 
    		
    		System.out.println("The letters after "+strInput+" are '"+after+"'.");
    		System.out.println("Put them together and you have '"+before+after+"'.");
    	
    	}	
    	
    	
    }
    
    
}