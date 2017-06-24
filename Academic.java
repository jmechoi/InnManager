/**
   Name: Jamie Choi
   Date: 12/10/15
   Course/Section: IT 206.001
   Assignment: Programming Assignment 10
  
   Description:
  
   This data definition subclass (Academic) is used to define a Guest object. 
   
   Each Academic object consists of: 
   	- Inherited Guest attributes
   	- Whether the guest wants printed materials or not
   	
   Cost Calculation
   - Academic package: An additional flat fee of $206
   		- Lecture materials printed at an additional cost of $50)
   	
  */
public class Academic extends Guest {
	// The flat fee for all academic guests
	public static final double ACADEMIC_FEE = 206;
	// The fee for printed materials
	public static final double PRINT_FEE = 50;
	
	// The number of guests requesting printed materials
	private static int numPrint = 0;
	
	// Attributes that make up an Academic guest
	private boolean printedMaterials = false;
	private double subtotal = 0;
	private double total = 0;
	
	/*
	   Creates an Academic object with default values.
	 */
	public Academic(){
		this("","","","",0,false,false);
	}
	
	/*
	   Creates an Academic object with passed in values.
	 */
	public Academic(String name, String emName, String emPhone, String emEmail, int numDays, boolean smoker, boolean printed){
		super(name, emName, emPhone, emEmail, numDays, smoker);
		this.printedMaterials = printed;
	}
	
	// The accessors for the Academic object
	public static int getNumPrint(){ return numPrint; }
	public double getSubtotal(){ return this.subtotal; }
	public double getTotal(){ return this.total; }
	public boolean getPrinted(){ return this.printedMaterials; }
	
	/**
	   Validates and sets whether the guest wants printed materials or not.
	   
	   @param input whether the guest wants printed materials or not
	   @return whether the given input is valid or not
	 */
	public boolean setPrint(String input){
		if (input.equalsIgnoreCase("Y")){
			this.printedMaterials = true;
			numPrint++;
			return true;
		} else if (input.equalsIgnoreCase("N")){
			this.printedMaterials = false;
			return true;
		} else {
			return false;
		}
	}
	
	// Calculates the subtotal fee (without the smoker surcharge)
	public double calculateSubtotal(){
		this.subtotal = (Guest.FLAT_RATE * this.getNumDays()) + ACADEMIC_FEE;
		if (printedMaterials){
			this.subtotal += PRINT_FEE;
		}
		
		return subtotal;
	}
	
	// Calculates the total fees incurred (with the smoker surcharge)
	public double calculateTotal() {
		if (this.getSmoker()){
			this.total = subtotal + (subtotal * Guest.SMOKER_CHARGE);
		} else {
			this.total = subtotal;
		}
		return total;
	}
	
	// Outputs a string representation of the Academic object
	public String toString(){
		String output = "";
		
		output += super.toString() + "\nPrinted Materials? " + this.printedMaterials;
		output += "\nSubtotal: $" + this.calculateSubtotal();
		output += "\nTotal: $" + this.calculateTotal();
		
		return output;
	}
}
