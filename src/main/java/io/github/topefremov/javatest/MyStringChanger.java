package io.github.topefremov.javatest;

public class MyStringChanger implements Runnable {
	private Lock lock;
	private int n;
	private int flag;
	private int nextFlag;
	private int milliseconds;
	private MyString myString;
	
	public MyStringChanger(Lock lock, int n, int flag, int nextFlag, int milliseconds, MyString myString) {
		super();
		this.lock = lock;
		this.n = n;
		this.flag = flag;
		this.nextFlag = nextFlag;
		this.milliseconds = milliseconds;
		this.myString = myString;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				while(true) {
					while(lock.flag != flag)
						lock.wait();
					for (int i = 0; i < 10; i++) {
						Thread.sleep(milliseconds);
						myString.add(n);
					}
						
					lock.flag = nextFlag;
					lock.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
