package lsystems;

public class A_AA extends LRule {

	@Override
	public char getMatch() {
		return 'A';
	}

	@Override
	public char[] getBody() {
		return  new char[] {'A','A'};
	}

}
