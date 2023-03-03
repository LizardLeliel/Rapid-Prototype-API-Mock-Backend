import java.util.ArrayList;
import java.util.Scanner;

public class SchemaEditMenu implements MenuNode {
    private Scanner scanner = new Scanner(System.in);
    private Schema schema;

    private DataSaver<Schema> dataSaver;

    public SchemaEditMenu(DataSaver<Schema> dataSaver, Schema schemaToEdit) {
        this.dataSaver = dataSaver;
        this.schema = schemaToEdit;
    }

    public void listOptions() {
        System.out.println("1. Save and Return");
        System.out.println("2. Change Name");
        System.out.println("3. Change Description");
        System.out.println("4. List fields");
        System.out.println("5. Edit Field");
        System.out.println("6. Return without saving");
    }

    public MenuNode processInput() {
        boolean complete = false;
        MenuNode nextNode = null;

        while (!complete) {
            this.listOptions();

            String input = this.scanner.nextLine();
            switch (input) {
                case "1":
                    // Todo: Exception handle
                    this.dataSaver.save(this.schema);
                    complete = true;
                    nextNode = null;
                    break;
                case "2":
                    // Todo: description name constraints and reforce it.
                    System.out.println("Please type a new name:");
                    String newName = this.scanner.nextLine();
                    this.schema.setName(newName);
                    break;
                case "3":
                    // Todo: how to allow users to reset description to nothing?
                    System.out.println("Please type a new description:");
                    String newDescription = this.scanner.nextLine();
                    this.schema.setDescription(newDescription);
                    break;
                case "4":
                    for (SchemaField field : this.schema.getFields()) {
                        System.out.println(field.getLabel());
                    }
                    break;
                case "5":
                    System.out.println("Unsupported");
                    break;
                case "6":
                    complete = true;
                    nextNode = null;
                default:
                    System.out.println("Invalid Option");
                    // Print "invalid option", ask to try again.
            }
        }

        return nextNode;
    }
}
