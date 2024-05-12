package com.example.firstproject.controller;

import com.example.firstproject.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping(
            value = "/sum/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping(
            value = "/sub/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double subtraction(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) {
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping(
            value = "/multi/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double multiply(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) {
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping(
            value = "/division/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if (convertToDouble(numberTwo) == 0) throw new UnsupportedMathOperationException("Enter a valid value for second number");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public double convertToDouble(String stringNumber) {
        if (stringNumber == null) return 0d;
        stringNumber = stringNumber.replaceAll(",", ".");
        if (isNumeric(stringNumber)) return Double.parseDouble(stringNumber);
        if (!isNumeric(stringNumber)) throw new UnsupportedMathOperationException("Please enter with a numeric value");
        return 0d;
    }

    public boolean isNumeric(String stringNumber) {
        return stringNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}