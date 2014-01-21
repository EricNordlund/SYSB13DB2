package Controller;

import View.MainWindow;

/**
 * Initiates the controller and GUI.
 *
 * @author G14
 */
public class Main {

    public static void main(String[] args) {

        MainWindow view = new MainWindow();
        Controller controller = new Controller();
        view.setController(controller);
        view.resultSetToTable(null, true); //Loads the initial table in the GUI

        view.setVisible(true);

    }
}
