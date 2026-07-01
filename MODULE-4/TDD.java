//Test Driven Development(TDD): it basically means writing test cases first then writing original code based on the test cases. It will only neccessary code that passes that test cases.

//It uses the concept of Red-Green-Refactor cycle.

//it's nothing but red means actual bankaccount code is not written yet.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void shouldReturnZeroBalanceForNewAccount() {
        BankAccount account = new BankAccount();
        assertEquals(0.0, account.getBalance());
        // This test FAILS right now because BankAccount doesn't exist yet
    }
}

// Green: Now we write the simplest code that required to pass the test cases.
public class BankAccount {
    private double balance = 0.0;

    public double getBalance() {
        return balance;
    }
}

//like these it does the same repeatedly until it acheive all the requirements of the project. It is a very good practice to write code in this way because it will help us to write only necessary code and also it will help us to avoid bugs in the code.
public class TDD {
    
}
