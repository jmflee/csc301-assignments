package a2;
//Author: Mohammad Umar Farooq and Joseph Lee

public interface Observable {
	
	// Your code for the Observable interface goes here
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();

}
