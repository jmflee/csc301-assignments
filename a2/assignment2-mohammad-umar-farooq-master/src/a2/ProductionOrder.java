package a2;
//Author: Mohammad Umar Farooq and Joseph Lee

public class ProductionOrder implements Observer, DisplayElement {
    private static int orderSequence;
    protected int ID;
    protected double minQuantity;
    protected Observable inventory;

    public ProductionOrder(int minQty, Observable inventory) {
        orderSequence++;
        this.ID = orderSequence;
        this.minQuantity = minQty;
        this.inventory = inventory;
        //always register so we can see when the inventory needs more produced
        this.inventory.registerObserver(this);
    }

    @Override
    public void display(double q) {
    	System.out.println("Production Order# " + Integer.toString(ID) + ", item " + ((Inventory)inventory).product.name + ", produced " + Double.toString(q));
    }

    @Override
    public void update(double availQty, double orderQty) {
        //update inventory quantities if backQty >= minQty
        double backQty = ((Inventory)inventory).getBackQty();
        double avail = ((Inventory)inventory).getAvailQty();
        if(backQty >= this.minQuantity){
                ((Inventory)inventory).updateQuantities(backQty, -backQty);
                display(backQty);
        }
    }

    public String toString(){
        return ("[PO" + Integer.toString(ID) + " " + ((Inventory)inventory).product.name + " " + Double.toString(minQuantity) + "]");
    }
}
