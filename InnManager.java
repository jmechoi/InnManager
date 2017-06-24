/**
   Name: Jamie Choi
   Date: 12/10/15
   Course/Section: IT 206.001
   Assignment: Programming Assignment 10
  
   Description:
  
   This program supports the GMU Innovation Inn that houses guests with different activity
   packages. This application will prompt the user to enter all guest details and the print 
   out each guest's summary after the guest has been entered. After all guests have been 
   entered, a summary report of all guests will be output. Each guest will receive their own, 
   unique, generated ID when added to the system. The user will enter the guest's name, 
   emergency contact information (name, phone number, email), the number of days the guest 
   will be staying, and if the guest is a smoker or non-smoker. If the guest is a smoker, 
   there is a surcharge of 10% of all fees. If the guest chooses the Sports package, the user 
   will be prompted to enter any equipment rented, if applicable. If the guest chooses the 
   Academic package, the user must enter if the guest would like printed materials. If the 
   guest chooses the Theatre package, the user must enter if the guest would like any 
   additional shows, how many shows, and also if the guest would like premium seating.
  
   A max of 555 guests can be entered.
   
   Each Guest has:
   	- A unique generated ID
   	- Guest Name
   	- Emergency Contact Information:
   		- Name
   		- Phone Number       (xxx) xxx-xxxx
   		- Email Address      email@domain.com
   	- Number of days staying at inn
   	- Smoker or not a smoker
   
   A Sports guest additionally has:
   	- List of equipment rented
   		- Equipment Name
   		- Equipment cost per hour
   		- Equipment rented hours
   
   An Academic guest additionally has: 
   	- Whether the guest wants printed materials or not
   	
   A Theatre guest additionally has:
    - Any additional shows wanted
    - If premium seating is requested
   	
   The total fee for each guest will be calculated as follows:
   	- Each guest must pay a flat rate of $45 per night at the Inn
   	- Sports package: An additional flat fee of $75 for the facilities fee
   		- Costs for each equipment rented (cost per hour * hours rented)
   	- Academic package: An additional flat fee of $206
   		- Lecture materials printed at an additional cost of $50
   	- Theatre package: Flat rate of $150 to see up to three shows
   		- Each additional show (up to 2) costs an additional $25 each
   		- Premium seating for an additional $100
   	- Smoker surcharge (10% of all other fees)
   	
   Upon entering all details of each guest, a guest summary will print the guest's ID, 
   guest name, total fees incurred, and the total amount of fees incurred with surcharge.
   
   After all guests have been entered, a summary report will print how many guests stayed 
   at the inn, the total amount of all fees collected, the average fee collected per guest, 
   number of sports equipment rented, number of guests who requested printed materials, 
   and the number of guests requesting premium seating.
   
  */
import javax.swing.JOptionPane;
public class InnManager {
	public static void main(String[] args) {
		// The max number of guests that can be entered
		final int MAX_GUESTS = 555;
		
		Guest[] guests = new Guest[MAX_GUESTS];
		int count = 0;
		
		// Prompt user to add a guest until indicated otherwise
		while(JOptionPane.showConfirmDialog(null, "Would you like to add a guest?") == JOptionPane.YES_OPTION){
			if (count < MAX_GUESTS){
				
				// Adds a guest to the Inn
				addGuest(guests, count);
				
				// Prints each guest's summary report
				printGuestSummary(guests, count);
				count++;
			} else {
				JOptionPane.showMessageDialog(null,"Error! You have reached the max number of guests you can enter.");
				break;
			}
		}
		
		// Prints the Inn's summary report upon exit
		printEndReport();
	}
	
