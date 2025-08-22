package org.example.data.mappers;

import org.example.model.Server;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerMapper {

    public static Server mapRow(ResultSet resultSet) throws SQLException {
        Server server = new Server();

        server.setServerID(resultSet.getInt("ServerID"));
        server.setFirstName(resultSet.getString("FirstName"));
        server.setLastName(resultSet.getString("LastName"));
        server.setHireDate(resultSet.getDate("HireDate").toLocalDate());

        if (resultSet.getDate("TermDate") != null) {
            server.setTermDate(resultSet.getDate("TermDate").toLocalDate());
        }

        return server;
    }

    public static RowMapper<Server> serverRowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }
}
