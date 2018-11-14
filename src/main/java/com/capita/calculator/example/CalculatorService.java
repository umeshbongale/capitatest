package com.capita.calculator.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Stack;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.capita.calculator.example.constant.ApplicationConstant;
import com.capita.calculator.example.error.SnapException;

@Component
public class CalculatorService {


	/**
	 * Format expression for evaluate
	 * @param expression
	 * @return
	 */
	public String formateExpession(String expression) {

		for (char c : ApplicationConstant.OPERATORS) {
			expression = expression.replace(c + "", " " + c + " ");
		}
		return expression;
	}

	/**
	 * Validate format
	 * 
	 * @param expression
	 * @return
	 * @throws SnapException
	 */
	public String validateFormat(String expression) throws SnapException {
		for (char c : ApplicationConstant.VALIDATE_FOR_OPERATORS) {
			if (expression.contains(c + "+") || expression.contains(c + "-")) {
				throw new SnapException(ApplicationConstant.ERROR_MSG);
			}
			if (expression.contains(".")) {
				throw new SnapException(ApplicationConstant.ERROR_MSG);
			}
		}
		for (int i = ApplicationConstant.MIN_VAL; i <= ApplicationConstant.MAX_VAL; i++) {
			if (expression.contains(i + "(") || expression.contains(")" + i)) {
				throw new SnapException(ApplicationConstant.ERROR_MSG);
			}
		}

		return expression;
	}

	/**
	 * Evaluate Expression
	 * 
	 * @param expression
	 * @return
	 * @throws SnapException
	 */
	public double evaluateExpression(String expression) throws SnapException {
		char[] tokens = expression.toCharArray();

		Stack<Double> operands = new Stack<Double>();
		Stack<Character> operators = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i] == ' ')
				continue;

			if (tokens[i] >= '0' && tokens[i] <= '9') {
				StringBuffer sbuf = new StringBuffer();
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				double oprn = Double.parseDouble(sbuf.toString());
				if (oprn >= 0 && oprn <= 9) {
					operands.push(Double.parseDouble(sbuf.toString()));
				} else {
					throw new SnapException(ApplicationConstant.ERROR_MSG);
					// not valid number
				}
			} else if (tokens[i] == '(')
				operators.push(tokens[i]);

			else if (tokens[i] == ')') {
				while (operators.peek() != '(')
					operands.push(calculateOp(operators.pop(), operands.pop(), operands.pop()));
				operators.pop();
			}

			// for operator.
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '^') {
				while (!operators.empty() && hasPrecedence(tokens[i], operators.peek()))
					operands.push(calculateOp(operators.pop(), operands.pop(), operands.pop()));
				operators.push(tokens[i]);
			}
		}

		while (!operators.empty())
			operands.push(calculateOp(operators.pop(), operands.pop(), operands.pop()));

		return operands.pop();
	}

	/**
	 * return true if op2 has higher or same precedence as op1
	 * 
	 * @param op1
	 * @param op2
	 * @return
	 */
	public static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')' || op1 == '^')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @param op
	 * @param b
	 * @param a
	 * @return
	 */
	public static double calculateOp(char op, double b, double a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '^':
			int i = (int) Math.pow(a, b);
			return i;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}

}