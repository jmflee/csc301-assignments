package a2;
//Author: Mohammad Umar Farooq and Joseph Lee

public class Customer {
    protected int ID;
    protected String name;

    public Customer(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String toString(){
        return ("Customer with ID: " + Integer.toString(ID) + " and named " + name);
    }
    
}