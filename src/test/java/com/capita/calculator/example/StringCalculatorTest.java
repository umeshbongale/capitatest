
package com.capita.calculator.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.capita.calculator.example.error.SnapException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { StringCalculatorApp.class })
public class StringCalculatorTest{

    @Autowired
    public CalculatorComponent calculatorComponent;

    @Autowired
    private CalculatorService calculatorService;
    
    String expression = "";

	@Before
	public void before() {
		expression = "7+(6*5^2+3-4/2)";
	}
      
    @Test
    public void validateFormat() throws SnapException{
    	 assertNotNull(calculatorComponent.validateFormat(expression));
    }
    
    @Test
    public void formateExpession() {
    	 assertNotNull(calculatorService.formateExpession(expression));
    }
    
    @Test
    public void evaluateExpression() throws SnapException{
    	assertEquals(158, (int)calculatorService.evaluateExpression(calculatorComponent.formateExpession(expression)));
    }
    
    @Test
    public void hasPrecedence() {
       assertEquals(false, calculatorService.hasPrecedence('/','+'));
       assertEquals(false, calculatorService.hasPrecedence('*','+'));
       assertEquals(true, calculatorService.hasPrecedence('+','/'));
       assertEquals(true, calculatorService.hasPrecedence('-','*'));
    }
    
    @Test
    public void calculateOp() {
        //assertEquals("Hello world!", calculatorComponent.evaluate(""));
    	assertEquals(10, (int)calculatorService.calculateOp('+',5,5));
    	assertEquals(25, (int)calculatorService.calculateOp('*',5,5));
    	assertEquals(0, (int)calculatorService.calculateOp('-',5,5));
    	assertEquals(2, (int)calculatorService.calculateOp('/',5,10));
    	
    }
}