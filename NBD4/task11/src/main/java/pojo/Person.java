package pojo;

public class Person {

    private String name;
    private String surname;
    private Boolean student;
    private Integer age;

    public Person(String name, String surname, Boolean student, Integer age) {
        this.name = name;
        this.surname = surname;
        this.student = student;
        this.age = age;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    Boolean getStudent() {
        return student;
    }

    Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    Person() {
    }

    @Override
    public String toString() {
        return "Person : " +
                "name '" + name + '\'' +
                ", surname '" + surname + '\'' +
                ", student " + student +
                ", age " + age +
                '}';
    }
}
