package io.github.topefremov.javatest;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyStringTest {
	
	@Test
	public void myStringTest() {
		MyString myString = new MyString();
		
		for(int i = 0; i < 25; i ++) {
			myString.add(i);
		}
		assertEquals("0123456789101112131415161718192021222324", myString.getString());
	}
	
}
