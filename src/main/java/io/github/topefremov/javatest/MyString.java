package io.github.topefremov.javatest;

public class MyString {
	private StringBuffer buffer;
	
	public MyString() {
		this.buffer = new StringBuffer();
	}
	
	public void add(int c) {
		buffer.append(c);
	}
	
	public String getString() {
		return buffer.toString();
	}
	
	public void clear() {
		buffer.setLength(0);
	}
}
