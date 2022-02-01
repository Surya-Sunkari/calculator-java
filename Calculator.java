package Calculator;

import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*; 

public class Calculator implements ActionListener {
	
	//https://www.thecrazyprogrammer.com/2014/06/program-to-create-calculator-using-java-swing.html
	
	JFrame frame;
	JTextField t;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, div, mul, sub, add, oppar, clpar, dec, clr, ent;
	boolean prevAns = false;
	double previous = 0;
	
	
	Calculator()
	{
		frame = new JFrame("Calculator");
		t = new JTextField();
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		b0 = new JButton("0");
		div = new JButton("/");
		mul = new JButton("*");
		sub = new JButton("-");
		add = new JButton("+");
		oppar = new JButton("(");
		clpar = new JButton(")");
		dec = new JButton(".");
		clr = new JButton("CLEAR");
		ent = new JButton("ENTER");
		
		//width 50 and length 40, gap between is 20, side gap is 30
		//x y length width
		
		//row 1
		t.setBounds(30, 30, 280, 80);
		b7.setBounds(30, 130, 50, 40);
		b8.setBounds(106, 130, 50, 40);
		b9.setBounds(182, 130, 50, 40);
		div.setBounds(258, 130, 50, 40);
		
		//row 2
		b4.setBounds(30, 200, 50, 40);
		b5.setBounds(106, 200, 50, 40);
		b6.setBounds(182, 200, 50, 40);
		mul.setBounds(258, 200, 50, 40);
		
		//row 3
		b1.setBounds(30, 270, 50, 40);
		b2.setBounds(106, 270, 50, 40);
		b3.setBounds(182, 270, 50, 40);
		sub.setBounds(258, 270, 50, 40);
		
		//row 4
		b0.setBounds(30, 340, 50, 40);
		oppar.setBounds(106, 340, 50, 40);
		clpar.setBounds(182, 340, 50, 40);
		add.setBounds(258, 340, 50, 40);
		
		//row 5
		dec.setBounds(30, 410, 50, 40);
		clr.setBounds(106, 410, 94, 40);
		ent.setBounds(214, 410, 94, 40);
		
		frame.add(t);
		t.addActionListener(this);
		frame.add(b7);
		b7.addActionListener(this);
		frame.add(b8);
		b8.addActionListener(this);
		frame.add(b9);
		b9.addActionListener(this);
		frame.add(div);
		div.addActionListener(this);
		frame.add(b4);
		b4.addActionListener(this);
		frame.add(b5);
		b5.addActionListener(this);
		frame.add(b6);
		b6.addActionListener(this);
		frame.add(mul);
		mul.addActionListener(this);
		frame.add(b1);
		b1.addActionListener(this);
		frame.add(b2);
		b2.addActionListener(this);
		frame.add(b3);
		b3.addActionListener(this);
		frame.add(sub);
		sub.addActionListener(this);
		frame.add(b0);
		b0.addActionListener(this);
		frame.add(oppar);
		oppar.addActionListener(this);
		frame.add(clpar);
		clpar.addActionListener(this);
		frame.add(add);
		add.addActionListener(this);
		frame.add(dec);
		dec.addActionListener(this);
		frame.add(clr);
		clr.addActionListener(this);
		frame.add(ent);
		ent.addActionListener(this);
		
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setSize(345, 520);
		frame.setLocation(1920/2-345/2, 1080/2-520/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
		
		
		

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}
			t.setText(t.getText()+ "1");
		} else if(e.getSource()==b2) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}
			t.setText(t.getText()+ "2");
		} else if(e.getSource()==b3) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "3");
		} else if(e.getSource()==b4) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "4");
		} else if(e.getSource()==b5) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "5");
		} else if(e.getSource()==b6) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "6");
		} else if(e.getSource()==b7) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "7");
		} else if(e.getSource()==b8) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "8");
		} else if(e.getSource()==b9) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "9");
		} else if(e.getSource()==b0) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "0");
		} else if(e.getSource()==div) {
			if(prevAns) {
				t.setText(Double.toString(previous) + " / ");
				prevAns = false;
			}
			else {
				t.setText(t.getText()+ " / ");
			}
		} else if(e.getSource()==mul) {
			if(prevAns) {
				t.setText(Double.toString(previous) + " * ");
				prevAns = false;
			}
			else {
				t.setText(t.getText()+ " * ");
			}
		} else if(e.getSource()==sub) {
			if(prevAns) {
				t.setText(Double.toString(previous) + " - ");
				prevAns = false;
			}
			else {
				t.setText(t.getText()+ " - ");
			}
		} else if(e.getSource()==add) {
			if(prevAns) {
				t.setText(Double.toString(previous) + " + ");
				prevAns = false;
			}
			else {
				t.setText(t.getText()+ " + ");
			}
		} else if(e.getSource()==dec) {
			t.setText(t.getText()+ ".");
		} else if(e.getSource()==oppar) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ "(");
		} else if(e.getSource()==clpar) {
			if(prevAns) {
				t.setText("");
				prevAns = false;
			}			t.setText(t.getText()+ ")");
		} else if(e.getSource()==clr) {
			t.setText("");
			prevAns = false;
		} else if(e.getSource()==ent) {
			Evaluator eval = new Evaluator(t.getText());
			double result = eval.evaluate();
			t.setText(t.getText() + " = " + result);
			prevAns = true;
			previous = result;
			if(Double.isNaN(result)) {
				previous = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		new Calculator();
	}

}
