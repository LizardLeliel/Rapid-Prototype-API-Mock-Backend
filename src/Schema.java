import java.util.ArrayList;

public class Schema {
    private String name;
    private String description;
    private ArrayList<SchemaField> fields;

    public Schema(String name, String description, ArrayList<SchemaField> fields) {
        this.name = name;
        this.description = description;

        // todo: can fields be cloned to prevent a previous reference from interfering?
        this.fields = fields;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }

    public ArrayList<SchemaField> getFields() {
        return this.fields;
    }

    // This object does not handle verifying uniqueness of name value. Its DataSaver object will throw exception
    //  if it tries to save a Schema with a conflicting name. The DataSaver object can be used to query uniqueness.
    // Todo: Throw exception if name is invalid. For now, silently fail on empty string.
    public void setName(String newName) {
        if (newName.equals("")) {
            return;
        }
        else {
            this.name = newName;
        }
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void addField(SchemaField newField) {
        // Todo: uniqueness check.
        this.fields.add(newField);
    }

    public boolean removeField(String label) {
        // todo: implement this.
        return true;
    }

    // Todo: how do we simply update a schema field?

    // Could belong somewhere else but keep it here for convenience.
    @Override
    public String toString() {
        String schemaString = "Name:" + this.name 
            + "\n" + "Description: " + this.description
            + "\n" + "Fields:";

        for (SchemaField field : this.fields) {
            // Todo: move this to schemaField's own toString?
            schemaString = schemaString + "\n" + "label: " + field.getLabel()
                    + " --- type: " + field.getType()
                    + " --- required: " + (field.getRequired()? "yes": "no")
                    + "\n" + "description: " + field.getDescription();
        }

        return schemaString;
    }
}
