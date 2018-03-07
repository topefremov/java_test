package io.github.topefremov.javatest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyStringChangerTest {
	
	@Test
	public void threadTest() {
		Lock lock = new Lock();
		MyString myString = new MyString();
		ExecutorService es = Executors.newFixedThreadPool(2);
		es.execute(new MyStringChanger(lock, 1, 1, 2, 100, myString));
		es.execute(new MyStringChanger(lock, 2, 2, 1, 100, myString));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		es.shutdownNow();
		assertTrue(myString.getString().contains("11111111112222222222"));
	}
	
}
