DROP PROCEDURE IF EXISTS set_known_good_state_minimal;

DELIMITER //
CREATE PROCEDURE set_known_good_state_minimal()
BEGIN
    SET FOREIGN_KEY_CHECKS = 0;

    -- Delete all existing test data (including any broken records)
    DELETE FROM OrderItem WHERE OrderItemID >= 0;
    DELETE FROM Payment WHERE PaymentID >= 0;
    DELETE FROM `Order` WHERE OrderID >= 0;

    SET FOREIGN_KEY_CHECKS = 1;

    -- Reset auto-increment to 1 BEFORE inserting
    ALTER TABLE `Order` AUTO_INCREMENT = 1;
    ALTER TABLE OrderItem AUTO_INCREMENT = 1;
    ALTER TABLE Payment AUTO_INCREMENT = 1;

    -- Insert test data WITHOUT explicit IDs (let auto-increment work)
    INSERT INTO `Order` (ServerID, OrderDate, SubTotal, Tax, Tip, Total)
    VALUES
        (1, '2022-05-03 10:00:00', 25.00, 1.56, 5.00, 31.56),
        (2, '2021-12-07 14:30:00', 15.00, 0.94, 2.25, 18.19),
        (3, '2022-01-15 12:00:00', 30.00, 1.88, 6.00, 37.88);

    -- Rest of the procedure...
END //
DELIMITER ;