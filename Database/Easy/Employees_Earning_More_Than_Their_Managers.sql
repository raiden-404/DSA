# LeetCode Problem: 181. Employees Earning More Than Their Managers

# Write your MySQL query statement below
SELECT E.name AS Employee
FROM Employee E
# Self Join: Connect the Employee table (E) with itself (M) 
# to match employees with their managers.
JOIN Employee M
    ON E.managerId = M.id
# Condition: Check if the employee's salary is strictly greater than their manager's.
WHERE E.salary > M.salary;