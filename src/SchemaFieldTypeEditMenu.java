import java.util.Scanner;

/*
 * A menu for editing the type on a schema field. Very small menu.
 */
public class SchemaFieldTypeEditMenu implements MenuNode {
    private Scanner scanner = new Scanner(System.in);
    private SchemaField schemaField;

    private String candidateType;

    public SchemaFieldTypeEditMenu(SchemaField schemaField) {
        this.schemaField = schemaField;
        this.candidateType = schemaField.getType();
    }

    public void listOptions() {
        System.out.println("Setting " + this.schemaField.getLabel() + " type. (Currently " + this.schemaField.getType() + ")");
        System.out.println("1. Set to string and return");
        System.out.println("2. Set to number and return");
        System.out.println("3. Set to boolean and return");
        System.out.println("4. Set to email and return");
        System.out.println("5. Cancel");
    }

    @Override
    public MenuNode processInput() {
        while (true) {
            this.listOptions();

            String input = this.scanner.nextLine();
            switch (input) {
                case "1":
                    this.schemaField.setType("string");
                    return null;
                case "2":
                    this.schemaField.setType("number");
                    return null;
                case "3":
                    this.schemaField.setType("boolean");
                    return null;
                case "4":
                    this.schemaField.setType("email");
                    return null;
                case "5":
                    return null;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
