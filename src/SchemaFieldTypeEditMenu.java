import java.util.Scanner;

public class SchemaFieldTypeEditMenu implements MenuNode {
    private Scanner scanner = new Scanner(System.in);
    private SchemaField schemaField;

    private String candidateType;

    public SchemaFieldTypeEditMenu(SchemaField schemaField) {
        this.schemaField = schemaField;
        this.candidateType = schemaField.getType();
    }

    public void listOptions() {
        System.out.println("1. Save and return");
        System.out.println("2. List current type");
        System.out.println("3. Set to string");
        System.out.println("4. Set to number");
        System.out.println("5. Set to boolean");
        System.out.println("6. Set to email");
        System.out.println("7. Reset changes and return");
    }

    @Override
    public MenuNode processInput() {
        while (true) {
            this.listOptions();

            String input = this.scanner.nextLine();
            switch (input) {
                case "1":
                    this.schemaField.setType(this.candidateType);
                    return null;
                case "2":
                    System.out.println(this.candidateType + " (was " + this.schemaField.getType() + ")");
                    break;
                case "3":
                    this.candidateType = "string";
                    break;
                case "4":
                    this.candidateType = "number";
                    break;
                case "5":
                    this.candidateType = "boolean";
                    break;
                case "6":
                    this.candidateType = "email";
                    break;
                case "7":
                    return null;
            }
        }
    }
}
