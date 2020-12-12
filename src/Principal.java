import Database.CreateDb;
import Database.DatabaseConnection;
import GraphicInterface.Window;


public class Principal {
    public static void main(String[] args) {
        DatabaseConnection.makeJDBCConnection();
        CreateDb.CreateDb();

        Window window = new Window("Gerenciamento da Liga Pokemon",100, 100, 1200, 500);

    }
}
