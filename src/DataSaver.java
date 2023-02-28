
/*
 * Represents this data can be stored in permanent storage
 */
public interface DataSaver<T> {

    // Save a specific object to destination. It will force overwrite the object.
    boolean save(T object);

    // Retrieve a specific object from source.
    T retrieve(String source);

    // Remove destination.
    boolean remove(String location);

    boolean move(String source, String destination);

    // Lists all objects of this type that may be retrieved.
    String[] queryAll();
}