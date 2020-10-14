package practice11;

import java.util.LinkedList;
import java.util.stream.Collectors;

import static practice11.KlassEvents.NEW_APPENDED_STUDENT;
import static practice11.KlassEvents.NEW_LEADER;

public class Teacher extends Person implements Observer {
    private LinkedList<Klass> klasses;
    public Teacher(int id, String name, int age, LinkedList<Klass> klasses) {
        super(id, name, age);
        this.klasses = klasses;
        registerAsClassObserver();
    }

    private void registerAsClassObserver() {
        this.klasses.stream().forEach(klass -> klass.registerObserver(this));
    }

    public Teacher(int id, String name, int age) {
        super(id, name, age);
    }

    public LinkedList<Klass> getClasses() {
        return klasses;
    }

    public void setClasses(LinkedList<Klass> klasses) {
        this.klasses = klasses;
    }

    public String introduce() {
        return this.klasses == null || this.klasses.size() == 0 ?
                super.introduce() + " I am a Teacher. I teach No Class." :
                super.introduce() + " I am a Teacher. I teach Class " +
                        this.klasses.stream()
                                .map(Klass::getNumber)
                                .map(String::valueOf)
                                .collect(Collectors.joining(", ")) + ".";
    }

    public boolean isTeaching(Student student) {
        return this.klasses != null && this.klasses.size() != 0 &&
                this.klasses.stream()
                        .map(Klass::getNumber)
                        .anyMatch(klass -> klass.equals(student.getKlass().getNumber()));
    }

    public String introduceWith(Student student) {
        boolean isTeaching = isTeaching(student);
        return  isTeaching ?
                super.introduce() + " I am a Teacher. I teach " + student.getName() + "." :
                super.introduce() + " I am a Teacher. I don\'t teach " + student.getName() + ".";
    }

    @Override
    public void update(Object data, Event event) {
        if(data instanceof KlassEventDataWrapper) {
            KlassEventDataWrapper klassEventDataWrapper = (KlassEventDataWrapper)data;
            Student student = klassEventDataWrapper.getStudent();
            Klass klass = klassEventDataWrapper.getKlass();
            switch ((KlassEvents)event) {
                case NEW_APPENDED_STUDENT:
                    System.out.print("I am " + this.getName() + ". I know " + student.getName() +" has joined "+ klass.getDisplayName()+ ".\n");
                    break;

                case NEW_LEADER:
                    System.out.print("I am "+ this.getName() +". I know " + student.getName() +" become Leader of "+ klass.getDisplayName() + ".\n");
                    break;
            }
        }
    }
}
