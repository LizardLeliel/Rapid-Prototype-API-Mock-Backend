import java.util.ArrayList;
import java.io.Serializable;

/*
 * Data container for "Schemas", information for what data certain objects may have.
 *  (what data can be found in a "table")
 */
public class Schema implements Serializable {
    private String name;
    private String description;
    private ArrayList<SchemaField> fields;

    private final int id;

    public Schema(int id, String name, String description, ArrayList<SchemaField> fields) {
        this.id = id;
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

    public int getId() { return this.id; }

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

    // Could belong somewhere else - this class is intended to be a data container -
    //  but keep it here for convenience.
    @Override
    public String toString() {
        String schemaString = "Name:" + this.name + " (ID: " + Integer.toString(this.id) + ")"
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
