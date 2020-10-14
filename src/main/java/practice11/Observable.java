package practice11;

public interface Observable {
    public void registerObserver(Observer observer);
    public void notifyObservers(EventData eventData, Event event);
    public void removeObserver(Observer observer);
}
