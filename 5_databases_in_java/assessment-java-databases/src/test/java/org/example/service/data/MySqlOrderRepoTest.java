package org.example.service.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.MySqlOrderRepo;
import org.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MySqlOrderRepoTest {

    @Autowired
    private MySqlOrderRepo repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("CALL set_known_good_state_minimal()");
    }

    @Test
    void shouldGetOrderById() {
        try {
            Order order = repository.getOrderById(1);

            assertNotNull(order);
            assertEquals(1, order.getOrderID());
            assertTrue(order.getServerID() > 0);
            assertNotNull(order.getOrderDate());
            assertNotNull(order.getTotal());

            assertNotNull(order.getServer());
            assertNotNull(order.getServer().getFirstName());

            assertNotNull(order.getItems());
            assertTrue(order.getItems().size() > 0);
            assertNotNull(order.getPayments());
            assertTrue(order.getPayments().size() > 0);

            assertNotNull(order.getItems().get(0).getItem());
            assertNotNull(order.getPayments().get(0).getPaymentType());

        } catch (Exception e) {
            fail("Should not throw exception for valid order ID: " + e.getMessage());
        }
    }

    @Test
    void shouldThrowRecordNotFoundForInvalidOrderId() {
        assertThrows(RecordNotFoundException.class, () -> {
            repository.getOrderById(99999);
        });
    }

    @Test
    void shouldGetAllOrdersWithCompleteData() {
        try {
            List<Order> orders = repository.getAllOrders();

            assertNotNull(orders);
            assertTrue(orders.size() > 0);

            assertEquals(3, orders.size());

            for (Order order : orders) {
                assertTrue(order.getOrderID() > 0);
                assertTrue(order.getServerID() > 0);
                assertNotNull(order.getOrderDate());
                assertNotNull(order.getSubTotal());
                assertNotNull(order.getTax());
                assertNotNull(order.getTip());
                assertNotNull(order.getTotal());
            }

        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    void shouldReturnOrdersInCorrectOrder() {
        try {
            List<Order> orders = repository.getAllOrders();

            for (int i = 1; i < orders.size(); i++) {
                LocalDateTime previousDate = orders.get(i - 1).getOrderDate();
                LocalDateTime currentDate = orders.get(i).getOrderDate();

                assertTrue(previousDate.isAfter(currentDate) || previousDate.isEqual(currentDate),
                        "Orders should be ordered by OrderDate DESC");
            }

        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    void shouldAddOrder() {
        try {
            Order newOrder = createTestOrder();

            Order savedOrder = repository.addOrder(newOrder);

            assertNotNull(savedOrder);
            assertTrue(savedOrder.getOrderID() > 0);
            assertEquals(newOrder.getServerID(), savedOrder.getServerID());
            assertEquals(newOrder.getTotal(), savedOrder.getTotal());

            Order retrievedOrder = repository.getOrderById(savedOrder.getOrderID());
            assertEquals(savedOrder.getOrderID(), retrievedOrder.getOrderID());

        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception when adding order: " + e.getMessage());
        }
    }

    @Test
    void shouldUpdateOrder() {
        try {
            Order order = repository.getOrderById(1);

            BigDecimal newTip = new BigDecimal("15.00");
            BigDecimal newTotal = order.getSubTotal().add(order.getTax()).add(newTip);
            order.setTip(newTip);
            order.setTotal(newTotal);

            repository.updateOrder(order);

            Order updatedOrder = repository.getOrderById(1);
            assertEquals(newTip, updatedOrder.getTip());
            assertEquals(newTotal, updatedOrder.getTotal());

        } catch (Exception e) {
            fail("Should not throw exception when updating order: " + e.getMessage());
        }
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentOrder() {
        Order fakeOrder = new Order();
        fakeOrder.setOrderID(99999);
        fakeOrder.setServerID(1);
        fakeOrder.setOrderDate(LocalDateTime.now());
        fakeOrder.setSubTotal(new BigDecimal("10.00"));
        fakeOrder.setTax(new BigDecimal("1.00"));
        fakeOrder.setTip(new BigDecimal("2.00"));
        fakeOrder.setTotal(new BigDecimal("13.00"));
        fakeOrder.setItems(new ArrayList<>());
        fakeOrder.setPayments(new ArrayList<>());

        assertThrows(InternalErrorException.class, () -> {
            repository.updateOrder(fakeOrder);
        });
    }

    @Test
    void shouldDeleteOrder() {
        try {
            Order deletedOrder = repository.deleteOrder(2);

            assertNotNull(deletedOrder);
            assertEquals(2, deletedOrder.getOrderID());

            assertThrows(RecordNotFoundException.class, () -> {
                repository.getOrderById(2);
            });

        } catch (Exception e) {
            fail("Should not throw exception when deleting order: " + e.getMessage());
        }
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentOrder() {
        assertThrows(InternalErrorException.class, () -> {
            repository.deleteOrder(99999);
        });
    }

    private Order createTestOrder() {
        Order order = new Order();
        order.setServerID(1); // We know Server 1 exists from our test data
        order.setOrderDate(LocalDateTime.now());
        order.setSubTotal(new BigDecimal("25.00"));
        order.setTax(new BigDecimal("1.56"));
        order.setTip(new BigDecimal("5.00"));
        order.setTotal(new BigDecimal("31.56"));

        // Add some items - using ItemIDs that we know exist
        List<OrderItem> items = new ArrayList<>();
        OrderItem item1 = new OrderItem();
        item1.setItemID(1); // We'll verify this exists in debug
        item1.setQuantity(1); // Keep it simple
        item1.setPrice(new BigDecimal("25.00"));
        items.add(item1);
        order.setItems(items);

        // Add a payment - using PaymentTypeID that we know exists
        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setPaymentTypeID(1); // We'll verify this exists in debug
        payment.setAmount(new BigDecimal("31.56"));
        payments.add(payment);
        order.setPayments(payments);

        return order;
    }
}
