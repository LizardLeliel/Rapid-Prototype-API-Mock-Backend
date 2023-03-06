/*
 * A menu node, which will display options for users and process various inputs.
 * Will very likely be refactored.
 */
public interface MenuNode {
    // Return null if going back a menu, return a menu if changing menus.
    public MenuNode processInput();
}
