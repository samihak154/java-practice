DELIMITER //

CREATE PROCEDURE get_order_with_details(
    IN user_order_id INT
)
BEGIN
    -- First result set: Order with Server info
    SELECT o.OrderID, o.ServerID, o.OrderDate, o.SubTotal, o.Tax, o.Tip, o.Total,
           s.FirstName, s.LastName, s.HireDate, s.TermDate
    FROM `Order` o
    INNER JOIN Server s ON o.ServerID = s.ServerID
    WHERE o.OrderID = user_order_id;

    -- Second result set: OrderItems with Item and ItemCategory info
    SELECT oi.OrderItemID, oi.OrderID, oi.ItemID, oi.Quantity, oi.Price,
           i.ItemCategoryID, i.ItemName, i.ItemDescription, i.StartDate, i.EndDate, i.UnitPrice,
           ic.ItemCategoryName
    FROM OrderItem oi
    INNER JOIN Item i ON oi.ItemID = i.ItemID
    INNER JOIN ItemCategory ic ON ic.ItemCategoryID = i.ItemCategoryID
    WHERE oi.OrderID = user_order_id;

    -- Third result set: Payments with PaymentType info
    SELECT p.PaymentID, p.OrderID, p.PaymentTypeID, p.Amount,
           pt.PaymentTypeName
    FROM Payment p
    INNER JOIN PaymentType pt ON p.PaymentTypeID = pt.PaymentTypeID
    WHERE p.OrderID = user_order_id;
END //

DELIMITER ;