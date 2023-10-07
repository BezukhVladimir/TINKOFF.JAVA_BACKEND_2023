package edu.hw1;

public class DecimalNumber32 {
    private boolean sign;
    private int value;
    private int absoluteValue;
    private int numberOfDigits;
    private int[] digits;

    public DecimalNumber32() {
        this(0);
    }

    public DecimalNumber32(int number) {
        sign = number >= 0;
        value = number;
        absoluteValue = sign ? number : -number;

        initializeDigits();
    }

    private void initializeDigits() {
        if (value == 0) {
            numberOfDigits = 1;
            digits = new int[]{0};
        }

        numberOfDigits = NumberUtils.countDigits(absoluteValue);
        digits = NumberUtils.getDigits(absoluteValue);
    }

    public boolean getSign() {
        return sign;
    }

    public int getValue() {
        return value;
    }

    public int getAbsoluteValue() {
        return absoluteValue;
    }

    public int getNumberOfDigits() {
        return numberOfDigits;
    }

    public int[] getDigits() {
        return digits;
    }
}
