package loggingSystem.model;

import loggingSystem.interfaces.*;

import java.sql.*;
import java.time.Instant;
import javax.sql.DataSource;

/**
 * Writes log messages to a database table.
 * In real systems: inject a DataSource (e.g. HikariCP pool), use async/batching for high throughput.
 */
public class DatabaseDestination implements Destination {

    private static final String INSERT_LOG = "INSERT INTO logs (timestamp, log_level, content) VALUES (?, ?, ?)";

    private final DataSource dataSource;

    public DatabaseDestination(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void printLogMessage(Message msg) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_LOG)) {
            ps.setObject(1, Instant.now());  // or Timestamp.from(Instant.now()) for java.sql.Timestamp
            ps.setString(2, msg.getLogLevel().name());
            ps.setString(3, msg.getContent());
            ps.executeUpdate();
        } catch (SQLException e) {
            // In production: log to fallback (e.g. console/file) and maybe metrics
            e.printStackTrace();
        }
    }
}
