package com.capita.calculator.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capita.calculator.example.error.SnapException;

@Component
public class CalculatorComponent {

    @Autowired
    private CalculatorService calculatorService;

    public String validateFormat(String expression) throws SnapException{
        return calculatorService.validateFormat(expression);
    }
    public String formateExpession(String expression) { 
        return calculatorService.formateExpession(expression);
    }
    public double evaluateExpression(String expression) throws SnapException{
        return calculatorService.evaluateExpression(expression);
    }
    
}