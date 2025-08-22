package org.example.data.mappers;

import org.example.model.ItemCategory;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCategoryMapper {

    public static ItemCategory mapRow(ResultSet resultSet) throws SQLException {
        ItemCategory category = new ItemCategory();

        category.setItemCategoryID(resultSet.getInt("ItemCategoryID"));
        category.setItemCategoryName(resultSet.getString("ItemCategoryName"));

        return category;
    }

    public static RowMapper<ItemCategory> itemCategoryRowMapper() {
        return (rs, rowNum) -> mapRow(rs);
    }
}