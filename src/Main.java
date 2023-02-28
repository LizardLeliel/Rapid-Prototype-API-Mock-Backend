import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Main.testStuff();

    }

    public static void testStuff() {
        // Sample test data
        SchemaField testNameField = new SchemaField("name", "The user's name", "string", true);
        SchemaField testEmailField = new SchemaField("email", "The user's email", "email", false);
        ArrayList<SchemaField> userFields = new ArrayList(Arrays.asList(testNameField, testEmailField));
        Schema testUserSchema = new Schema("Test1", "This is a test schema", userFields);

        System.out.println("Simple Example");
        System.out.println(testUserSchema.toString());

        // Valid changes
        testUserSchema.setName("Profile");
        testUserSchema.setDescription("Profile for our customers");
        SchemaField testUserDescription = new SchemaField("bio", "the user's description", "email", false);
        testUserDescription.setLabel("profile bio");
        testUserDescription.setDescription("The user's biography");
        testUserDescription.setRequired(true);
        testUserDescription.setType("string");

        testUserSchema.addField(testUserDescription);
        // Will do nothing for now; placeholder
        testUserSchema.removeField("email");
        System.out.println("After changes");

        System.out.println(testUserSchema.toString());

        // Todo: test that invalid operations fail.
    }
}