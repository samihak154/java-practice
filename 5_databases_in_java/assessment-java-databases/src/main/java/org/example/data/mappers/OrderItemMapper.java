package org.example.data.mappers;

import org.example.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper {

    public static OrderItem mapRow(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderItemID(resultSet.getInt("OrderItemID"));
        orderItem.setOrderID(resultSet.getInt("OrderID"));
        orderItem.setItemID(resultSet.getInt("ItemID"));
        orderItem.setQuantity(resultSet.getInt("Quantity"));
        orderItem.setPrice(resultSet.getBigDecimal("Price"));

        return orderItem;
    }

    public static RowMapper<OrderItem> orderItemRowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }
}
