import java.util.ArrayList;
import java.util.Scanner;

public class RootMenu implements MenuNode {
    private Scanner scanner = new Scanner(System.in);
    private DataSaver<Schema> dataSaver;

    public RootMenu(DataSaver<Schema> dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void listOptions() {
        System.out.println("Top Menu");
        System.out.println("1. Return");
        System.out.println("2. View Schemas");
        System.out.println("3. Create Schema");
        System.out.println("4. Edit Schema");
        System.out.println("5. Delete Schema");
    }

    public MenuNode processInput() {

        boolean complete = false;
        MenuNode nextNode = null;

        while (!complete) {
            this.listOptions();

            String input = this.scanner.nextLine();
            switch (input) {
                case "1":
                    complete = true;
                    nextNode = null;
                    break;
                case "2":
                    ArrayList<Schema> schemas = this.dataSaver.queryAll();
                    for (Schema schema: schemas) {
                        System.out.println(schema.getId() + ": " + schema.getName());
                    }
                    // View Schemas
                    break;
                case "3":
                    Schema newSchema = this.dataSaver.create();
                    complete = true;
                    nextNode = new SchemaEditMenu(this.dataSaver, newSchema);
                    break;
                case "4":
                    System.out.println("Which schema do you want to edit? (Input its ID)");
                    String schemaID = this.scanner.nextLine();
                    Schema schemaToEdit = this.dataSaver.retrieve(schemaID);
                    complete = true;
                    nextNode = new SchemaEditMenu(this.dataSaver, schemaToEdit);
                    break;
                case "5":
                    System.out.println("Which Schema to delete? (input ID number)");
                    String id = this.scanner.nextLine();
                    // Todo; "invalid input" check
                    this.dataSaver.remove(id);
                    System.out.println("Successful");
                default:
                    // Print "invalid option", ask to try again.
            }

        }

        return nextNode;
    }
}
