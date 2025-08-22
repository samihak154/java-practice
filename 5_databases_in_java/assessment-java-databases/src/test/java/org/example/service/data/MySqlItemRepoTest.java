package org.example.service.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.MySqlItemRepo;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MySqlItemRepoTest {

    @Autowired
    private MySqlItemRepo repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("CALL set_known_good_state_minimal()");
    }

    @Test
    void shouldGetItemById() {
        try {
            Item item = repository.getItemById(1);

            assertNotNull(item);
            assertEquals(1, item.getItemID());
            assertNotNull(item.getItemName());
            assertNotNull(item.getUnitPrice());
            assertTrue(item.getItemCategoryID() > 0);

            System.out.println("Found item: " + item.getItemName() +
                    " ($" + item.getUnitPrice() + ")");

        } catch (Exception e) {
            fail("Should not throw exception for valid item ID: " + e.getMessage());
        }
    }

    @Test
    void shouldThrowRecordNotFoundForInvalidItemId() {
        assertThrows(RecordNotFoundException.class, () -> {
            repository.getItemById(99999);
        });
    }

    @Test
    void shouldGetAllAvailableItems() {
        try {
            LocalDate today = LocalDate.now();
            List<Item> items = repository.getAllAvailableItems(today);

            assertNotNull(items);
            assertTrue(items.size() > 0);

            System.out.println("Found " + items.size() + " available items");

            for (Item item : items) {
                assertTrue(item.getStartDate().isBefore(today) || item.getStartDate().isEqual(today));
                if (item.getEndDate() != null) {
                    assertTrue(item.getEndDate().isAfter(today));
                }
            }

        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    void shouldGetItemsByCategory() {
        try {
            LocalDate today = LocalDate.now();
            int categoryId = 1;

            List<Item> items = repository.getItemsByCategory(today, categoryId);

            assertNotNull(items);

            System.out.println("Found " + items.size() + " items in category " + categoryId);

            for (Item item : items) {
                assertEquals(categoryId, item.getItemCategoryID());
                assertTrue(item.getStartDate().isBefore(today) || item.getStartDate().isEqual(today));
                if (item.getEndDate() != null) {
                    assertTrue(item.getEndDate().isAfter(today));
                }
            }

        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    void shouldGetAllItemCategories() {
        try {
            List<ItemCategory> categories = repository.getAllItemCategories();

            assertNotNull(categories);
            assertTrue(categories.size() > 0);

            System.out.println("Found " + categories.size() + " item categories:");
            for (ItemCategory category : categories) {
                System.out.println("  " + category.getItemCategoryID() +
                        ": " + category.getItemCategoryName());
                assertTrue(category.getItemCategoryID() > 0);
                assertNotNull(category.getItemCategoryName());
            }

        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    void shouldReturnEmptyListForInvalidCategory() throws InternalErrorException {
        LocalDate today = LocalDate.now();
        int invalidCategoryId = 99999;

        List<Item> items = repository.getItemsByCategory(today, invalidCategoryId);

        assertNotNull(items);
        assertEquals(0, items.size());
    }
}
