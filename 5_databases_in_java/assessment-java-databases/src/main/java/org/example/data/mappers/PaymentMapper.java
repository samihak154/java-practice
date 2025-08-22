package org.example.data.mappers;

import org.example.model.Payment;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper {

    public static Payment mapRow(ResultSet resultSet) throws SQLException {
        Payment payment = new Payment();

        payment.setPaymentID(resultSet.getInt("PaymentID"));
        payment.setPaymentTypeID(resultSet.getInt("PaymentTypeID"));
        payment.setOrderID(resultSet.getInt("OrderID"));
        payment.setAmount(resultSet.getBigDecimal("Amount"));

        return payment;
    }

    public static RowMapper<Payment> paymentRowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }
}




