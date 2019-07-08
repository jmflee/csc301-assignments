package a2;
//Author: Mohammad Umar Farooq and Joseph Lee

public class Product {
    protected int ID;
    protected String name;

    public Product(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    
    public String toString(){
        return (Integer.toString(ID) + " " + name);
    }

}