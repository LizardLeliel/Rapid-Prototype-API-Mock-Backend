import java.io.*;
import java.util.ArrayList;

/*
 * An abstract class implementing the DataSaver interface, using a simple filesystem to
 *  permanently store the data (as opposed to a database).
 */
public abstract class FileSaver<T extends Serializable> implements DataSaver<T> {
    private String configurationPath;
    private int runningID;

    public FileSaver() {
        try {
            // Todo: some sort of configuration variable
            // This file keeps tracking of how many unique IDs there are
            final String dataDirectory = "savedData";
            this.configurationPath = "./" + Main.SAVE_DATA_DIRECTORY
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

    public T create() {
        return this.createNew(this.getNewID());
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
        T object;
        try {
            FileInputStream objectFile = new FileInputStream(this.getFullSaveDirectoryPath() + source);
            ObjectInputStream deserializer = new ObjectInputStream(objectFile);

            object = (T) deserializer.readObject();
            deserializer.close();
            return object;
        } catch (Exception e) {

        }
        return null;
    }

    public boolean remove(String location) {
        File targetFile = new File(this.getFullSaveDirectoryPath() + location);
        return targetFile.delete();
    }

    public boolean move(String source, String location) {
        // This method is left unimplemented
        return false;
    }

    public ArrayList<T> queryAll() {
        File saveDirectory = new File(this.getFullSaveDirectoryPath());
        File[] files = saveDirectory.listFiles();
        ArrayList<T> items = new ArrayList<T>();

        for (int i = 0; i < files.length; ++i) {
            String fileName = files[i].getName();
            if (!fileName.equals("metadata")) {
                items.add(this.retrieve(fileName));
            }
        }
        return items;
    }

    protected int getNewID() {
        this.runningID++;

        // When the ID is updated, update the configuration file so it'll remember that.
        try {
            BufferedWriter initialization = new BufferedWriter(new FileWriter(this.configurationPath));
            initialization.write(Integer.toString(this.runningID));
            initialization.close();
        } catch (Exception e) {
            System.out.println("ERROR!");
            return 0;
        }

        return this.runningID;
    }

    protected abstract String getDeterminant(T object);

    protected abstract String getSaveDirectory();

    protected abstract T createNew(int newID);

    private String getFullSaveDirectoryPath() {
        return this.configurationPath = "./" + Main.SAVE_DATA_DIRECTORY
                + "/" + this.getSaveDirectory() + "/";
    }
}
