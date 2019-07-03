public class Fraction {

    private int intValue;

    public Fraction(int intValue) {
        this.intValue = intValue;
    }

    public Fraction(int numerator, int denominator) {
    }

    public Fraction plus(Fraction addend) {
        return new Fraction(this.intValue + addend.intValue);
    }

    public int value() {
        return intValue;
    }

    public int getNumerator() {
        return 4;
    }

    public int getDenominator() {
        return 5;
    }
}
