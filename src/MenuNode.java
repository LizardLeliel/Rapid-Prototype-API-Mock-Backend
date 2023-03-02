/*
 * A menu node, which will display options for users and process various inputs.
 * Will very likely be refactored.
 */
public interface MenuNode {
    // Todo: Just make this a string?
    public void listOptions();
    // Return true if going back a menu, return false if staying in this menu
    public MenuNode processInput();
}
