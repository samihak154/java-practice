package org.example.data.impl;

import org.example.data.ItemRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.mappers.ItemCategoryMapper;
import org.example.data.mappers.ItemMapper;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MySqlItemRepo implements ItemRepo {

    private final JdbcTemplate jdbcTemplate;

    public MySqlItemRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item getItemById(int id) throws RecordNotFoundException, InternalErrorException {
        try {
            final String sql = "SELECT ItemID, ItemCategoryID, ItemName, ItemDescription, " +
                    "StartDate, EndDate, UnitPrice FROM Item WHERE ItemID = ?";

            List<Item> items = jdbcTemplate.query(sql, (rs, rowNum) -> ItemMapper.mapRow(rs), id);

            if (items.isEmpty()) {
                throw new RecordNotFoundException();
            }

            return items.get(0);

        } catch (RecordNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Item> getAllAvailableItems(LocalDate today) throws InternalErrorException {
        try {
            final String sql = "SELECT ItemID, ItemCategoryID, ItemName, ItemDescription, " +
                    "StartDate, EndDate, UnitPrice FROM Item " +
                    "WHERE StartDate <= ? AND (EndDate IS NULL OR EndDate > ?)";

            return jdbcTemplate.query(sql, (rs, rowNum) -> ItemMapper.mapRow(rs), today, today);

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Item> getItemsByCategory(LocalDate today, int itemCategoryID) throws InternalErrorException {
        try {
            final String sql = "SELECT ItemID, ItemCategoryID, ItemName, ItemDescription, " +
                    "StartDate, EndDate, UnitPrice FROM Item " +
                    "WHERE ItemCategoryID = ? AND StartDate <= ? AND (EndDate IS NULL OR EndDate > ?)";

            return jdbcTemplate.query(sql, (rs, rowNum) -> ItemMapper.mapRow(rs),
                    itemCategoryID, today, today);

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<ItemCategory> getAllItemCategories() throws InternalErrorException {
        try {
            final String sql = "SELECT ItemCategoryID, ItemCategoryName FROM ItemCategory";

            return jdbcTemplate.query(sql, (rs, rowNum) -> ItemCategoryMapper.mapRow(rs));

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
