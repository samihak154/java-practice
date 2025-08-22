package org.example.data.impl;

import org.example.data.PaymentTypeRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.mappers.PaymentTypeMapper;
import org.example.model.PaymentType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySqlPaymentTypeRepo implements PaymentTypeRepo {

    private final JdbcTemplate jdbcTemplate;

    public MySqlPaymentTypeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PaymentType> getAll() throws InternalErrorException {
        try {
            final String sql = "SELECT PaymentTypeID, PaymentTypeName FROM PaymentType";

            return jdbcTemplate.query(sql, (rs, rowNum) -> PaymentTypeMapper.mapRow(rs));

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
