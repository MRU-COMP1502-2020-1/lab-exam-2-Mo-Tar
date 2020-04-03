package lsystems;

/**
 * 
 * A base class for L-System Rules. Each rule has a matching character
 * and an array of chars that should be produced when that match is found. 
 * 
 * @author tjkendon
 *
 */
public abstract class LRule {

	
	/**
	 * 
	 * Returns the character which this rules matches
	 * 
	 * @return the character the rule matches
	 */
	public abstract char getMatch();
	
	/**
	 * 
	 * Returns the array of characters produced by the rule
	 * 
	 * @return the array of characters produced
	 */
	public abstract char[] getBody();
	
}
