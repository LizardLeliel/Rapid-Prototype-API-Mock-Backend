//import java.io.File;
//import java.io.Serializable;
//import java.nio.Files.OutputStream;
import java.io.*;

// As opposed to something that saves to a DB
public abstract class FileSaver<T extends Serializable> implements DataSaver<T> {

    // Will overwrite existing files
    public boolean save(T object) {
        String determinant = this.getDeterminant(object);
        // File newFile = new File("./savedData/schemas/" + this.getDeterminant(object));
        try {
            // Todo: paramterize the directory - don't make it a magic string!
            FileOutputStream file = new FileOutputStream("./savedData/schemas/" + this.getDeterminant(object));
            ObjectOutputStream serialization = new ObjectOutputStream(file);
            serialization.writeObject(object);
            serialization.close();
            file.close();
            return true;
        } catch (Exception e) {
            // Todo: don't eat the exception (this is just here to keep compiler happy until
            //  this is more developed)
            return false;
        }
    }

    public T retrieve(String source) {
        return null;
    }

    public boolean remove(String location) {
        return true;
    }

    public boolean move(String source, String location) {
        return true;
    }

    public String[] queryAll() {
         String[] nothing = {};
         return nothing;

    }

    protected abstract String getDeterminant(T object);
    protected abstract String getSaveDirectory();
}
