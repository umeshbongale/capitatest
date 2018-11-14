package com.capita.calculator.example;

import java.util.stream.IntStream;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.capita.calculator.example.constant.ApplicationConstant;
import com.capita.calculator.example.error.SnapException;

@ComponentScan
public class StringCalculatorApp {
	//private static final Logger LOGGER = Logger.getLogger(StringCalculatorApp.class)
	public static void main(String[] args) {
		System.out.println("Input :\n" + ApplicationConstant.EXPRESSION);
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				StringCalculatorApp.class)) {
			CalculatorComponent calculatorComponent = context.getBean(CalculatorComponent.class);
			String lines[] = ApplicationConstant.EXPRESSION.split("\\r?\\n");
			if(lines.length == Integer.parseInt(lines[0])+1 && (Integer.parseInt(lines[0])+1 >=0 && Integer.parseInt(lines[0])+1 <=100)) {
						IntStream.range(1, lines.length).forEach(nbr -> {
				try {
					String expression = calculatorComponent.validateFormat(lines[nbr].replaceAll("\\s", ""));
					double d = calculatorComponent.evaluateExpression(calculatorComponent.formateExpession(expression));
					System.out.println("Case #" + nbr + ": " + ApplicationConstant.NF.format(d));
				} catch (SnapException e) {
					System.out.println("Case #" + nbr + ": " + ApplicationConstant.ERROR_MSG);
				} catch (Exception e) {
					System.out.println("Case #" + nbr + ": " + ApplicationConstant.ERROR_MSG);
				}
			});
			}else {
				System.out.println("Please provide valid data.");
			}
		}
	}
}