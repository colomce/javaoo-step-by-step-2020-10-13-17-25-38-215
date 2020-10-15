package practice11;

import java.util.ArrayList;
import java.util.List;

import static practice11.KlassEvents.NEW_APPENDED_STUDENT;
import static practice11.KlassEvents.NEW_LEADER;

public class Klass implements Observable {
    private int number;
    private Student leader;
    private List<Observer> observerList;

    public Klass(int number) {
        this.number = number;
        observerList = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDisplayName() {
        return "Class " + this.number;
    }

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public void assignLeader(Student student) {
        if(this.number != student.getKlass().getNumber()) {
            System.out.print("It is not one of us.\n");
        } else {
            this.leader = student;
            notifyObservers(new KlassEventDataWrapper(student, this), NEW_LEADER);
        }
    }

    public void appendMember(Student student) {
        student.setKlass(this);
        notifyObservers(new KlassEventDataWrapper(student, this), NEW_APPENDED_STUDENT);
    }

    public boolean isIn(Student student) {
        return student.getKlass().number == this.number;
    }

    @Override
    public void registerObserver(Observer observer) {
        if(observer != null) {
            this.observerList.add(observer);
        }
    }

    @Override
    public void notifyObservers(EventData eventData, Event event) {
        this.observerList.forEach(observer -> {
            observer.update(eventData, event);
        });
    }

    @Override
    public void removeObserver(Observer observer) {
        if(observer != null ){
            this.observerList.remove(observer);
        }
    }
}
