package com.example.firstproject.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    public double convertToDouble(String stringNumber) {
        if (stringNumber == null) return 0d;
        return Double.parseDouble(stringNumber);
    }
}