DELIMITER //
CREATE PROCEDURE get_all_orders_with_details()
BEGIN
    -- Result Set 1: All Orders with Server info
    SELECT
        o.OrderID, o.ServerID, o.OrderDate, o.SubTotal, o.Tax, o.Tip, o.Total,
        s.FirstName, s.LastName, s.HireDate, s.TermDate
    FROM `Order` o
    JOIN Server s ON o.ServerID = s.ServerID
    WHERE o.OrderID > 0
    ORDER BY o.OrderDate DESC;

    -- Result Set 2: All OrderItems with Item details for all orders
    SELECT
        oi.OrderItemID, oi.OrderID, oi.ItemID, oi.Quantity, oi.Price,
        i.ItemName, i.ItemDescription, i.UnitPrice, i.ItemCategoryID,
        i.StartDate, i.EndDate,
        ic.ItemCategoryName
    FROM `Order` o
    JOIN OrderItem oi ON o.OrderID = oi.OrderID
    JOIN Item i ON oi.ItemID = i.ItemID
    JOIN ItemCategory ic ON i.ItemCategoryID = ic.ItemCategoryID
    WHERE o.OrderID > 0;

    -- Result Set 3: All Payments with PaymentType details for all orders
    SELECT
        p.PaymentID, p.PaymentTypeID, p.OrderID, p.Amount,
        pt.PaymentTypeName
    FROM `Order` o
    JOIN Payment p ON o.OrderID = p.OrderID
    JOIN PaymentType pt ON p.PaymentTypeID = pt.PaymentTypeID
    WHERE o.OrderID > 0;
END //
DELIMITER ;