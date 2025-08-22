package org.example.service.data;

import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.MySqlServerRepo;
import org.example.model.Server;
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
class MySqlServerRepoTest {

    @Autowired
    private MySqlServerRepo repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("CALL set_known_good_state_minimal()");
    }

    @Test
    void shouldGetServerById() {
        try {
            Server server = repository.getServerById(2);

            assertEquals(2, server.getServerID());
            assertNotNull(server.getFirstName());
            assertNotNull(server.getLastName());

            System.out.println("Found server: " + server.getFirstName() + " " + server.getLastName());

        } catch (Exception e) {
            fail("Should not throw exception for valid server ID: " + e.getMessage());
        }
    }

    @Test
    void shouldThrowRecordNotFoundForInvalidId() {
        assertThrows(RecordNotFoundException.class, () -> {
            repository.getServerById(99999);
        });
    }

    @Test
    void shouldGetAllAvailableServers() {
        try {
            LocalDate testDate = LocalDate.of(2022, 6, 1);
            List<Server> servers = repository.getAllAvailableServers(testDate);

            assertNotNull(servers);
            assertTrue(servers.size() > 0);

            System.out.println("Found " + servers.size() + " available servers on " + testDate);

            for (Server server : servers) {
                assertTrue(server.getHireDate().isBefore(testDate) || server.getHireDate().isEqual(testDate));
                if (server.getTermDate() != null) {
                    assertTrue(server.getTermDate().isAfter(testDate));
                }
            }

        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
}
