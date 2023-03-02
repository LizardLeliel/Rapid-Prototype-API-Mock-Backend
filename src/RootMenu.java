import java.util.ArrayList;
import java.util.Scanner;

public class RootMenu implements MenuNode {
    private Scanner scanner = new Scanner(System.in);
    private DataSaver<Schema> dataSaver;

    public RootMenu(DataSaver<Schema> dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void listOptions() {
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
                    // View Scheams
                    break;
                case "3":
                    // Create new Schema, push
                    // complete = true;
                    // nextNode = new EditSchemaNode(newSchema, dataSaver)
                    break;
                case "4":
                    // Get schema
                    // If schema invalid, tell that to user.
                    // complete = true;
                    // nextNode = new EditSchemaMenua(fetchedSChema, dataSaver)
                    break;
                case "5":
                    // delete node
                default:
                    // Print "invalid option", ask to try again.
            }

        }

        return nextNode;
    }
}
