public class SchemaField {
    private String label;
    private String description;
    // Todo: use enums for below
    private String type;
    private boolean required;

    public SchemaField(String label, String description, String type, boolean required) {
        this.label = label;
        this.description = description;
        this.type = type;
        this.required = required;
    }

    public String getLabel() { return this.label; }
    public String getDescription() { return this.description; }
    public String getType() { return this.type; }
    public boolean getRequired() { return this.required; }

    // This method does not insure the label is unique. The class "Schema", which contains
    //  the schema fields related to itself, will handle that validation.
    //Todo: implement exception for empty string. For now, fail silently.
    //Todo: more robust validation. Match the website. It should contain a non-whitespace character.
    public void setLabel(String newLabel) {
        if (newLabel.equals("")) {
            return;
        }
        else {
            this.label = newLabel;
        }
    }

    public void setDescription(String newDescription) { this.description = newDescription; }

    // Todo: implement enums. For now, fail silently if newType isn't valid.
    public void setType(String newType) {
        switch (newType) {
            case "number":
            case "string":
            case "boolean":
            case "email":
                this.type = newType;
                break;
            default:
                return;
        }
        return;
    }

    public void setRequired(boolean newRequired) {
        this.required = newRequired;
    }
}