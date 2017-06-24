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
public class Equipment {
	// The max and min number of hours an equipment can be rented
	public static final int MAX_HOURS = 10;
	public static final int MIN_HOURS = 1;
	
	// The number of equipment rented out
	private static int equipmentTracker = 0;
	
	// The attributes that make up an Equipment object
	private String name = "";
	private double costPerHour = 0;
	private int numHours = 0;
	
	/*
	   Creates an Equipment object with default values.
	 */
	public Equipment(){
		this("",0,0);
	}
	
	/*
	   Creates an Equipment objects with passed in values and increments the equipment tracker.
	 */
	public Equipment(String name, double cost, int numHours){
		this.name = name;
		this.costPerHour = cost;
		this.numHours = numHours;
		equipmentTracker++;
	}
	
	// The accessors for the Equipment object
	public String getName() { return this.name; }
	public double getCostPerHour() { return this.costPerHour; }
	public int getNumHours() { return this.numHours; }
	public static int getEquipmentTracker(){ return equipmentTracker; }
	
	/**
	   Validates and sets the name of the equipment.
	   
	   @param name the name of the equipment
	   @return whether the name is valid or not
	 */
	public boolean setName(String name){
		if (name != null && !name.equals("")){
			this.name = name;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	   Validates and sets the cost per hour for the equipment.
	    
	   @param cost the cost per hour for each equipment
	   @return whether the cost is valid or not
	 */
	public boolean setCostHr(String cost){
		try{
			setCostHr(Double.parseDouble(cost));
			return true;
		} catch (NumberFormatException e){
			throw e;
		}
	}
	
	public boolean setCostHr(double cost){
		if (cost > 0){
			this.costPerHour = cost;
			return true;
		} else {
			throw new IllegalArgumentException("Cost per hour must be positive");
		}
	}
	
	/**
	   Validates and sets the number of hours rented.
	   
	   @param num the number of hours the equipment is rented
	   @return whether the number is valid or not
	 */
	public boolean setNumHrs(String num){
		try {
			setNumHrs(Integer.parseInt(num));
			return true;
		} catch (NumberFormatException e) {
			throw e;
		}
	}
	
	public boolean setNumHrs(int num){
		if (num >= MIN_HOURS && num <= MAX_HOURS){
			this.numHours = num;
			return true;
		} else {
			throw new IllegalArgumentException("Number of hours must be 1-10");
		}
	}
	
	// Calculates the rent cost of the equipment
	public double calculateCost(){
		return this.costPerHour * this.numHours;
	}
	
	// Outputs a string representation of an Equipment object
	public String toString(){
		String equip = "";
		equip = "- Equipment Name: " + this.name + 
				"\n-- Cost Per Hour: $" + this.costPerHour + 
				"\n-- Hours Rented: " + this.numHours + 
				"\n-- Equipment Cost: $" + this.calculateCost(); 
		
		return equip;
	}
	
}
