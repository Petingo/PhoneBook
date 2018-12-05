import java.sql.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

public class PhoneBookModelSQL extends PhoneBookModel {

    private String FILENAME = "jdbc:sqlite:../database.db";
    private String CREATE_SQL = "CREATE TABLE IF NOT EXISTS PhoneBook"    +
                                "(ID       INTEGER PRIMARY KEY AUTOINCREMENT," +
                                " Name     TEXT                NOT NULL," +
                                " Number   TEXT                NOT NULL)";

    private Connection database = null;
    private DatabaseMetaData databaseMetaData = null;
    private Statement statement = null;

    private String sql = "";

    public PhoneBookModelSQL(PhoneBookView view) {
        super(view);
        try {
            database = DriverManager.getConnection(FILENAME);
            databaseMetaData = database.getMetaData();
            statement = database.createStatement();

            statement.executeUpdate(CREATE_SQL);

            sql = "SELECT * FROM PhoneBook";
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                phoneBook.put(result.getString("Name"), result.getString("Number"));
            }
            result.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeDatabase(){
        try{
            // DROP & CREATE
            sql = "DROP TABLE PhoneBook";
            statement.executeUpdate(sql);
            statement.executeUpdate(CREATE_SQL);

            Set<String> keys = phoneBook.keySet();
            for(String key: keys){
                sql = String.format("INSERT INTO PhoneBook (Name, Number) VALUES ('%s', '%s')", key, phoneBook.get(key));
                statement.executeUpdate(sql);
            }

            database.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
