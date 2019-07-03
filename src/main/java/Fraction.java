public class Fraction {

    private final int intValue;

    public Fraction(int intValue) {
        this.intValue = intValue;
    }

    public Fraction plus(Fraction addend) {
        return new Fraction(this.intValue + addend.intValue);
    }

    public int value(){
        return intValue;
    }
}
