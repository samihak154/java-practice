package org.example.service.data;

import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.MySqlTaxRepo;
import org.example.model.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MySqlTaxRepoTest {

    @Autowired
    private MySqlTaxRepo repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("CALL set_known_good_state_minimal()");
    }

    @Test
    void shouldGetCurrentTaxForValidDate() {
        try {
            LocalDate testDate = LocalDate.of(2022, 6, 15);
            Tax tax = repository.getCurrentTax(testDate);

            assertNotNull(tax);
            assertTrue(tax.getTaxID() > 0);
            assertNotNull(tax.getTaxPercentage());
            assertTrue(tax.getTaxPercentage().compareTo(BigDecimal.ZERO) > 0);
            assertNotNull(tax.getStartDate());

            assertTrue(tax.getStartDate().isBefore(testDate) || tax.getStartDate().isEqual(testDate));
            if (tax.getEndDate() != null) {
                assertTrue(tax.getEndDate().isAfter(testDate));
            }

            System.out.println("Found tax rate: " + tax.getTaxPercentage() + "% " +
                    "effective from " + tax.getStartDate() +
                    " to " + (tax.getEndDate() != null ? tax.getEndDate() : "present"));

        } catch (Exception e) {
            fail("Should not throw exception for valid date: " + e.getMessage());
        }
    }

    @Test
    void shouldGetCurrentTaxForToday() {
        try {
            LocalDate today = LocalDate.now();
            Tax tax = repository.getCurrentTax(today);

            assertNotNull(tax);
            assertTrue(tax.getTaxID() > 0);
            assertNotNull(tax.getTaxPercentage());

            System.out.println("Current tax rate: " + tax.getTaxPercentage() + "%");

        } catch (Exception e) {
            fail("Should not throw exception for today's date: " + e.getMessage());
        }
    }

    @Test
    void shouldThrowRecordNotFoundForInvalidDate() {
        LocalDate oldDate = LocalDate.of(1900, 1, 1);

        assertThrows(RecordNotFoundException.class, () -> {
            repository.getCurrentTax(oldDate);
        });
    }

    @Test
    void shouldHandleDateBoundaries() {
        try {
            LocalDate date2021 = LocalDate.of(2021, 6, 1);
            Tax tax2021 = repository.getCurrentTax(date2021);

            LocalDate date2022 = LocalDate.of(2022, 6, 1);
            Tax tax2022 = repository.getCurrentTax(date2022);

            assertNotNull(tax2021);
            assertNotNull(tax2022);

            System.out.println("2021 tax rate: " + tax2021.getTaxPercentage() + "%");
            System.out.println("2022 tax rate: " + tax2022.getTaxPercentage() + "%");

        } catch (Exception e) {
            fail("Should handle different date periods: " + e.getMessage());
        }
    }
}