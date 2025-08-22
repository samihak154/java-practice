package org.example.data.mappers;

import org.example.model.Tax;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxMapper {

    public static Tax mapRow(ResultSet resultSet) throws SQLException {
        Tax tax = new Tax();

        tax.setTaxID(resultSet.getInt("TaxID"));
        tax.setTaxPercentage(resultSet.getBigDecimal("TaxPercentage"));
        tax.setStartDate(resultSet.getDate("StartDate").toLocalDate());

        // EndDate can be null (current tax rate)
        if (resultSet.getDate("EndDate") != null) {
            tax.setEndDate(resultSet.getDate("EndDate").toLocalDate());
        }

        return tax;
    }
}
