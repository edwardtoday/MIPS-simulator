package org.kde9.alu;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.kde9.cpu.SignalPool;
import org.kde9.cpu.Signals;
import org.kde9.pcunit.PCUnit;
import org.kde9.view.Factory;

public class ALU {
	Signals signal;
	Signals next;
	
	// in
	int a;
	int b;
	int q;
	int cin;
	
	// out
	int ret;
	boolean t = false;
	
	public void start() {
		signal = SignalPool.getCurrentSignals();
		next = SignalPool.getNextSignals();
		check();
		run();
		set();
	}
	
	public void check() {
		a = next.getA_CALU1();
		b = next.getB_CALU2();
		q = next.getALUCtrlOut_ID();
		cin = 0;
	}
	
	private void run() {
		switch(q) {
		case 0:
			ret = 0;
			break;
		case 1:
			ret = a + b + cin;
			break;
		case 2:
			ret = a - b - cin;
			break;
		case 3:
			ret = a & b;
			break;
		case 4:
			ret = a | b;
			break;
		case 5:
			ret = a^b;
			break;
		case 6:
			ret = ~a;
			break;
		case 7:
			ret = a << (b & 0x1f);
			break;
		case 8:
			ret = a >>> (b & 0x1f);
			break;
		case 9:
			ret = a >> (b & 0x1f);
			break;
		case 10:
			ret = (a==b) ? 1:0;
			break;
		case 11:
			ret = (a==b) ? 0:1;
			break;
		case 12:
			ret = (a<b) ? 1:0;
			break;
		case 13:
			ret = (a>b) ? 1:0;
			break;
		case 14:
			ret = (a>b) ? 0:1;
			break;
		case 15:
			ret = a*b;
			break;
		case 16:
			try {
				ret = a/b;
			} catch (ArithmeticException e) {
				JOptionPane.showMessageDialog(Factory.getMain(), "·¢Éú³ýÒÔ0´íÎó");
			}
			break;
		case 17:
			ret = b;
			break;
		default:
			ret = 0;
			t = false;
		}
	}
	
	private void set() {
		next.setRet_ALU(ret);
		next.setT_ALU(t);
	}
	
	public static void main(String args[]) {
		ALU p = new ALU();
		SignalPool.a.setA_CALU1(22);
		SignalPool.a.setB_CALU2(42);
		SignalPool.a.setALUCtrlOut_ID(3);
		p.start();
		System.out.println(SignalPool.b.getRet_ALU());
		SignalPool.next();
		p.start();
		System.out.println(SignalPool.a.getRet_ALU());
	}
}
