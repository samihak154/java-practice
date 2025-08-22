package org.example.data.impl;

import org.example.data.ServerRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.mappers.ServerMapper;
import org.example.model.Server;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MySqlServerRepo implements ServerRepo {

    private final JdbcTemplate jdbcTemplate;

    public MySqlServerRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Server getServerById(int id) throws InternalErrorException, RecordNotFoundException {
        try {
            final String sql = "SELECT ServerID, FirstName, LastName, HireDate, TermDate FROM Server WHERE ServerID = ?";

            List<Server> servers = jdbcTemplate.query(sql, (rs, rowNum) -> ServerMapper.mapRow(rs), id);

            if (servers.isEmpty()) {
                throw new RecordNotFoundException();
            }

            return servers.get(0);

        } catch (RecordNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException {
        try {
            final String sql = "SELECT ServerID, FirstName, LastName, HireDate, TermDate " +
                    "FROM Server " +
                    "WHERE HireDate <= ? AND (TermDate IS NULL OR TermDate > ?)";

            return jdbcTemplate.query(sql, (rs, rowNum) -> ServerMapper.mapRow(rs), date, date);

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
