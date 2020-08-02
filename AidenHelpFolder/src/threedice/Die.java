package threedice;

public class Die 
{
	private int sides;
	private int total;
	private int rolls;

    //The following method is called a 'Constructor'
    //it runs when a new 'Die' is created
    
    public Die(int numSides) 
    {
    	sides=numSides;
    	total=0;
    	rolls=0;
    }
    
    
    
    //gets a die roll
    public int roll()
    {
    	int result = (int)(Math.random()*sides+1);
    		// Math.random() returns a random number from 0 to .99999...  
    		// if sides = 6 (a standard die)
    		// Math.random()*sides returns a number from 0 to 5.99999... ,
    		//   adding 1 brings the number from 1 to 6.99999...  ,
    		//   and changing it to an int returns a number from 1,2,3,4,5,6.
    		
    	total = total+result;
    	rolls++;
    		
    	return result;
    }
    
    // The following methods return information about the die
    
    public int getSides()
    {
    	return sides;
    }
    
    public int getTotal()
    {
    	return total;
    }
    
    public int getRolls()
    {
    	return rolls;
    }
    
    
}