import java.util.Stack;

public class MenuController {
    private Stack<MenuNode> menuStack = new Stack<MenuNode>();

    public MenuController(MenuNode initialMenu) {
        menuStack.push(initialMenu);
    }

    // Note: run may not be called twice on an instance.
    // Todo: either throw exception if called twice, or do something better.
    public void run() {
        while (!this.menuStack.isEmpty()) {
            MenuNode result = menuStack.peek().processInput();

            if (result != null) {
                this.menuStack.push(result);
            } else {
                this.menuStack.pop();
            }
        }
    }
}
