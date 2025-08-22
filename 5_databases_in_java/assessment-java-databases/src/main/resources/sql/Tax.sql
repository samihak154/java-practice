-- Get current tax rate for today
SELECT TaxID, TaxPercentage, StartDate, EndDate
FROM Tax
WHERE StartDate <= CURDATE() AND (EndDate IS NULL OR EndDate > CURDATE());

-- Test tax rate for specific date
SELECT TaxID, TaxPercentage, StartDate, EndDate
FROM Tax
WHERE StartDate <= '2022-01-01' AND (EndDate IS NULL OR EndDate > '2022-01-01');

-- View all tax records to understand date ranges
SELECT TaxID, TaxPercentage, StartDate, EndDate FROM Tax ORDER BY StartDate;