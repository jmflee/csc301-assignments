package a2;
//Author: Mohammad Umar Farooq and Joseph Lee

public class SalesOrder implements Observer, DisplayElement {
    private static int orderSequence;
	protected int ID;
	protected Customer customer;
	protected double quantity;
	protected Observable inventory;
	protected boolean shipped;
	
    public SalesOrder(Customer customer, double quantity, Observable inventory) {
        orderSequence++;
        this.ID = orderSequence;
    	this.customer = customer;
    	this.quantity = quantity;
    	this.inventory = inventory;
    	//if have enough avail in inventory, ship immediately, otherwise register as observer
        //so we can observe the inventory and see when there is enough available
        if(((Inventory)inventory).getAvailQty() >= this.quantity){
            this.ship(this.quantity);
        }
        else{
            ((Inventory)inventory).updateQuantities(0,quantity);
            this.inventory.registerObserver(this);
        }
    }

    @Override
    public void display(double q) {
        System.out.println("Shipping Order# " + Integer.toString(ID) + " to " + customer.name + ", Product: " + ((Inventory)inventory).product.name + ", Quantity " + Double.toString(quantity));
    }

    @Override
    public void update(double availQty, double orderQty) {
        //ship iff the order if availQty >= orderQty
        if(((Inventory)inventory).getAvailQty() >= this.quantity){
            ((Inventory)inventory).updateQuantities(-this.quantity, 0);
            this.shipped = this.ship(0);
        }
    }

    private boolean ship(double availableQuantity){
        this.display(0);
        return true;
    }

    public String toString(){
        return ("Shipping Order# " + Integer.toString(ID) + " to " + customer.name + ", Product: " + ((Inventory)inventory).product.name + ", Ordered quantity " + Double.toString(quantity));
    }

}