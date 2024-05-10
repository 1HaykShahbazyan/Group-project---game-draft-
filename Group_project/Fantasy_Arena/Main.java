package Fantasy_Arena;


import javax.swing.SwingUtilities;

import Fantasy_Arena.cli.GameConsole;
import Fantasy_Arena.ui.GameMenu;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("-cli")) {
            GameConsole console = new GameConsole();
            console.run();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GameMenu();
                }
            });
        }
    }
}