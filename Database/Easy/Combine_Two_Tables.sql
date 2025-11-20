# LeetCode Problem: 175. Combine Two Tables

# Write your MySQL query statement below
SELECT 
    Person.firstName, 
    Person.lastName, 
    Address.city, 
    Address.state
FROM 
    Person
# We use LEFT JOIN to ensure all rows from the Person table are included.
# If a personId is not found in the Address table, city and state will be NULL.
LEFT JOIN 
    Address ON Person.personId = Address.personId;