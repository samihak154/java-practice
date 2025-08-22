-- Get server by ID
SELECT ServerID, FirstName, LastName, HireDate, TermDate
FROM Server WHERE ServerID = 1;

-- Get all available servers for current date
SELECT ServerID, FirstName, LastName, HireDate, TermDate
FROM Server
WHERE HireDate <= CURDATE() AND (TermDate IS NULL OR TermDate > CURDATE());

-- Test server availability for specific date
SELECT ServerID, FirstName, LastName, HireDate, TermDate
FROM Server
WHERE HireDate <= '2022-06-01' AND (TermDate IS NULL OR TermDate > '2022-06-01');