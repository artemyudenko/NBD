package pojo;

import com.basho.riak.client.api.commands.kv.UpdateValue;

public class PersonUpdate extends UpdateValue.Update<Person> {

    private final Person person;

    public PersonUpdate(Person person) {
        this.person = person;
    }

    @Override
    public Person apply(Person p) {
        if (p == null) {
            return new Person();
        }
        return new Person(person.getName(), person.getSurname(), person.getStudent(), person.getAge());
    }
}
