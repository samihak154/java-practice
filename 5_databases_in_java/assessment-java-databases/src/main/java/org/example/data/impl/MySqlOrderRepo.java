package org.example.data.impl;

import org.example.data.OrderRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.mappers.*;
import org.example.model.*;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MySqlOrderRepo implements OrderRepo {

    private final JdbcTemplate jdbcTemplate;

    public MySqlOrderRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException {
        try {
            Order result = jdbcTemplate.execute("{CALL get_order_with_details(?)}", (CallableStatementCallback<Order>) cs -> {
                cs.setInt(1, id);
                boolean hasResults = cs.execute();

                Order order = null;
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        if (rs.next()) {
                            order = OrderMapper.orderRowMapper().mapRow(rs, 1);
                            order.setServer(ServerMapper.serverRowMapper().mapRow(rs, 1));
                        }
                    }
                }
                if (order == null) {
                    return null;
                }

                List<OrderItem> items = new ArrayList<>();
                hasResults = cs.getMoreResults();
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs.next()) {
                            OrderItem oi = OrderItemMapper.orderItemRowMapper().mapRow(rs, 1);
                            Item i = ItemMapper.itemRowMapper().mapRow(rs, 1);
                            i.setItemCategory(ItemCategoryMapper.itemCategoryRowMapper().mapRow(rs, 1));
                            oi.setItem(i);
                            items.add(oi);
                        }
                    }
                }

                List<Payment> payments = new ArrayList<>();
                hasResults = cs.getMoreResults();
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs.next()) {
                            Payment p = PaymentMapper.paymentRowMapper().mapRow(rs, 1);
                            p.setPaymentType(PaymentTypeMapper.paymentTypeRowMapper().mapRow(rs, 1));
                            payments.add(p);
                        }
                    }
                }
                order.setItems(items);
                order.setPayments(payments);

                return order;
            });

            if (result == null) {
                throw new RecordNotFoundException();
            }

            return result;
        } catch (RecordNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() throws InternalErrorException, RecordNotFoundException {
        try {
            List<Order> result = jdbcTemplate.execute("{CALL get_all_orders_with_details()}", (CallableStatementCallback<List<Order>>) cs -> {
                boolean hasResults = cs.execute();

                List<Order> orders = new ArrayList<>();
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs.next()) {
                            Order order = OrderMapper.orderRowMapper().mapRow(rs, 1);
                            order.setServer(ServerMapper.serverRowMapper().mapRow(rs, 1));
                            orders.add(order);
                        }
                    }
                }

                List<OrderItem> allItems = new ArrayList<>();
                hasResults = cs.getMoreResults();
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs.next()) {
                            OrderItem oi = OrderItemMapper.orderItemRowMapper().mapRow(rs, 1);
                            Item i = ItemMapper.itemRowMapper().mapRow(rs, 1);
                            i.setItemCategory(ItemCategoryMapper.itemCategoryRowMapper().mapRow(rs, 1));
                            oi.setItem(i);
                            allItems.add(oi);
                        }
                    }
                }

                List<Payment> allPayments = new ArrayList<>();
                hasResults = cs.getMoreResults();
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs.next()) {
                            Payment p = PaymentMapper.paymentRowMapper().mapRow(rs, 1);
                            p.setPaymentType(PaymentTypeMapper.paymentTypeRowMapper().mapRow(rs, 1));
                            allPayments.add(p);
                        }
                    }
                }

                for (Order order : orders) {
                    List<OrderItem> orderItems = new ArrayList<>();
                    List<Payment> orderPayments = new ArrayList<>();

                    for (OrderItem item : allItems) {
                        if (item.getOrderID() == order.getOrderID()) {
                            orderItems.add(item);
                        }
                    }

                    for (Payment payment : allPayments) {
                        if (payment.getOrderID() == order.getOrderID()) {
                            orderPayments.add(payment);
                        }
                    }

                    order.setItems(orderItems);
                    order.setPayments(orderPayments);
                }

                return orders;
            });

            return result;

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional
    @Override
    public Order addOrder(Order order) throws InternalErrorException {
        String sql = "INSERT INTO `Order` (ServerID, OrderDate, SubTotal, Tax, Tip, Total) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                ps.setInt(1, order.getServerID());
                ps.setTimestamp(2, java.sql.Timestamp.valueOf(order.getOrderDate()));
                ps.setBigDecimal(3, order.getSubTotal());
                ps.setBigDecimal(4, order.getTax());
                ps.setBigDecimal(5, order.getTip());
                ps.setBigDecimal(6, order.getTotal());

                return ps;
            }, keyHolder);

            if (keyHolder.getKey() != null) {
                order.setOrderID(keyHolder.getKey().intValue());
            } else {
                Integer maxOrderId = jdbcTemplate.queryForObject(
                        "SELECT MAX(OrderID) FROM `Order`", Integer.class);
                order.setOrderID(maxOrderId);
            }

            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    item.setOrderID(order.getOrderID());
                    addOrderItem(item);
                }
            }

            if (order.getPayments() != null) {
                for (Payment payment : order.getPayments()) {
                    payment.setOrderID(order.getOrderID());
                    addPayment(payment);
                }
            }

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }

        return order;
    }

    @Transactional
    @Override
    public void updateOrder(Order order) throws InternalErrorException {
        try {
            final String orderSql = "UPDATE `Order` SET ServerID = ?, OrderDate = ?, " +
                    "SubTotal = ?, Tax = ?, Tip = ?, Total = ? WHERE OrderID = ?";

            int rowsUpdated = jdbcTemplate.update(orderSql,
                    order.getServerID(),
                    java.sql.Timestamp.valueOf(order.getOrderDate()),
                    order.getSubTotal(),
                    order.getTax(),
                    order.getTip(),
                    order.getTotal(),
                    order.getOrderID());

            if (rowsUpdated == 0) {
                throw new InternalErrorException();
            }

            jdbcTemplate.update("DELETE FROM OrderItem WHERE OrderID = ?", order.getOrderID());
            jdbcTemplate.update("DELETE FROM Payment WHERE OrderID = ?", order.getOrderID());

            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    item.setOrderID(order.getOrderID());
                    addOrderItem(item);
                }
            }

            if (order.getPayments() != null) {
                for (Payment payment : order.getPayments()) {
                    payment.setOrderID(order.getOrderID());
                    addPayment(payment);
                }
            }

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional
    @Override
    public Order deleteOrder(int id) throws InternalErrorException {
        try {
            Order order = getOrderById(id);

            jdbcTemplate.update("DELETE FROM OrderItem WHERE OrderID = ?", id);
            jdbcTemplate.update("DELETE FROM Payment WHERE OrderID = ?", id);
            jdbcTemplate.update("DELETE FROM `Order` WHERE OrderID = ?", id);

            return order;

        } catch (RecordNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    private void addOrderItem(OrderItem orderItem) {
        final String sql = "INSERT INTO OrderItem (OrderID, ItemID, Quantity, Price) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderItem.getOrderID());
            ps.setInt(2, orderItem.getItemID());
            ps.setInt(3, orderItem.getQuantity());
            ps.setBigDecimal(4, orderItem.getPrice());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            orderItem.setOrderItemID(keyHolder.getKey().intValue());
        }
    }

    private void addPayment(Payment payment) {
        final String sql = "INSERT INTO Payment (OrderID, PaymentTypeID, Amount) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, payment.getOrderID());
            ps.setInt(2, payment.getPaymentTypeID());
            ps.setBigDecimal(3, payment.getAmount());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            payment.setPaymentID(keyHolder.getKey().intValue());
        }
    }
}
