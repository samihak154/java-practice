-- Get item by ID
SELECT ItemID, ItemCategoryID, ItemName, ItemDescription, StartDate, EndDate, UnitPrice
FROM Item WHERE ItemID = 1;

-- Get all available items for current date
SELECT ItemID, ItemCategoryID, ItemName, ItemDescription, StartDate, EndDate, UnitPrice
FROM Item
WHERE StartDate <= CURDATE() AND (EndDate IS NULL OR EndDate > CURDATE());

-- Get items by category for current date
SELECT ItemID, ItemCategoryID, ItemName, ItemDescription, StartDate, EndDate, UnitPrice
FROM Item
WHERE ItemCategoryID = 1 AND StartDate <= CURDATE() AND (EndDate IS NULL OR EndDate > CURDATE());

-- Get all item categories
SELECT ItemCategoryID, ItemCategoryName FROM ItemCategory;