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
        System.out.println("2. Show Schema info");
        System.out.println("3. Change Name");
        System.out.println("4. Change Description");
        System.out.println("5. List fields");
        System.out.println("6. Create new field");
        System.out.println("7. Edit Field");
        System.out.println("8. Delete field");
        System.out.println("9. Return without saving");
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
                    System.out.println("Unimplemented.");
                    break;
                case "3":
                    // Todo: description name constraints and reforce it.
                    System.out.println("Please type a new name:");
                    String newName = this.scanner.nextLine();
                    this.schema.setName(newName);
                    break;
                case "4":
                    // Todo: how to allow users to reset description to nothing?
                    System.out.println("Please type a new description:");
                    String newDescription = this.scanner.nextLine();
                    this.schema.setDescription(newDescription);
                    break;
                case "5":
                    for (SchemaField field : this.schema.getFields()) {
                        System.out.println(field.getLabel());
                    }
                    break;
                case "6":
                    System.out.println("Enter name for field (must be unique):");
                    System.out.println("Note: this will create an empty field that will retain on the Schema "
                        + "even if you discard changes after editing the new field. To remove the field, "
                        + "use option 8.");
                    String newFieldLabel = this.scanner.nextLine();
                    SchemaField newField = new SchemaField(
                            newFieldLabel,
                            "",
                            "",
                            false
                    );
                    this.schema.addField(newField);

                    complete = true;
                    nextNode = new SchemaFieldEditMenu(schema, newField);
                    break;
                case "7":
                    System.out.println("Which field do you want to edit? (input label)");
                    String label = this.scanner.nextLine();
                    SchemaField field = this.schema.getSchema(label);
                    if (field == null) {
                        System.out.println("Could not find field " + label + " on schema.");
                    }
                    else {
                        complete = true;
                        nextNode = new SchemaFieldEditMenu(this.schema, field);
                    }
                    break;
                case "8":
                    System.out.println("Unimplemented");
                    break;
                case "9":
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
