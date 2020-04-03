package lsystems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LLine {


	char line[];
	Set<LRule> rules;


	public LLine (char[] start, Set<LRule> rules) {
		this.rules = rules;
		this.line = start;
	}

	public void process() throws LSystemSymbolException, LSystemLengthException {
		if (line.length == 0 ) throw new LSystemLengthException("The sequence must have atleast 1 character");

		symbolException();

		ArrayList<Character> charList = new ArrayList<Character>();

		for (char chars: line) {
			for(LRule rule: rules) {

				if ( chars == rule.getMatch()) {
					for (char c: rule.getBody())
						charList.add(c);
				}
			}
		}

		line = listToArray(charList);
	}

	private void symbolException() throws LSystemSymbolException {
		char invalid= 'Q';
		for(char c: line) {
			if ( c == invalid) {
				throw new LSystemSymbolException("That is not a valid symbol", c);
			}
		}
	}


	private char[] listToArray(List<Character> list) {
		char[] newChars = new char[list.size()];
		for (int i = 0; i < list.size(); i++) {
			newChars[i] = list.get(i);
		}
		return newChars;
	}

	public String toString() {

		return new String(line);
	}

}
