package lottogame.domain;

public class Money {

    private Double value;

    public Money(Double value) {
        this.value = value;
    }

    public static Money from(double value) {
        return new Money(Double.from(value));
    }

    public static Money from(Double value) {
        return new Money(value);
    }

    public static Money fromNumber(Number number) {
        return new Money(Double.from(number.intValue()));
    }

    public Money multiply(Number from) {
        return from(value.multiply(from));
    }

    public Money add(Number prize) {
        return from(value.add(prize));
    }

    public Money divide(Money from) {
        return from(value.divide(from.value));
    }

    public int toInt() {
        return value.toInt();
    }

    public int divideToInt(Money from) {
        return divide(from).toInt();
    }

    public Double divideToDouble(Money money) {
        return divide(money).toDouble();
    }

    public Double toDouble() {
        return value;
    }


    public boolean isLessThan(Money price) {
        return value.isLessThan(price.value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Money money = (Money) object;

        return value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
