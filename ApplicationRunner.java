public class ApplicationRunner {

    public static void main(String[] args) {

        // Instantiates and makes the gui which starts from
        // the admin page.

        GUI gui = new GUI(750, 480);
        gui.setUpGUI();
        gui.SetUpButtonListeners();

    }
}
