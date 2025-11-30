import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;

public class ApartmentsLinkedList {

    public static void main(String[] args) {
        LinkedList<Apartment> apartments = new LinkedList<>(); //linked list to store apartments
        Scanner input = new Scanner(System.in); //scanner for user input

        //variables
        int totalApts = 0; //total apartments to be entered

        System.out.println("Roberto's Apartment Listings");
        System.out.println("\nINSTRUCTIONS: \n* Please provide the following details for each apartment:");
        System.out.println("  - Street address\n  - Apartment number\n  - Monthly rent amount\n  - Number of bedrooms\n");
        System.out.println("* You will first be asked how many apartments you want to enter.\n");

        //ask how many apartments user wants to enter (.trim() to remove extra spaces)
        System.out.print("How many apartments would you like to enter? ");
        String countText = input.nextLine().trim();

        /*
        using try-catch to handle invalid input for number of apartments
        if invalid, default to 1 apartment
        if zero or negative, set to 0 so no apartments are added
        no infinite loop
        */
        try {
            totalApts = Integer.parseInt(countText); //parse to integer
            if (totalApts <= 0) { //only positive numbers allowed
                System.out.println("*****ATENTION: Number must be greater than 0. No apartments will be entered.*****");
                totalApts = 0; //so the for loop will not run
            }
        } catch (NumberFormatException e) { //catch invalid input
            System.out.println("*****ATENTION: Invalid input. Defaulting to 1 apartment.*****");
            totalApts = 1; //default value if user enters something not numeric
        }

        //in case user entered 1 or more apartments
        //for loop to add the specified number of apartments
        for (int i = 1; i <= totalApts; i++) {
            System.out.println("\n==================================================");
            System.out.println("Entering information for apartment #" + i);
            System.out.println("==================================================");

            //call method to add apartment
            addApartment(apartments, input);
        }

        //verifcation in case apartments were not added
        if (apartments.isEmpty()) {
            System.out.println("\nNo apartments were entered.\n");
        } else { //display sorted apartments
            Collections.sort(apartments); //collection.sort to sort by rent

            //final output
            System.out.println("\n=======================================================================================");
            System.out.println("\nTotal Apartments Listed: " + apartments.size()); //display total apartments
            System.out.println("\nApartments Sorted by Monthly Rent (Low to High):\n");
            for (Apartment apt : apartments) { 
                System.out.println(apt); //enhanced for loop to display apartments
            }
            System.out.println("\n=======================================================================================\n");
        }

        //close scanner
        input.close();
    }
    
    //----------------------------------------------------------------------------------------------------------
    //!!METHODS!!

    //method to add apartment to the linked list
    private static void addApartment(LinkedList<Apartment> apartments, Scanner input) {

        //variables
        double rent;
        int bedrooms;
        String aptNum, street, rentText, bedText;

        System.out.print("\nStreet address: "); //ask for street address
        street = input.nextLine().trim();

        System.out.print("Apartment number: ");  //ask for apartment number
        aptNum = input.nextLine().trim();

        //no infinite loop
        System.out.print("Monthly rent amount: "); //ask for monthly rent
        rentText = input.nextLine().trim();
        try {
            rent = Double.parseDouble(rentText); //parse to double
            if (rent < 0) { //only positive rent allowed
                System.out.println("*****ATENTION: Please enter a POSITIVE amount. Apartment will NOT be added.*****");
                return; //stop method, do not add apartment
            }
        } catch (NumberFormatException e) { //catch invalid input
            System.out.println("*****ATENTION: Invalid input. Apartment will NOT be added (rent must be a number).*****");
            return; //stop method, do not add apartment
        }

        //no infinite loop
        System.out.print("Number of bedrooms: "); //ask for number of bedrooms
        bedText = input.nextLine().trim();
        try {
            bedrooms = Integer.parseInt(bedText); //parse to integer
            if (bedrooms < 0) { //only non-negative bedrooms allowed
                System.out.println("*****ATENTION: Please enter a POSITIVE number of bedrooms. Apartment will NOT be added.*****");
                return; //stop method, do not add apartment
            }
        } catch (NumberFormatException e) { //catch invalid input
            System.out.println("*****ATENTION: Invalid input. Apartment will NOT be added (bedrooms must be a whole number).*****");
            return; //stop method, do not add apartment
        }
        
        //create new apartment object and add to linked list
        Apartment apts = new Apartment(street, aptNum, rent, bedrooms);
        apartments.add(apts);

        //confirmation for the apartment was added
        System.out.println("\n!!! Apartment listed. !!!");
    }
}