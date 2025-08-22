-- Get all orders
SELECT OrderID, ServerID, OrderDate, SubTotal, Tax, Tip, Total
FROM `Order` WHERE OrderID > 0 ORDER BY OrderDate DESC;

-- Get server information for order
SELECT ServerID, FirstName, LastName, HireDate, TermDate
FROM Server WHERE ServerID = 1;

-- Get order items with item details
SELECT oi.OrderItemID, oi.OrderID, oi.ItemID, oi.Quantity, oi.Price,
       i.ItemName, i.ItemDescription, i.UnitPrice, i.ItemCategoryID, i.StartDate, i.EndDate
FROM OrderItem oi
JOIN Item i ON oi.ItemID = i.ItemID
WHERE oi.OrderID = 1;

-- Get payments with payment type details
SELECT p.PaymentID, p.PaymentTypeID, p.OrderID, p.Amount, pt.PaymentTypeName
FROM Payment p
JOIN PaymentType pt ON p.PaymentTypeID = pt.PaymentTypeID
WHERE p.OrderID = 1;