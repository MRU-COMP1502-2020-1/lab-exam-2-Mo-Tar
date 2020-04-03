package lsystems;

public class B_A extends LRule {

	@Override
	public char getMatch() {
		return 'B';
	}

	@Override
	public char[] getBody() {
		return new char[] {'A'};
	}

}
