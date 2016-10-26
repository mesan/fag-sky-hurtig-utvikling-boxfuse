package no.mesan.fag.sky.hurtig.boxfuse.storage;

import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MessageStorage {

    private final DataSource dataSource;

    @Inject
    public MessageStorage(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Message> getMessages() {
        try {
            Connection connection = getConnection();

            try {
                List<Message> messages = new ArrayList<>();
                ResultSet resultSet = connection.createStatement()
                        .executeQuery("SELECT id, message, time FROM message");

                while (resultSet.next()) {
                    messages.add(new Message(
                            resultSet.getInt("id"),
                            resultSet.getString("message"),
                            resultSet.getTimestamp("time").toLocalDateTime())
                    );
                }

                return messages;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Message message) {
        try {
            Connection connection = getConnection();

            try {
                PreparedStatement insertStatement = connection.prepareStatement(
                        "INSERT INTO message (message, time) VALUES (?, ?)");
                insertStatement.setString(1, message.message);
                insertStatement.setTimestamp(2, Timestamp.valueOf(message.time));
                insertStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
