import java.util.ArrayList;

/*
 * Represents the methods needed for a class to be able to save and retrieve
 *  information, regardless of implementation.
 */
public interface DataSaver<T> {

    T create();

    // Save a specific object to destination. It will force overwrite the object.
    boolean save(T object);

    // Retrieve a specific object from source.
    T retrieve(String source);

    // Remove destination.
    boolean remove(String location);

    // Move the location of data. (todo: Necessary?)
    boolean move(String source, String destination);

    // Lists all objects of this type that may be retrieved.
    public ArrayList<T> queryAll();
}