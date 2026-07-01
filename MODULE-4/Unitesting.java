//Unit testing: It makes code simpler and easier to maintain. It helps to find bugs easily and it performs on a method or and class at a time.
//uses Junit framework to perform unit testing.
//it uses one pattern called AAA(Arrange, Act, Assert).
//AAA pattern for arrange all the data and objects that are required to perform and then it will call the method what to perform then it sees the assests means output then it would find bugs in it.
//parameterized testing is used to test a method woth multiple inputs in order to check instead of testing 10 times it makes to test code in one single time.
public class Unitesting {
    
}
//Parameterised Testing
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorParameterizedTest {

    private final Calculator calculator = new Calculator();

    // Tests the same method with multiple input pairs
    @ParameterizedTest
    @CsvSource({
        "1, 1, 2",
        "5, 3, 8",
        "10, -3, 7",
        "0, 0, 0",
        "-5, -5, -10"
    })
    void shouldAddCorrectly(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    // Tests a single input list
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 7, 11, 13})
    void shouldReturnPositiveForPositiveInputs(int number) {
        assertTrue(calculator.add(number, 0) > 0);
    }
}

//AAA pattern

@Test
void shouldCalculateTotalPriceWithDiscount() {
    // Arrange
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Laptop", 1000.0);
    cart.addItem("Mouse", 25.0);
    double discountPercent = 10.0;

    // Act
    double total = cart.calculateTotal(discountPercent);

    // Assert
    assertEquals(922.5, total, 0.01);
}