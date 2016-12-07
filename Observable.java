/**
 * makes a class observable
 * @author Gurdev Singh
 * @version 0.1
 */
public interface Observable
{
    void notifyObservers();
    void addObserver(Observer o);
   
}

