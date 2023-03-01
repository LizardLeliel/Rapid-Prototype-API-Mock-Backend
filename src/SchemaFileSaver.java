public class SchemaFileSaver extends FileSaver<Schema> {

    public SchemaFileSaver() {
        super();
    }

    protected String getDeterminant(Schema schema) {
        return Integer.toString(schema.getId());
    }

    protected String getSaveDirectory() {
        return "schemas";
    }
}
