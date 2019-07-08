package a2;
//Author: Mohammad Umar Farooq and Joseph Lee

import java.util.ArrayList;
import java.util.List;

public class Inventory implements Observable {
    protected List<Observer> observers;
    protected Product product;
    protected double availableQuantity, backorderedQuantity;

    public Inventory(Product product) {
        this.product = product;
        this.observers = new ArrayList<Observer>();
    }

    protected void updateQuantities(double stock, double backord){
        this.availableQuantity += stock;
        this.backorderedQuantity += backord;
    }
    public double getAvailQty(){
        return availableQuantity;
    }

    public double getBackQty(){
        return backorderedQuantity;
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
        this.notifyObserver();
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : observers){
            observer.update(this.availableQuantity, this.backorderedQuantity);
        }

        //removing shipped orders. going backwards so removing does not affect index values
        //we cannot do this in .update because them we are looping over observers while removing observers.
        for (int i = observers.size() - 1; i >= 0 ; i--){
            if (observers.get(i) instanceof SalesOrder) {
                if (((SalesOrder) observers.get(i)).shipped) {
                    removeObserver(observers.get(i));
                }
            }
        }
    }
    
    public String toString(){
        //321 Stormy Sea, Available: 0.0, Backorders: 0.0
        return (this.product.toString() + ", " + "Available: " +
            Double.toString(availableQuantity) + ", Backorders: "
            + Double.toString(backorderedQuantity));
    }
    

}