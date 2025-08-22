package org.example.data.mappers;

import org.example.model.Item;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ItemMapper {

    public static Item mapRow(ResultSet resultSet) throws SQLException {
        Item item = new Item();

        item.setItemID(resultSet.getInt("ItemID"));
        item.setItemCategoryID(resultSet.getInt("ItemCategoryID"));
        item.setItemName(resultSet.getString("ItemName"));
        item.setItemDescription(resultSet.getString("ItemDescription"));
        item.setStartDate(resultSet.getObject("StartDate", LocalDate.class));
        item.setEndDate(resultSet.getObject("EndDate", LocalDate.class));
        item.setUnitPrice(resultSet.getBigDecimal("UnitPrice"));
        return item;
    }

    public static RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }
}