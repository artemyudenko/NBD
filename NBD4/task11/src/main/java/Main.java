import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.UpdateValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import pojo.Person;
import pojo.PersonUpdate;
import riak.RiakDbConnector;

import java.util.concurrent.ExecutionException;

public class Main {

    private static final String BUCKET_NAME = "s12676";
    private static final String MY_PERSON = "MyPerson";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Location personLocation = getLocation();
        Person person = getPerson();
        StoreValue personToStoreInDb = getStoreValue(personLocation, person);

        RiakCluster cluster = new RiakDbConnector().setUpCluster();

        RiakClient client = new RiakClient(cluster);
        System.out.println("Client object successfully created");

        storeObject(personToStoreInDb, client);

        Person fetchedObject = getObjectPersonFromDb(personLocation, client);

        updatePersonSetName(personLocation, client, fetchedObject, "NewName");

        getObjectPersonFromDb(personLocation, client);

        deletePerson(personLocation, client);

        getObjectPersonFromDb(personLocation, client);

        cluster.shutdown();
    }

    private static void deletePerson(Location personLocation, RiakClient client) throws ExecutionException, InterruptedException {
        DeleteValue delete = new DeleteValue.Builder(personLocation).build();
        client.execute(delete);
        System.out.println("Object is successfully deleted");
    }

    private static void updatePersonSetName(Location personLocation, RiakClient client, Person fetchedObject, String name) throws ExecutionException, InterruptedException {
        fetchedObject.setName(name);
        PersonUpdate personUpdate = new PersonUpdate(fetchedObject);

        UpdateValue updateValue = new UpdateValue.Builder(personLocation)
                .withUpdate(personUpdate).build();

        client.execute(updateValue);
        System.out.println("Object is successfully updated");
    }

    private static void storeObject(StoreValue personToStoreInDb, RiakClient client) throws ExecutionException, InterruptedException {
        client.execute(personToStoreInDb);
        System.out.println("Object storage operation successfully completed");
    }

    private static Person getObjectPersonFromDb(Location personLocation, RiakClient client) throws ExecutionException, InterruptedException {
        FetchValue fetchOp = new FetchValue.Builder(personLocation)
                .build();
        FetchValue.Response execute = client.execute(fetchOp);
        Person fetchedObject = execute.getValue(Person.class);

        System.out.println();
        System.out.println("Object is successfully loaded from DB");
        System.out.println(execute);
        if (fetchedObject == null) {
            System.out.println("There is no such an object in the db");
        }
        System.out.println(fetchedObject);
        System.out.println();

        return fetchedObject;
    }

    private static StoreValue getStoreValue(Location quoteObjectLocation, Person person) {
        StoreValue personToStoreInDb = new StoreValue.Builder(person)
                .withLocation(quoteObjectLocation)
                .build();
        System.out.println("StoreValue operation created");
        return personToStoreInDb;
    }

    private static Person getPerson() {
        return new Person("Artem", "Yudenko", true, 22);
    }

    private static Location getLocation() {
        Namespace quotesBucket = new Namespace(BUCKET_NAME);

        Location quoteObjectLocation = new Location(quotesBucket, MY_PERSON);
        System.out.println("Location object created for person object");
        return quoteObjectLocation;
    }

}
