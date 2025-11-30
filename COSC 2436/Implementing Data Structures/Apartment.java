public class Apartment implements Comparable<Apartment> {

    //private for no one can access directly
    private String streetAddress, apartmentNumber;
    private double monthlyRent;
    private int numBedrooms;

    //constructor
    public Apartment(String streetAddress, String apartmentNumber,
                        double monthlyRent, int numBedrooms) { 
        this.streetAddress = streetAddress; //save street address 
        this.apartmentNumber = apartmentNumber; //save apartment number 
        this.monthlyRent = monthlyRent; //save in monthly rent 
        this.numBedrooms = numBedrooms; //save number of bedrooms 
    }

    //-------------------------------------------------------------------------------------
    //!!METHODS!!
    //compareTo method to compare apartments by rent value
    public int compareTo(Apartment other) {
        return Double.compare(this.monthlyRent, other.monthlyRent);
    }

    //toString method to display apartment details
    public String toString() {
        return "Address: " + streetAddress +
                ", Apt: " + apartmentNumber +
                ", Bedrooms: " + numBedrooms +
                ", Rent: $" + String.format("%.2f", monthlyRent);
    }
}