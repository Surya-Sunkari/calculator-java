package Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator {

	String expression;
	
	public Evaluator(String expression) {
		this.expression = expression;
	}
	
	public double evaluate() {
		return calculate(expression);
	}

	public static double calculate(String expression) {
		expression = expression.replaceAll(" ", "");
		if(!expression.contains("n")) {
			// attempt to deal with negatives
			if(expression.startsWith("-")) expression = expression.replaceFirst("-", "n");
			expression = expression.replaceAll("\\+-", "+n").replaceAll("--", "-n").replaceAll("\\*-", "*n").replaceAll("/-", "/n").replaceAll("\\(-", "(n");
//			System.out.println(expression);
		}
		
		Pattern mulParPattern = Pattern.compile("(\\d)\\(");
		Matcher mulParMatch = mulParPattern.matcher(expression);
		while(mulParMatch.find()) {
			String digit = mulParMatch.group(1);
			expression = expression.replace("" + digit + "(", "" + digit + "*(");
		}
		
		
		//separate between expr with parentheses and expr without
		if(!expression.contains("(")) {
			return condense(expression);
		}
		String exprWithoutParen = expression;
		while(exprWithoutParen.contains("(")) {
			exprWithoutParen = expression;
			exprWithoutParen = removeParen(exprWithoutParen);
			expression = exprWithoutParen.replaceAll("n-", "").replaceAll("nn", "");
		}
		return condense(expression);
	}

	public static String removeParen(String expression) {
		int firstIndex = expression.lastIndexOf("(");
		int lastIndex = expression.substring(firstIndex).indexOf(")") + firstIndex;
//		System.out.println(expression);
		String inside = Double.toString(condense(expression.substring(firstIndex + 1, lastIndex)));
		if(Double.parseDouble(inside) < 0) {
			double temp = Double.parseDouble(inside);
			inside = "n" + Math.abs(Double.parseDouble(inside)); 
		}
//		System.out.println(inside);
		return expression.substring(0, firstIndex)
				+ inside + expression.substring(lastIndex + 1);
	}

	public static double condense(String expr) {
		int mIndex = expr.indexOf("*") == -1 ? expr.length() : expr.indexOf("*");
		int dIndex = expr.indexOf("/") == -1 ? expr.length() : expr.indexOf("/");
		int aIndex = expr.indexOf("+") == -1 ? expr.length() : expr.indexOf("+");
		int sIndex = expr.indexOf("-") == -1 ? expr.length() : expr.indexOf("-");		
		
		if(mIndex < dIndex) {
			Pattern multiPattern = Pattern.compile("(n?\\d+\\.?\\d*)(\\*)(n?\\d+\\.?\\d*)");
			Matcher multiMatch = multiPattern.matcher(expr);
			String selectedExpr = "";
			if(multiMatch.find()) selectedExpr = multiMatch.group();
			double num1 = multiMatch.group(1).contains("n") ? -1 * Double.parseDouble(multiMatch.group(1).substring(1)) : Double.parseDouble(multiMatch.group(1));
			double num2 = multiMatch.group(3).contains("n") ? -1 * Double.parseDouble(multiMatch.group(3).substring(1)) : Double.parseDouble(multiMatch.group(3));
			double replacement = num1*num2;
			expr = expr.replace(selectedExpr, "" + (replacement<0?"n"+Math.abs(replacement):replacement));
			return condense(expr);
		} else if(dIndex < mIndex) {
			Pattern divPattern = Pattern.compile("(n?\\d+\\.?\\d*)(/)(n?\\d+\\.?\\d*)");
			Matcher divMatch = divPattern.matcher(expr);
			String selectedExpr = "";
			if(divMatch.find()) selectedExpr = divMatch.group();
//			System.out.println(divMatch.group(1));
//			System.out.println(divMatch.group(3));
			double num1 = divMatch.group(1).contains("n") ? -1 * Double.parseDouble(divMatch.group(1).substring(1)) : Double.parseDouble(divMatch.group(1));
			double num2 = divMatch.group(3).contains("n") ? -1 * Double.parseDouble(divMatch.group(3).substring(1)) : Double.parseDouble(divMatch.group(3));
			double replacement = num1/num2;
			expr = expr.replace(selectedExpr, "" + (replacement<0?"n"+Math.abs(replacement):replacement));
			return condense(expr);
		} else if(aIndex < sIndex) {
			Pattern addPattern = Pattern.compile("(n?\\d+\\.?\\d*)(\\+)(n?\\d+\\.?\\d*)");
			Matcher addMatch = addPattern.matcher(expr);
			String selectedExpr = "";
			if(addMatch.find()) selectedExpr = addMatch.group();
			double num1 = addMatch.group(1).contains("n") ? -1 * Double.parseDouble(addMatch.group(1).substring(1)) : Double.parseDouble(addMatch.group(1));
			double num2 = addMatch.group(3).contains("n") ? -1 * Double.parseDouble(addMatch.group(3).substring(1)) : Double.parseDouble(addMatch.group(3));
			double replacement = num1+num2;
			expr = expr.replace(selectedExpr, "" + (replacement<0?"n"+Math.abs(replacement):replacement));
			return condense(expr);
		} else if(sIndex < aIndex ){
			Pattern subPattern = Pattern.compile("(n?\\d+\\.?\\d*)(-)(n?\\d+\\.?\\d*)");
			Matcher subMatch = subPattern.matcher(expr);
			String selectedExpr = "";
			if(subMatch.find()) selectedExpr = subMatch.group();
			double num1 = subMatch.group(1).contains("n") ? -1 * Double.parseDouble(subMatch.group(1).substring(1)) : Double.parseDouble(subMatch.group(1));
			double num2 = subMatch.group(3).contains("n") ? -1 * Double.parseDouble(subMatch.group(3).substring(1)) : Double.parseDouble(subMatch.group(3));
			double replacement = num1-num2;
			expr = expr.replace(selectedExpr, "" + (replacement<0?"n"+Math.abs(replacement):replacement));
			return condense(expr);
		}
		
		if(!expr.contains("+") && !expr.contains("-") && !expr.contains("*") && !expr.contains("/")) {
			if(expr.startsWith("n")) {
				return -1 * Double.parseDouble(expr.substring(1));
			}
		}
		return Double.parseDouble(expr);
	}
}
