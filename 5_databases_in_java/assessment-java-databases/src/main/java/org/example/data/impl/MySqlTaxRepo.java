package org.example.data.impl;

import org.example.data.TaxRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.mappers.TaxMapper;
import org.example.model.Tax;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MySqlTaxRepo implements TaxRepo {

    private final JdbcTemplate jdbcTemplate;

    public MySqlTaxRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tax getCurrentTax(LocalDate dateOf) throws InternalErrorException, RecordNotFoundException {
        try {
            final String sql = "SELECT TaxID, TaxPercentage, StartDate, EndDate FROM Tax " +
                    "WHERE StartDate <= ? AND (EndDate IS NULL OR EndDate > ?)";

            List<Tax> taxes = jdbcTemplate.query(sql, (rs, rowNum) -> TaxMapper.mapRow(rs),
                    dateOf, dateOf);

            if (taxes.isEmpty()) {
                throw new RecordNotFoundException();
            }

            return taxes.get(0);

        } catch (RecordNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
