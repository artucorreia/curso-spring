package com.example.firstproject;

public class SimpleMath {
    public static double sum(double numberOne, double numberTwo) {
        return numberOne + numberTwo;
    }

    public static double subtraction(double numberOne, double numberTwo) {
        return numberOne - numberTwo;
    }

    public static double multiply(double numberOne, double numberTwo) {
        return numberOne * numberTwo;
    }

    public static double division(double numberOne, double numberTwo) {
        return numberOne / numberTwo;
    }

    public static double mean(double numberOne, double numberTwo) {
        return sum(numberOne, numberTwo) / 2;
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static double squareRoot(double number) {
        return Math.sqrt(number);
    }
}
