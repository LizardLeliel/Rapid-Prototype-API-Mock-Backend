public class SchemaFileSaver extends FileSaver<Schema> {

    protected String getDeterminant(Schema schema) {
        return schema.getName();
    }

    protected String getSaveDirectory() {
        return "schemas";
    }
}
