package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection javaTestConn = null;
    private static PreparedStatement prepareStm = null;
    private static final String DB_USER = "postgres";
    private static final String DB_USER_PASS = "docker";
    private static Connection conn;

    public static void makeJDBCConnection(){
        try{
            String url = "jdbc:postgresql://localhost/liga_pokemon";
            Properties props = new Properties();
            props.setProperty("user",DB_USER);
            props.setProperty("password",DB_USER_PASS);
            conn = DriverManager.getConnection(url, props);
            if(conn != null)
                System.out.println("Conexão realizada com sucesso!");
            else
                System.out.println("Conexão falhou!");
        } catch (SQLException e){
            System.out.println("Conexão Postgres falhou!");
            e.printStackTrace();
            return;
        }
    }
    public static void executeQuery(String instrucaoSQL) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(instrucaoSQL);
            st.close();
        }catch (Exception e) {

        }
    }
    public static ResultSet selectValues(String instrucaoSQL) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(instrucaoSQL);
            //st.close();
            return rs;
        }catch (Exception e) {

        }
        return null;
    }
}
