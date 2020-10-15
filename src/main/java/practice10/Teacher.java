package practice10;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Teacher extends Person {
    private LinkedList<Klass> klasses;
    public Teacher(int id, String name, int age, LinkedList<Klass> klasses) {
        super(id, name, age);
        this.klasses = klasses;
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
                this.klasses.stream().anyMatch(klass -> klass.isIn(student));
    }

    public String introduceWith(Student student) {
        boolean isTeaching = isTeaching(student);
        return  isTeaching ?
                super.introduce() + " I am a Teacher. I teach " + student.getName() + "." :
                super.introduce() + " I am a Teacher. I don\'t teach " + student.getName() + ".";
    }
}
