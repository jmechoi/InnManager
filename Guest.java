/**
   Name: Jamie Choi
   Date: 12/10/15
   Course/Section: IT 206.001
   Assignment: Programming Assignment 10
  
   Description:
  
   This (abstract) data definition superclass (Guest) is used to define a Guest object which 
   is extended by the Sports, Academic, and Theatre classes (activity packages).
   
   Each Guest object consists of: 
   	- A unique generated ID
   	- Guest Name
   	- Emergency Contact Information:
   		- Name
   		- Phone Number       (xxx) xxx-xxxx
   		- Email Address      email@domain.com
   	- Number of days staying at inn
   	- Smoker or not a smoker
   	
   There are three types of Guests:
   	- Sports activity
   	- Academic activity
   	- Theatre activity
   	
   	The subtotal and total fees incurred will be calculated in its respective subclasses.
   	 
   	 The following static variable(s) are used to keep track of attributes that are 
   	 applicable to all Server objects:
   	  - idBase: Holds the letter used as part of the unique ID number
   	  - idTracker: Holds the last generated ID number to generate a new, unique one
   	  - numGuests: Keeps track of how many Guest objects have been created
   	  - totalFees: Keeps track of the total fees incurred across all guests
   	  
  */
public abstract class Guest {
	// The surcharge for a guest who smokes
	public static final double SMOKER_CHARGE = .10;
	// The flat rate for all guests
	public static final double FLAT_RATE = 45;
	
	// The trackers to create unique IDs for each guest
	private static String idBase = "G";
	private static int idTracker = 100000;
	
	// The total number of guests created
	private static int numGuests = 0;
	// The total number of fees incurred across all guests
	private static double totalFees = 0;
	
	// The attributes that make up every Guest object
	private String id = "";
	private String name = "";
	private String emName = "";
	private String emPhone = "";
	private String emEmail = "";
	private int numDays = 0;
	private boolean smoker = false;
	
	/*
	   Creates a Guest object and sets default values
	 */
	public Guest(){
		this("","","","",0,false);
	}
	
	/*
	   Creates a Guest object with passed in values, generates a unique ID, and increments the 
	   number of guests created.
	 */
	public Guest(String name, String emName, String emPhone, String emEmail, int numDays, boolean smoker){
		this.name = name;
		this.emName = emName;
		this.emPhone = emPhone;
		this.emEmail = emEmail;
		this.numDays = numDays;
		this.smoker = smoker;
		this.setID();
		numGuests++;
	}
	
	// The accessors for the Guest object
	public String getID(){ return this.id; }
	public String getName(){ return this.name; }
	public String getEmName(){ return this.emName; }
	public String getEmPhone(){ return this.emPhone; }
	public String getEmEmail(){ return this.emEmail; }
	public int getNumDays() { return this.numDays; }
	public boolean getSmoker() { return this.smoker; }
	public static int getNumGuests() { return numGuests; }
	public static double getTotalFees() { return totalFees; }
	public static double getAverage() { return totalFees/numGuests; }
	
	// Adds each guest's total fees to a total across all guests
	public void setTotalFees(){
		totalFees += calculateTotal();
	}
	
	// Generates a unique ID for each guest
	public void setID(){
		if (numGuests == 0){
			this.id = idBase + idTracker;
		}else {
			idTracker++;
			this.id = idBase + idTracker;
		}
	}
	
	/**
	   Validates and sets the name of the guest.
	   
	   @param name the guest's name
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
	   Validates and sets the name of the emergency contact.
	   
	   @param name the emergency's contact name
	   @return whether the name is valid or not
	 */
	public boolean setEmName(String name){
		if (name!= null && !name.equals("")){
			this.emName = name;
			return true;
		}else {
			return false;
		}
	}
	
	/**
	   Validates and sets the emergency contact's phone number.
	   
	   @param phone the phone number
	   @return whether the phone number is valid or not
	 */
	public boolean setEmPhone(String phone){
			if (phone.length() != 14){
				return false;
			} else if (phone.charAt(0) != '(' || phone.charAt(4) != ')'){
				return false;
			} else if (phone.charAt(5) != ' ' || phone.charAt(9) != '-'){
				return false;
			} else if (!Character.isDigit(phone.charAt(1)) || !Character.isDigit(phone.charAt(2))|| !Character.isDigit(phone.charAt(3))){
				return false;
			} else if (!Character.isDigit(phone.charAt(6)) || !Character.isDigit(phone.charAt(7))|| !Character.isDigit(phone.charAt(8))){
				return false;
			} else if (!Character.isDigit(phone.charAt(10)) || !Character.isDigit(phone.charAt(11))|| !Character.isDigit(phone.charAt(12)) || !Character.isDigit(phone.charAt(13))){
				return false;
			} else {
				this.emPhone = phone;
				return true;
			}
	}
	
