import java.util.Scanner;

/*
 *  A menu for editing a specific field on a schema.
 */
public class SchemaFieldEditMenu implements MenuNode {
        private Scanner scanner = new Scanner(System.in);
        private Schema schema;

        // Once the program returns, schemaField will be used to update the schema
        private SchemaField schemaField;

        public SchemaFieldEditMenu(Schema schema, SchemaField schemaFieldToEdit) {
            // Create a copy so returning without saving still won't put the schema in a stealth updated
            //  state. One save an exit, the data here will be copied to the field in the Schema. Schema
            //  edit menu will still need to select "save and exit" for changes to be permanent.
            this.schemaField = new SchemaField(
                    schemaFieldToEdit.getLabel(),
                    schemaFieldToEdit.getDescription(),
                    schemaFieldToEdit.getType(),
                    schemaFieldToEdit.getRequired()
            );

            this.schema = schema;
        }

        // Changing the label will be implemented once we change the implementation so fields use
        //  an integer ID to distinguish each other instead of name..
        public void listOptions() {
            System.out.println("Editing field " + this.schemaField.getLabel() + " on Schema "
                    + this.schema.getId() + " (" + this.schema.getName() + ")");
            System.out.println("1. Save and Return");
            System.out.println("2. Print field info");
            System.out.println("3. Change Description");
            System.out.println("4. Change Type");
            System.out.println("5. Toggle required status");
            System.out.println("6. Return without saving");
        }

        public MenuNode processInput() {
            MenuNode nextMenu = null;
            Boolean complete = false;

            while (!complete) {
                this.listOptions();

                String input = this.scanner.nextLine();
                switch (input) {
                    case "1":
                        this.schema.updateField(
                                this.schemaField.getLabel(),
                                this.schemaField.getDescription(),
                                this.schemaField.getType(),
                                this.schemaField.getRequired()
                        );
                        complete = true;
                        break;
                    case "2":
                        System.out.println(
                                "Label: " + this.schemaField.getLabel() + " (Type: " + this.schemaField.getType() + ", "
                                        + (this.schemaField.getRequired()? "": "not " + "required)")
                                + "\n Description: " + this.schemaField.getDescription()
                        );
                        break;
                    case "3":
                        System.out.println("Please type a new description:");
                        String newDescription = this.scanner.nextLine();
                        this.schemaField.setDescription(newDescription);
                        break;
                    case "4":
                        // Go to type submenu
                        complete = true;
                        nextMenu = new SchemaFieldTypeEditMenu(this.schemaField);
                        break;
                    case "5":
                        this.schemaField.setRequired(!this.schemaField.getRequired());
                        System.out.println("This field is now " + (this.schemaField.getRequired()? "": "not ") + "required.");
                        break;
                    case "6":
                        System.out.println("Discarding changes.");
                        complete = true;
                        break;
                    default:
                        System.out.println("Invalid command.");
                }
            }

            return nextMenu;
        }

}
