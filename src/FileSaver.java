//import java.io.File;
//import java.io.Serializable;
//import java.nio.Files.OutputStream;
import java.io.*;

// As opposed to something that saves to a database
public abstract class FileSaver<T extends Serializable> implements DataSaver<T> {
    private String configurationPath;
    private int runningID;

    public FileSaver() {
        try {
            // Todo: some sort of configuration variable
            // This file keeps tracking of how many unique IDs there are
            final String dataDirectory = "savedData";
            this.configurationPath = "./" + dataDirectory
                    + "/" + this.getSaveDirectory()
                    + "/metadata";



            // Check if the file doesn't exist. If not, create and initialize it.
            File configuration = new File(this.configurationPath);
            if (!configuration.exists()) {
                boolean fileWorked = configuration.createNewFile();
                if (fileWorked) {
                    BufferedWriter initialization = new BufferedWriter (new FileWriter(configuration));
                    initialization.write(Integer.toString(0));
                    initialization.close();
                }
            }

            // Read current highest ID.
            BufferedReader initialIDReader = new BufferedReader((new FileReader(configuration)));
            String _id = initialIDReader.readLine();
            this.runningID = Integer.parseInt(_id);
            initialIDReader.close();

        } catch (Exception e) {
            // Todo: proper exception handling.
        }
    }

    // Will overwrite existing files
    public boolean save(T object) {
        String determinant = this.getDeterminant(object);
        // File newFile = new File("./savedData/schemas/" + this.getDeterminant(object));
        try {
            // Todo: paramaterize the directory - don't make it a magic string!
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

    protected int getNewID() {
        this.runningID++;

        // When the ID is updated, update the configuration file to remember that.
        try {
            BufferedWriter initialization = new BufferedWriter (new FileWriter(this.configurationPath));
            initialization.write(Integer.toString(this.runningID));
            initialization.close();
        } catch (Exception e) {
            return 0;
        }

        return this.runningID;
    }

    protected abstract String getDeterminant(T object);

    protected abstract String getSaveDirectory();
}