	/**
	   Prompt the user for the guest's activity package (Sports, Academic, or Theatre) until 
	   a valid package is chosen. Continually prompt the user for the guest's name, emergency 
	   contact information (name, phone number, email address), number of days staying, and 
	   if the guest is a smoker or not until valid input is given. Prompt for individual 
	   activity details, if applicable, until valid input is given.
	   @param guests the list of guests staying at the inn
	   @param count the number of guests added
	 */
	public static void addGuest(Guest[] guests, int count){
		boolean validActivity = false;
		
		// Prompt for the guest's activity package type until a valid option is chosen
		do{
			String activity = JOptionPane.showInputDialog("Which activity package for the guest?\n"
					+ "[1] Sports\n"
					+ "[2] Academic\n"
					+ "[3] Theatre\n");
			if (activity.equalsIgnoreCase("1") || activity.equalsIgnoreCase("Sports")){
				guests[count] = new Sports();
				validActivity = true;
			} else if (activity.equalsIgnoreCase("2") || activity.equalsIgnoreCase("Academic")){
				guests[count] = new Academic();
				validActivity = true;
			} else if (activity.equalsIgnoreCase("3") || activity.equalsIgnoreCase("Theatre")) {
				guests[count] = new Theatre();
				validActivity = true;
			} else {
				JOptionPane.showMessageDialog(null, "Error. Please choose a valid option.");
			}
			
		}while(!validActivity);
		
		String guestName;
		boolean validGName = false;
		// Prompt for the guest's name until a valid name is entered
		do { 
			guestName = JOptionPane.showInputDialog("Guest Name? ");
			if (guests[count].setName(guestName)){
				validGName = true;
			} else {
				JOptionPane.showMessageDialog(null, "Error. You must enter a valid name.");
			}
		}while(!validGName);
		
		// Prompt for the guest's emergency contact's name until a valid name is given
		String eName;
		boolean validEName = false;
		
		do{
			eName = JOptionPane.showInputDialog("Guest's emergency contact name? ");
			if (guests[count].setEmName(eName)){
				validEName = true;
			} else {
				JOptionPane.showMessageDialog(null, "Error. You must enter a valid emergency contact name.");
			}
		}while(!validEName);
		
		// Prompt for the guest's emergency contact's phone number in the format (xxx) xxx-xxxx
		String phone;
		boolean validPhone = false;
		
		do{
			phone = JOptionPane.showInputDialog("Guest's emergency contact phone number in the format (xxx) xxx-xxxx ");
			if (guests[count].setEmPhone(phone)){
				validPhone = true;
			} else {
				JOptionPane.showMessageDialog(null, "Error. You must enter a valid emergency contact phone number in the format (xxx) xxx-xxxx");
			}
		}while(!validPhone);
		
		// Prompt for the guest's emergency contact's email in the format email@domain.com
		String email;
		boolean validEmail = false;
		
		do {
			email = JOptionPane.showInputDialog("Guest's emergency contact email in the format email@domain.com");
			if (guests[count].setEmEmail(email)){
				validEmail = true;
			} else {
				JOptionPane.showMessageDialog(null, "Error. Please enter a valid email address in the format email@domain.com");
			}
		}while(!validEmail);
		
		// Prompt for the number of days staying until a valid number is given
		boolean validNum = false;
		
		do {
			try{
				validNum = guests[count].setNumDays(JOptionPane.showInputDialog("How many days will the guest be staying?"));	
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "You did not enter a number. Try again.");
			}
			catch (IllegalArgumentException e){
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}while(!validNum);
		
		// Prompt for whether the guest is a smoker or not until a valid input is given
		String smoker;
		boolean validSmoker = false;
		
		do {
			smoker = JOptionPane.showInputDialog("Is the Guest a smoker? Y or N?");
			if (guests[count].setSmoker(smoker)){
				validSmoker = true;
			} else {
				JOptionPane.showMessageDialog(null, "Error. You must enter a valid input: Y or N?");
			}
		}while(!validSmoker);
		
		
		// Prompts for the following details if Sports activity package was chosen
		if (guests[count] instanceof Sports){
			
			// Prompt for guest's rented equipment
			while(JOptionPane.showConfirmDialog(null, "Add rented Equipment? ") == JOptionPane.YES_OPTION){
				Equipment equip = new Equipment();
				
				// Prompt for the equipment's name until a valid name is given
				String equipName;
				boolean validEquipName = false;
				
				do {
					equipName = JOptionPane.showInputDialog("What is the equipment's name?");
					if (equip.setName(equipName)){
						validEquipName = true;
					} else {
						JOptionPane.showMessageDialog(null, "Error. Please enter a valid equipment name.");
					}
				}while(!validEquipName);
				
				// Prompt for equipment's cost per hour until a valid cost is given
				boolean costHr = false;
				
				do {
					try{
						costHr = equip.setCostHr(JOptionPane.showInputDialog("What is the equipment's cost per hour?"));
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Error. You must enter a number.");
					}
					catch(IllegalArgumentException e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}while(!costHr);
				
				// Prompt for equipment's rented hours (1-10) until a valid number is given
				boolean rentHrs = false;
				
				do {
					try{
						rentHrs = equip.setNumHrs(JOptionPane.showInputDialog("How many hours will the equipment be rented?"));
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Error. You must enter a number");
					}
					catch(IllegalArgumentException e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}while(!rentHrs);
				
				// Add equipment to guest's equipment rented list (0-9 pieces) if space remains
				boolean addEquip = false;
				
				do {
					if(((Sports)guests[count]).addEquipment(equip)){
						addEquip = true;
					} else {
						JOptionPane.showMessageDialog(null, "You cannot add anymore equipment.");
						break;
					}
				}while(!addEquip);
			}
		}
		
		// Prompts for the following if guest chooses the Academic activity package
		if (guests[count] instanceof Academic){
			
			// Prompt if guest wants printed materials until a valid input is given
			String print;
			boolean validPrint = false;
			
			do{
				print = JOptionPane.showInputDialog("Would the guest like printed materials? Y or N?");
				if (((Academic)guests[count]).setPrint(print)){
					validPrint = true;
				}else {
					JOptionPane.showMessageDialog(null, "Please enter Y or N.");
				}
			}while(!validPrint);
		}
		
		// Prompts for the following if the guest chooses the Theatre activity package
		if (guests[count] instanceof Theatre){
			
			// Prompt guest for any additional shows (1-2) until valid choice is made
			String moreShows;
			boolean shows = false;
			int num = 0;
			
			do {
				moreShows = JOptionPane.showInputDialog("Would the guest like to add additional shows? Y or N?");
				if (((Theatre)guests[count]).getAdditional()){
				num = Integer.parseInt(JOptionPane.showInputDialog("How many additional shows (1-2)?")); }
				
				if (((Theatre)guests[count]).setAdditional(moreShows,num)){
					shows = true;
				}else {
					JOptionPane.showMessageDialog(null, "Please enter valid input (Y or N) or (1-2)");
				}
			}while(!shows);
			
			// Prompt guest for premium seating until a valid input is given
			String premiumSeats;
			boolean premium = false;
			
			do{
				premiumSeats = JOptionPane.showInputDialog("Would the guest like premium seats? Y or N?");
				
				if(((Theatre)guests[count]).setPremium(premiumSeats)){
					premium = true;
				}else {
					JOptionPane.showMessageDialog(null, "Please enter Y or N.");
				}
			}while(!premium);
		}
	}
	
	/**
	   Output a guest summary report for each guest entered with the following details: guest ID, 
	   guest name, guest's emergency contact information (name, phone, email), number of days 
	   staying, whether the guest is a smoker or not, any other activity-applicable details, and
	   also the subotal and total fees incurred.
	   @param guests the list of guests staying at the Inn
	   @param count the number of guests added
	 */
	public static void printGuestSummary(Guest[] guests, int count){
		String guestSummary = "************* Guest Summary *************\n\n";
		
		guestSummary += guests[count].toString();
		
		JOptionPane.showMessageDialog(null, guestSummary);
		guests[count].setTotalFees();
	}
	
	/**
	   Output a summary report of all guests staying at the Inn with the following details: 
	   Total amount of fees collected, average fee collected by each guest, the number of 
	   equipment rented, the number of guests requesting printed materials, and the number 
	   of guests requesting premium seating. 
	 */
	public static void printEndReport(){
		String summaryReport = "************* Summary Report *************\n\n";
		
		summaryReport += "Total Number of Guests: " + Guest.getNumGuests() +
				"\nTotal Fees Collected: $" + Guest.getTotalFees() +
				"\nAverage Fee Collected: $" + Guest.getAverage() + 
				"\nPieces of Equipment Rented: " + Sports.getNumEquip() + 
				"\nGuests with Printed Materials: " + Academic.getNumPrint() + 
				"\nPremium Seating Requests: " + Theatre.getNumPremium();
		
		JOptionPane.showMessageDialog(null, summaryReport);
	}
}