	/**
	   Validates and sets the emergency contact's email address.
	   
	   @param email the email address
	   @return whether the email address is valid or not
	 */
	public boolean setEmEmail(String email){
		boolean validEmail = false;
		
		int positionA = email.indexOf('@');
		int posA = positionA;
		int positionB = email.indexOf('.');
		int posB = positionB;
		
		if (positionA == (email.length() - 1)){
			validEmail = false;
			return false;
		}
		
		if (positionB == (email.length() - 1)){
			validEmail = false;
			return false;
		}
		
		if (positionA > 0){
			
			while (positionA < email.length() && !validEmail) {
				if (email.charAt(positionA + 1) != '@' || email.charAt(positionA + 1) != '.'){
					validEmail = true;
					positionA++;
				} else {
					validEmail = false;
					return false;
				}
			}
		} else {
			validEmail = false;
			return false;
		}
		
		if (positionB > 0){
			do{
				if (email.charAt(posB + 1) !=  '.' || email.charAt(posB + 1) != '@'){
					validEmail = true;
					positionB++;
				} else {
					validEmail = false;
					return false;
				}
			}while (!validEmail && positionB < email.length());
		} else {
			validEmail = false;
			return false;
		}
		
		if (posA > posB){
			validEmail = false;
			return false;
		}
		
		if (Character.isDigit(email.charAt(posA - 1)) || Character.isLetter(email.charAt(posA - 1))){
			validEmail = true;
		} else {
			validEmail = false;
			return false;
		}
		
		if ((Character.isDigit(email.charAt(posB - 1)) || (Character.isLetter(email.charAt(posB - 1)) && email.charAt(posB - 1) != '@'))){
			validEmail = true;
		} else {
			validEmail = false;
			return false;
		}
		
		if (Character.isDigit(email.charAt(posB + 1)) || Character.isLetter(email.charAt(posB + 1))){
			validEmail = true;
			this.emEmail = email;
		} else {
			validEmail = false;
		}
		
		return validEmail;
	}
	
	/**
	   Validates and sets the number of days the guest is staying.
	   
	   @param days the number of days the guest is staying
	   @return whether the number is valid or not
	 */
	public boolean setNumDays(String days){
		try {
			setNumDays(Integer.parseInt(days));
			return true;
		} catch (NumberFormatException e){
			throw e;
		}
	}
	
	public boolean setNumDays(int days){
		if (days > 0){
			this.numDays = days;
			return true;
		} else {
			throw new IllegalArgumentException("Number of days must be positive");
		}
	}
	
	/**
	   Validates and sets whether the guest is a smoker or not
	   @param input the input by the user of whether the guest is a smoker or not
	   @return whether the input was valid or not
	 */
	public boolean setSmoker(String input){
		boolean validInput = false;
		
		if (input.equalsIgnoreCase("Y")){
			validInput = true;
			this.smoker = true;
		} else if (input.equalsIgnoreCase("N")){
			validInput = true;
			this.smoker = false;
		} else {
			validInput = false;
		}
		
		return validInput;
	}
	
	// Abstract method to calculate the subtotal (without the smoker surcharge)
	public abstract double calculateSubtotal();
	
	// Abstract method to calculate the total (with the smoker surcharge)
	public abstract double calculateTotal();
	
	// Outputs a string representation of the Guest object
	public String toString(){
		String guest = "";
		
		guest = "Guest ID: " + this.id + 
				"\nGuest Name: " + this.name + 
				"\n\nEmergency Contact Information: " + 
				"\n- Name: " + this.emName + 
				"\n- Phone Number: " + this.emPhone + 
				"\n- E-mail: " + this.emEmail + 
				"\n\nNumber of Days Staying: " + this.numDays + 
				"\nSmoker? " + this.smoker + 
				"\nActivity Package: " + getClass().getName() + "\n";
		return guest;
	}
	
}
