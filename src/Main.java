public class Main {
    public static final String SAVE_DATA_DIRECTORY = "savedData";

    public static void main(String[] args) {

        // Main.testStuff();
        SchemaFileSaver schemaFileSaver = new SchemaFileSaver();
        MenuController menuController = new MenuController(new RootMenu(schemaFileSaver));
        menuController.run();
    }

    public static void testStuff() {
        // Run any stuff here for the purpose of testing.
        // TODO: DELETE THIS
    }
}