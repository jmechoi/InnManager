/**
   Name: Jamie Choi
   Date: 12/10/15
   Course/Section: IT 206.001
   Assignment: Programming Assignment 10
  
   Description:
  
   This data definition subclass (Theatre) is used to define a Guest object. 
   
   Each Theatre object consists of: 
   	- Inherited Guest attributes
    - Any additional shows wanted
    - If premium seating is requested
   	
   Cost Calculation
   	- Theatre package: Flat rate of $150 to see up to three shows
   		- Each additional show (up to 2) costs an additional $25 each
   		- Premium seating for an additional $100
   	
  */
public class Theatre extends Guest {
	// The flat fee for all Theatre guests
	public static final double THEATRE_FEE = 150;
	// The fee for each additional show
	public static final double ADD_FEE = 25;
	// The fee for premium seating
	public static final double PREMIUM_FEE = 100;
	// The maximum number of additional shows
	public static final int MAX_ADD = 2;
	
	// The total number of requests for premium seating
	private static int numPremium = 0;
	
	// Whether the guest wants additional shows or not
	private boolean additional = false;
	// The number of additional shows wanted
	private int numAdditional = 0;
	// Whether the guest wants premium seating
	private boolean premium = false;
	
	// The subtotal and total fees incurred
	private double subtotal = 0;
	private double total = 0;
	
	/*
	   Creates a Theatre object with default values. 
	 */
	public Theatre(){
		this("","","","",0,false,0,false);
	}
	
	/*
	   Creates a Theatre object with passed in values.
	 */
	public Theatre(String name, String emName, String emPhone, String emEmail, int numDays, boolean smoker, int numAdditional, boolean premium){
		this.numAdditional = numAdditional;
		this.premium = premium;
	}
	
	// The accessors for the Theatre object
	public static int getNumPremium(){ return numPremium; }
	public boolean getAdditional(){ return this.additional; }
	public int getNumAdditional(){ return this.numAdditional; }
	public boolean getPremium(){ return this.premium; }
	
	/**
	   Validates and sets if the guest wants additional shows or not.
	   
	   @param moreShows whether the guest wants additional shows or not
	   @param num the number of additional shows requested
	   @return whether the input given is valid or not
	 */
	public boolean setAdditional(String moreShows, int num){
		boolean valid = false;
		if (moreShows.equalsIgnoreCase("Y") && (num <= MAX_ADD || num == 1)){
			this.additional = true;
			this.numAdditional = num;
			valid = true;
		} else if (moreShows.equalsIgnoreCase("N")){
			this.additional = false;
			this.numAdditional = 0;
			valid = true;
		} else {
			valid = false;
		}
		
		return valid;
	}
	
	/**
	   Validates and sets whether the guest wants premium seating or not.
	   
	   @param input whether the guest wants premium seating or not
	   @return whether the input given is valid or not
	 */
	public boolean setPremium(String input){
		boolean valid = false;
		if (input.equalsIgnoreCase("Y")){
			this.premium = true;
			numPremium++;
			valid = true;
		} else if (input.equalsIgnoreCase("N")){
			this.premium = false;
			valid = true;
		} else {
			valid = false;
		}
		
		return valid;
	}
	
	// Calculates the subtotal fee (without the smoker surcharge)
	public double calculateSubtotal(){
		this.subtotal = (Guest.FLAT_RATE * this.getNumDays()) + THEATRE_FEE;
		if (this.additional){
			subtotal += (ADD_FEE * this.numAdditional);
		}
		if (this.premium){
			subtotal += PREMIUM_FEE;
		}
		
		return subtotal;
	}
	
	// Calculates the total fee (with the smoker surcharge)
	public double calculateTotal(){
		if (this.getSmoker()){
			this.total = subtotal + (subtotal * Guest.SMOKER_CHARGE);
		} else {
			this.total = subtotal;
		}
		
		return total;
	}
	
	// Outputs a string representation of the Theatre object.
	public String toString(){
		String output = "";
		
		output += super.toString() + "\nAdditional Shows: " + this.numAdditional +
				"\nPremium? " + this.premium +
				"\nSubtotal: $" + this.calculateSubtotal() + 
				"\nTotal: $" + this.calculateTotal();
		
		return output;
	}
}
