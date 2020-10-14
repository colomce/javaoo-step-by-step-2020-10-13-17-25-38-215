package practice11;

public class KlassEventDataWrapper implements EventData {
    private Student student;
    private Klass klass;

    public KlassEventDataWrapper(Student student, Klass klass) {
        this.student = student;
        this.klass = klass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }
}
