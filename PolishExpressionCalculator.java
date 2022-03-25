import java.io.*;
import java.util.*;

public class PolishExpressionCalculator {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Polish expressions in the format \"{PRE/POST} {EXPR}\" and enter \"done\" when finished.\nex: \"POST 7 5 * 9 6 - - -5 +\"\n");
		List<String> exprs = new ArrayList<>();
		String kb = input.nextLine();
		while(!kb.equals("done")) {
			exprs.add(kb);
			kb = input.nextLine();
		}
		
		int numExpr = exprs.size();
		int index = 1;
		while(index <= numExpr) {
			String[] line = exprs.get(index-1).split(" ");
			String op = line[0];
			List<String> vals = new ArrayList<>();
			for(int i = 1; i < line.length; i++) {
				vals.add(line[i]);
			}
			
			int res;
			if(op.equals("PRE")) {
				res = Integer.parseInt(PRE(vals).get(0));
			} else {
				res = Integer.parseInt(POST(vals).get(0));
			}
			System.out.printf("Expression #%d: %d\n",index++,res);
		}

	}
	
	public static List<String> PRE(List<String> expr) {
		
		if(expr.size()==1) return expr;
		
		for(int i = 0; i < expr.size()-2; i++) {
			if("+-*/^".contains(expr.get(i)) && isDigit(expr.get(i+1)) && isDigit(expr.get(i+2))) {
				String op = expr.get(i);
				int d1 = Integer.parseInt(expr.get(i+1));
				int d2 = Integer.parseInt(expr.get(i+2));
				
				int res;
				if(op.equals("+")) {
					res = d1+d2;
				} else if(op.equals("-")) {
					res = d1-d2;
				} else if(op.equals("*")) {
					res = d1*d2;
				} else if(op.equals("^")) {
					res = (int)Math.pow(d1,d2);
				} else {
					res = d1/d2;
				}
				
				expr.remove(i);
				expr.remove(i);
				expr.remove(i);
				expr.add(i, Integer.toString(res));
				
				break;
			}
		}
		
		return PRE(expr);
	}

	public static List<String> POST(List<String> expr) {
		
		if(expr.size()==1) return expr;
		
		for(int i = 0; i < expr.size()-2; i++) {
			if("+-*/^".contains(expr.get(i+2)) && isDigit(expr.get(i+1)) && isDigit(expr.get(i))) {
				String op = expr.get(i+2);
				int d1 = Integer.parseInt(expr.get(i));
				int d2 = Integer.parseInt(expr.get(i+1));
				
				int res;
				if(op.equals("+")) {
					res = d1+d2;
				} else if(op.equals("-")) {
					res = d1-d2;
				} else if(op.equals("*")) {
					res = d1*d2;
				} else if(op.equals("^")) {
					res = (int)Math.pow(d1,d2);
				} else {
					res = d1/d2;
				}
				
				expr.remove(i);
				expr.remove(i);
				expr.remove(i);
				expr.add(i, Integer.toString(res));
				
				break;
			}
		}
		
		
		return POST(expr);
	}
	
	public static boolean isDigit(String dig) {
		try {
			int num = Integer.parseInt(dig);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

}