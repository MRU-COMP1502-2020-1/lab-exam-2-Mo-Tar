package lsystems;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		LRule r1 = new A_BC();
		LRule r2 = new B_A();
		LRule r3 = new C_B();
		
		Set<LRule> rules = new HashSet<>();
		rules.add(r1);
		rules.add(r2);
		rules.add(r3);

		char[] lineChar = {'A'};
		LLine line = new LLine(lineChar, rules);

		System.out.println("Step 0:" + line);
		try {
			for (int i = 1; i <= 10; i++) {

				line.process();

				System.out.println("Step " + i + ":" + line);
			}
		} catch (LSystemSymbolException e) {
			System.err.println("Not able to process LSystem line due to " + e.getSymbol());
			e.printStackTrace();
		} catch (LSystemLengthException e) {
			System.err.println("Not able to process LSystem due to no symbols");
			e.printStackTrace();
		}

	}

}
