package org.example.data.mappers;

import org.example.model.PaymentType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentTypeMapper {

    public static PaymentType mapRow(ResultSet resultSet) throws SQLException {
        PaymentType paymentType = new PaymentType();

        // Map database columns to object properties
        paymentType.setPaymentTypeID(resultSet.getInt("PaymentTypeID"));
        paymentType.setPaymentTypeName(resultSet.getString("PaymentTypeName"));

        return paymentType;
    }

    public static RowMapper<PaymentType> paymentTypeRowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }
}