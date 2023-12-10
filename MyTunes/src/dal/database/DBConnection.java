package dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Wrapper;

public class DBConnection {

    private SQLServerDataSource dataSource;

    public DBConnection(){
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName("CSe2023b_e_Mytunes");
        dataSource.setUser("CSe2023b_e_23");
        dataSource.setPassword("CSe2023bE23#23");
        dataSource.setServerName("10.176.111.34");
        dataSource.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main (String[] args) throws SQLException{
        DBConnection dbConnection = new DBConnection();

        try (Connection connection = dbConnection.getConnection()){
            System.out.println("is it open?" + !connection.isClosed());
        }
    }
}
