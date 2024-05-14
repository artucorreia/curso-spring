package com.example.firstproject;

public class MathUtil {
    public static double convertToDouble(String stringNumber) {
        if (stringNumber == null) return 0d;
        stringNumber = stringNumber.replaceAll(",", ".");
        if (isNumeric(stringNumber)) return Double.parseDouble(stringNumber);
        return 0d;
    }
    public static boolean isNumeric(String stringNumber) {
        return stringNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
