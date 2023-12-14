package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DatabaseConnection {

    private SQLServerDataSource ds;

    public DatabaseConnection() {
        ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe23B_28_MyTunes");
        ds.setUser("CSe2023b_e_28");
        ds.setPassword("CSe2023bE28#23");
        ds.setServerName("EASV-DB4");
        ds.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }
}
