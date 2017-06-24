/**
   Name: Jamie Choi
   Date: 12/10/15
   Course/Section: IT 206.001
   Assignment: Programming Assignment 10
  
   Description:
  
   This data definition subclass (Sports) is used to define a Guest object. 
   
   Each Sports object consists of: 
   	- Inherited Guest attributes
   	- List of equipment rented
   		- Equipment Name
   		- Equipment cost per hour
   		- Equipment rented hours
   	
   Cost Calculation
   - Sports package: An additional flat fee of $75 for the facilities fee
   		- Costs for each equipment rented (cost per hour * hours rented)
   	
  */
public class Sports extends Guest{
	// The flat fee for Sports activity guests for facility use
	public static final double FACILITIES_FEE = 75;
	// The max and min number of equipment that can be rented
	public static final int MAX_EQUIP = 9;
	public static final int MIN_EQUIP = 0;
	
	// The total number of equipment rented by guests
	private static int numEquip = 0;
	
	// The list of equipments rented by each guest
	private Equipment[] equipment;
	// The number of equipment rented by each guest
	private int equipCount = 0;
	// The subtotal and total fees incurred
	private double subtotal = 0;
	private double total = 0;
	
	/*
	   Creates a Sports object with default values.
	 */
	public Sports(){
		this("","","","",0,false,null);
	}
	
	/*
	   Creates a Sports object with passed in values
	 */
	public Sports(String name, String emName, String emPhone, String emEmail, int numDays, boolean smoker, Equipment[] equipment){
		super(name, emName, emPhone, emEmail, numDays, smoker);
		this.equipment = new Equipment[MAX_EQUIP];
	}
	
	// Accessors for the Sports object
	public Equipment getEquipment(int i){ return this.equipment[i]; }
	public double getTotal(){ return this.total; }
	public double getSubtotal(){ return this.subtotal; }
	public static int getNumEquip(){ return numEquip; }
	
	/**
	   Validates and sets the pieces of equipment rented for each guest.
	   
	   @param equip the piece of equipment rented
	   @return whether the equipment has been added or not
	 */
	public boolean addEquipment(Equipment equip){
		if (equipCount < MAX_EQUIP){
			equipment[equipCount] = equip;
			equipCount++;
			numEquip++;
			return true;
		} else {
			return false;
		}
	}
	
	// Calculates the subtotal (without the smoker surcharge)
	public double calculateSubtotal(){
		this.subtotal = (Guest.FLAT_RATE * this.getNumDays()) + FACILITIES_FEE;
		for (int i = 0; i < equipCount; i++){
			this.subtotal += (equipment[i].calculateCost());
		}
		
		return subtotal;
	}
	
	// Calculates the total (with the smoker surcharge)
	public double calculateTotal(){
		if (this.getSmoker()){
			this.total = subtotal + (subtotal * Guest.SMOKER_CHARGE);
		} else {
			this.total = subtotal;
		}
		return total;
	}
	
	// Outputs a string representation of a Sports object
	public String toString(){
		String output = "";
		
		output += super.toString() + "\nEquipment: \n";
		
		for (int i = 0; i < equipCount; i++){
			output += this.equipment[i].toString() + "\n";
		}
		
		output += "\nSubtotal: $" + this.calculateSubtotal();
		output += "\nTotal: $" + this.calculateTotal();
		
		return output;
	}
}
