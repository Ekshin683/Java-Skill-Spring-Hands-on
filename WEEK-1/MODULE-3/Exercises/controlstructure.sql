//scenerio - 1

SET SERVEROUTPUT ON;

DECLARE
BEGIN
    FOR cust IN (SELECT CustomerID, Age FROM Customers) LOOP

        IF cust.Age > 60 THEN

            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Interest updated for Customer ID : '
                                 || cust.CustomerID);

        END IF;

    END LOOP;

    COMMIT;
END;
/


//Scenerio - 2


SET SERVEROUTPUT ON;

DECLARE
BEGIN

    FOR cust IN (SELECT CustomerID, Balance FROM Customers) LOOP

        IF cust.Balance > 10000 THEN

            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer '
                                  || cust.CustomerID
                                  || ' promoted to VIP');

        END IF;

    END LOOP;

    COMMIT;

END;
/

//scenerio - 3

SET SERVEROUTPUT ON;

DECLARE
BEGIN

    FOR loan IN
    (
        SELECT
        c.Name,
        l.DueDate
        FROM Customers c
        JOIN Loans l
        ON c.CustomerID = l.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE + 30
    )
    LOOP

        DBMS_OUTPUT.PUT_LINE(
        'Reminder: Dear '
        || loan.Name
        || ', your loan is due on '
        || TO_CHAR(loan.DueDate,'DD-MON-YYYY'));

    END LOOP;

END;
/