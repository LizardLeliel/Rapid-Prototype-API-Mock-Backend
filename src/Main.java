import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        // Sample test schema Fields
        SchemaField testNameField = new SchemaField("name", "The user's name", "string", true);
        SchemaField testEmailField = new SchemaField("email", "The user's email", "email", false);

        List<SchemaField> userFields = Arrays.asList(testNameField, testEmailField);

        // Sample test data
        Schema testUserSchema = new Schema("Test1", "This is a test schema", userFields);

        System.out.println(testUserSchema.toString());
    }
}