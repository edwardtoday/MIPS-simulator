package org.kde9.cpu;

public class SignalPool {
	public static Signals a = new Signals();
	public static Signals b = new Signals();
	static boolean flag = true;
	
	public static Signals getCurrentSignals() {
		if(flag) {
			return a;
		} else {
			return b;
		}
	}
	
	public static Signals getNextSignals() {
		if(flag) {
			return b;
		} else {
			return a;
		}
	}
	
	public static void next() {
		if(flag)
			flag = false;
		else
			flag = true;
	}
}
