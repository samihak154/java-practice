package org.example.data.mappers;

import org.example.model.Order;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderMapper {

    public static Order mapRow(ResultSet resultSet) throws SQLException {
        Order order = new Order();

        order.setOrderID(resultSet.getInt("OrderID"));
        order.setServerID(resultSet.getInt("ServerID"));
        order.setOrderDate(resultSet.getObject("OrderDate", LocalDateTime.class));
        order.setSubTotal(resultSet.getBigDecimal("SubTotal"));
        order.setTax(resultSet.getBigDecimal("Tax"));
        order.setTip(resultSet.getBigDecimal("Tip"));
        order.setTotal(resultSet.getBigDecimal("Total"));

        return order;
    }

    public static RowMapper<Order> orderRowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }
}