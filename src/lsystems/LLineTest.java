package lsystems;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class LLineTest {

	@Test
	public void testConstruction() {
		char[] test = {'T', 'E', 'S', 'T'};
		Set<LRule> rules = new HashSet<LRule>();
		LLine line = new LLine(test, rules);
	}
	
	@Test
	public void testToString() {
		char[] test = {'T', 'E', 'S', 'T'};
		Set<LRule> rules = new HashSet<LRule>();
		LLine line = new LLine(test, rules);
		assertEquals("Expected output TEST", "TEST", line.toString());
	}
	
	@Test
	public void testA_Aprocess() throws LSystemSymbolException, LSystemLengthException {
		char[] test = {'A'};
		Set<LRule> rules = new HashSet<LRule>();
		rules.add(new A_A());
		LLine line = new LLine(test, rules);
		line.process();
		assertEquals("Expected output A", "A", line.toString());
		
	}
	
	@Test
	public void testA_AAprocess() throws LSystemSymbolException, LSystemLengthException {
		char[] test = {'A'};
		Set<LRule> rules = new HashSet<LRule>();
		rules.add(new A_AA());
		LLine line = new LLine(test, rules);
		line.process();
		assertEquals("Expected output AA", "AA", line.toString());
		
	}
	
	@Test
	public void testA_BC() throws LSystemSymbolException, LSystemLengthException {
		char[] test = {'A'};
		Set<LRule> rules = new HashSet<LRule>();
		
		rules.add(new A_BC());
		rules.add(new B_A());
		rules.add(new C_B());

		LLine line = new LLine(test, rules);
		for (int i = 1; i <= 10; i++) {
			line.process();
		}
		assertEquals("Expected output ABBCBCABCAABBCAABABBC after 10 runs", 
				"ABBCBCABCAABBCAABABBC", 
				line.toString());
		
	}
	
	@Test
	public void testLSystemLengthException() {
		char[] test = {'A'};
		Set<LRule> rules = new HashSet<LRule>();
		rules.add(new A_X());
		LLine line = new LLine(test, rules);
		boolean caughtClean = false;
		try {
			line.process();
			line.process(); // exception raised this pass, because first still
			                // has line to process
		} catch (LSystemSymbolException e) {
		} catch (LSystemLengthException e) {
			caughtClean = true;
		}
		if (!caughtClean) {
			fail("Should have thrown LSystemLengthException");
		}
	}
	
	@Test
	public void testLSystemSymbolException() {
		char[] test = {'A'};
		Set<LRule> rules = new HashSet<LRule>();
		rules.add(new A_Q());
		LLine line = new LLine(test, rules);
		boolean caughtClean = false;
		try {
			line.process();
			line.process(); // exception raised this pass, because first still
			                // has line to process
		} catch (LSystemSymbolException e) {
			caughtClean = true;
		} catch (LSystemLengthException e) {
		}
		if (!caughtClean) {
			fail("Should have thrown LSystemSymbolException");
		}
	}

}
