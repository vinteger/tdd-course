import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddFractionsTest {

    @Test
    public void zeroPlusZero() {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        assertEquals(0, sum.value());
    }

    @Test
    public void onePlusOneEqualsTwo() {
        Fraction sum = new Fraction(1).plus(new Fraction(1));
        assertEquals(2, sum.value());
    }

    @Test
    public void negativePlusPositive() {
        Fraction sum = new Fraction(-1).plus(new Fraction(1));
        assertEquals(0, sum.value());
    }

    @Test
    public void negativePlusNegative() {
        Fraction sum = new Fraction(-5).plus(new Fraction(-6));
        assertEquals(-11, sum.value());
    }

    @Test
    public void simplifiedWithCommonDenominator() {
        Fraction sum = new Fraction(1, 5).plus(new Fraction(3, 5));
        assertEquals(4, sum.getNumerator());
        assertEquals(5, sum.getDenominator());
    }
}
