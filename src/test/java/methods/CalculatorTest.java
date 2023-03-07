package methods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    //inicializar test
    Calculator calculator;

    @BeforeEach
    void setUp(){
        //calculator class initilized
        calculator = new Calculator();
    }

    @Test
    void testMultiple(){
        assertEquals(20, calculator.multiply(5,4));
    }


    @Test
    void testMultiple_Different(){
        assertEquals(20, calculator.multiply(4,5));
    }

    @Test
    void testDivide(){
        assertEquals(5 , calculator.divide(25,5));
    }


}