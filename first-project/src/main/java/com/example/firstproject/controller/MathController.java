package com.example.firstproject.controller;

import com.example.firstproject.SimpleMath;
import com.example.firstproject.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import static com.example.firstproject.MathUtil.convertToDouble;
import static com.example.firstproject.MathUtil.isNumeric;

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
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        return SimpleMath.sum(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/sub/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double subtraction(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        return SimpleMath.subtraction(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/multi/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double multiply(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        return SimpleMath.sum(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/division/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        if (convertToDouble(numberTwo) == 0) {
            throw new UnsupportedMathOperationException("Enter a valid value for second number");
        }
        return SimpleMath.division(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/mean/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double mean(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        return SimpleMath.mean(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/power/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double power(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        return SimpleMath.power(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/squareRoot/{number}",
            method = RequestMethod.GET
    )
    public double squareRoot(
            @PathVariable(value = "number") String number
    ) {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        if (convertToDouble(number) < 0) {
            throw new UnsupportedMathOperationException("Please enter a valid value");
        }
        return SimpleMath.squareRoot(convertToDouble(number));
    }
}