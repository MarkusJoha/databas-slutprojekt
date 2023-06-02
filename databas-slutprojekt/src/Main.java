import DatabaseConnection.TableCreator;
import views.MenuView;

public class Main {
    public static void main(String[] args) {
        TableCreator.createTables();
        MenuView.displayLoginMenu();
    }
}
