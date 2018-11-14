package com.capita.calculator.example.constant;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ApplicationConstant {
	public static String EXPRESSION = "4\n"
			+ "7+(6*5^2+3-4/2)\n"
			+ "7+(67(56*2))\n"
			+ "8*+7\n"
			+ "(8*5/8)-(3/1)-5";
	public static String ERROR_MSG = "INVALID EXPRESSION";			
	public static NumberFormat NF = new DecimalFormat("##.##");
	public static char[] OPERATORS = {'+','-','*','/','^',')','('};
	public static char[] VALIDATE_FOR_OPERATORS = {'*','/'};
	public static int MIN_VAL = 0;
	public static int MAX_VAL = 0;
}
