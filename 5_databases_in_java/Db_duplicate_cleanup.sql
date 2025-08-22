SET FOREIGN_KEY_CHECKS = 0;

-- Delete duplicates
DELETE ic1 FROM ItemCategory ic1
INNER JOIN ItemCategory ic2 
WHERE ic1.ItemCategoryID > ic2.ItemCategoryID 
AND ic1.ItemCategoryName = ic2.ItemCategoryName;

DELETE pt1 FROM PaymentType pt1
INNER JOIN PaymentType pt2 
WHERE pt1.PaymentTypeID > pt2.PaymentTypeID 
AND pt1.PaymentTypeName = pt2.PaymentTypeName;

-- Fix auto-increment with exact numbers
ALTER TABLE ItemCategory AUTO_INCREMENT = 8;
ALTER TABLE PaymentType AUTO_INCREMENT = 6;

SET FOREIGN_KEY_CHECKS = 1;

-- Verify
SELECT ItemCategoryName, COUNT(*) as Count 
FROM ItemCategory 
GROUP BY ItemCategoryName;

SHOW TABLE STATUS LIKE 'ItemCategory';