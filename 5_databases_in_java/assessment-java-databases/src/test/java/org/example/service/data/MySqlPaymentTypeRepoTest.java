package org.example.service.data;

import org.example.data.impl.MySqlPaymentTypeRepo;
import org.example.model.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MySqlPaymentTypeRepoTest {

    @Autowired
    private MySqlPaymentTypeRepo repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        // Reset database to known state before each test
        jdbcTemplate.execute("CALL set_known_good_state_minimal()");
    }

    @Test
    void shouldGetAllPaymentTypes() {
        try {
            List<PaymentType> paymentTypes = repository.getAll();

            // Verify we got payment types
            assertNotNull(paymentTypes);
            assertTrue(paymentTypes.size() > 0);

            // Print for debugging
            System.out.println("Found " + paymentTypes.size() + " payment types:");
            for (PaymentType pt : paymentTypes) {
                System.out.println("ID: " + pt.getPaymentTypeID() + ", Name: " + pt.getPaymentTypeName());
            }

        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
}
