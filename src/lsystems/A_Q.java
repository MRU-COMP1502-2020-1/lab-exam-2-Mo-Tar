package lsystems;

public class A_Q extends LRule {

	@Override
	public char getMatch() {
		return 'A';
	}

	@Override
	public char[] getBody() {
		return  new char[] {'Q'};
	}

}
