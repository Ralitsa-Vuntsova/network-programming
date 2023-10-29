import java.sql.*;

public class DBManager {
    Connection connection;

    DBManager() {
        connection = null;
    }

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:file_system.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }

    private void disconnect() throws SQLException {
        connection.close();
        System.out.println("Closed database successfully");
    }

    private void createDatabase() {
        connect();

        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            String userTable = "CREATE TABLE USER " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " NAME CHAR(50) NOT NULL, "  +
                    " EMAIL CHAR(50) NOT NULL, " +
                    " PASSWORD CHAR(50) NOT NULL)";

            stmt.executeUpdate(userTable);

            String resourceTable = "CREATE TABLE RESOURCES " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " NAME_OF_FILE CHAR(50) NOT NULL, "  +
                    " TYPE CHAR(50) NOT NULL, " +
                    " SIZE REAL NOT NULL)";

            stmt.executeUpdate(resourceTable);

            String rightsTable = "CREATE TABLE RIGHTS " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " USER_ID INT NOT NULL, "  +
                    " RESOURCE_ID INT NOT NULL, " +
                    " TYPE CHAR(50) NOT NULL, " +
                    " FOREIGN KEY(USER_ID) REFERENCES USER(ID), " +
                    " FOREIGN KEY(RESOURCE_ID) REFERENCES RESOURCE(ID))";

            stmt.executeUpdate(rightsTable);

            stmt.close();
            disconnect();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Tables created successfully");
    }

    private void initDatabase() {
        connect();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO USER (ID,NAME,EMAIL,PASSWORD) " +
                    "VALUES (1, 'Paul', 'paul@gmail.com', 'paul_123');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO USER (ID,NAME,EMAIL,PASSWORD) " +
                    "VALUES (2, 'Teddy', 'teddy@gmail.com', 'teddy_123');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RESOURCES (ID,NAME_OF_FILE,TYPE,SIZE) " +
                    "VALUES (1, 'file1', 'txt', '123');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RESOURCES (ID,NAME_OF_FILE,TYPE,SIZE) " +
                    "VALUES (2, 'file2', 'sql', '321');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RIGHTS (ID,USER_ID,RESOURCE_ID,TYPE) " +
                    "VALUES (1, 1, 1, 'write');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RIGHTS (ID,USER_ID,RESOURCE_ID,TYPE) " +
                    "VALUES (2, 2, 2, 'read');";
            stmt.executeUpdate(sql);

            stmt.close();
            connection.commit();
            disconnect();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Records created successfully");
    }

    private void selectAll() {
        connect();
        Statement stmt = null;

        try {
            connection.setAutoCommit(false);

            stmt = connection.createStatement();
            ResultSet userResult = stmt.executeQuery("SELECT * FROM USER;");

            while (userResult.next()) {
                int id = userResult.getInt("id");
                String  name = userResult.getString("name");
                String  email = userResult.getString("email");
                String  password = userResult.getString("password");

                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("EMAIL = " + email);
                System.out.println("PASSWORD = " + password);
                System.out.println();
            }

            userResult.close();

            stmt = connection.createStatement();
            ResultSet resourcesResult = stmt.executeQuery("SELECT * FROM RESOURCES;");

            while (resourcesResult.next()) {
                int id = resourcesResult.getInt("id");
                String  nameOfFile = resourcesResult.getString("name_of_file");
                String  type = resourcesResult.getString("type");
                float  size = resourcesResult.getFloat("size");

                System.out.println("ID = " + id);
                System.out.println("NAME_OF_FILE = " + nameOfFile);
                System.out.println("TYPE = " + type);
                System.out.println("SIZE = " + size);
                System.out.println();
            }

            resourcesResult.close();

            stmt = connection.createStatement();
            ResultSet rightsResult = stmt.executeQuery("SELECT * FROM RIGHTS;");

            while (rightsResult.next()) {
                int id = rightsResult.getInt("id");
                int  userId = rightsResult.getInt("user_id");
                int  resourceId = rightsResult.getInt("resource_id");
                String  type = rightsResult.getString("type");

                System.out.println("ID = " + id);
                System.out.println("USER_ID = " + userId);
                System.out.println("RESOURCE_ID = " + resourceId);
                System.out.println("TYPE = " + type);
                System.out.println();
            }

            userResult.close();

            stmt.close();
            disconnect();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        System.out.println("Operation done successfully");
    }

    private void emptyDatabase() {
        connect();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            connection.setAutoCommit(false);

            String userTable = "DELETE FROM USER;";
            stmt.executeUpdate(userTable);

            String resourcesTable = "DELETE FROM RESOURCES;";
            stmt.executeUpdate(resourcesTable);

            String rightsTable = "DELETE FROM RIGHTS;";
            stmt.executeUpdate(rightsTable);

            stmt.close();
            connection.commit();
            disconnect();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Records cleared successfully");
    }

    private void dropAllTables() {
        connect();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            connection.setAutoCommit(false);

            String userTable = "DROP TABLE USER;";
            stmt.executeUpdate(userTable);

            String resourcesTable = "DROP TABLE RESOURCES;";
            stmt.executeUpdate(resourcesTable);

            String rightsTable = "DROP TABLE RIGHTS;";
            stmt.executeUpdate(rightsTable);

            stmt.close();
            connection.commit();
            disconnect();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Tables deleted successfully");
    }

    public static void main(String[] args) {
        DBManager manager = new DBManager();
        manager.createDatabase();
        manager.initDatabase();
        manager.selectAll();
        
        // manager.emptyDatabase();
        // manager.selectAll();
        // manager.dropAllTables();
        // manager.selectAll();
    }
}
