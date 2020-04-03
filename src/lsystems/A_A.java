package lsystems;

public class A_A extends LRule {

	@Override
	public char getMatch() {		
		return 'A';
	}

	@Override
	public char[] getBody() {
		return  new char[] {'A'};
	}

}
