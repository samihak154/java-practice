USE SimpleBistroTest;

-- Remove all broken records with ID = 0
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM OrderItem WHERE OrderID = 0 OR OrderItemID = 0;
DELETE FROM Payment WHERE OrderID = 0 OR PaymentID = 0;  
DELETE FROM `Order` WHERE OrderID = 0;

SET FOREIGN_KEY_CHECKS = 1;

-- Verify cleanup
SELECT OrderID, COUNT(*) FROM `Order` GROUP BY OrderID;
SELECT OrderItemID, COUNT(*) FROM OrderItem GROUP BY OrderItemID;
SELECT PaymentID, COUNT(*) FROM Payment GROUP BY PaymentID;