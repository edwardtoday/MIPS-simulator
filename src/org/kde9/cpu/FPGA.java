package org.kde9.cpu;

import java.util.HashMap;

import org.kde9.util.Constants;
import org.kde9.view.Factory;

public class FPGA 
implements Constants {
	private CPU cpu;
	private Thread thread;
	
	private int circle = 0;
	private boolean goon = true;
	private boolean reset = false;
	private int pc;
	private int loc;
	private int currentPc;
	private boolean runto = false;
	
	private HashMap<Integer, Integer> pc2circle;
	private HashMap<Integer, Integer> whichPart;
	
	public HashMap<Integer, Integer> getWhichPart() {
		return whichPart;
	}

	public HashMap<Integer, Integer> getPc2circle() {
		return pc2circle;
	}

	public int getCurrentPc() {
		return currentPc;
	}

	private int count = 0;
	
	public int getCount() {
		return count;
	}

	private int[] pcs = new int[] {-1, -1, -1, -1, -1};
	
	public int[] getPcs() {
		return pcs;
	}

	private void checkPcs() {
		Signals s = SignalPool.getCurrentSignals();
		currentPc = s.getPC_PC();
		if (s.isReady_Mem()) {// && !s.isIslwswOut_EXE())
			pcs[0] = s.getPC_PC();
			pc2circle.put(s.getPC_PC(), count);
			whichPart.put(s.getPC_PC(), 0);
		}
		else
			pcs[0] = -1;
		if (s.getInsOut_IF() != NOP_INS) {
			pcs[1] = s.getPCOut_IF();
			whichPart.put(s.getPCOut_IF(), 1);
		}
		else
			pcs[1] = -1;
		pcs[2] = s.getPCOut_ID();
		whichPart.put(s.getPCOut_ID(), 2);
		pcs[3] = s.getPCOut_EXE();
		whichPart.put(s.getPCOut_EXE(), 3);
		pcs[4] = s.getPCOut_MEM();
		whichPart.put(s.getPCOut_MEM(), 4);
	}
	
	private void needStop() {
		if(pcs[loc] == pc) {
			goon = false;
			runto = false;
		}
	}
	
	public FPGA() {
		cpu = new CPU();
		pc2circle = new HashMap<Integer, Integer>();
		whichPart = new HashMap<Integer, Integer>();
		thread = new Thread() {
			public void run() {
				while (true) {
					synchronized (this) {
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					while(circle > 0) {
						System.out.println("reset: " + reset);
						cpu.circle(reset);
						checkPcs();
						count++;
						if(reset) {
							reset = false;
							pcs = new int[] {-1, -1, -1, -1, -1};
							count = 0;
						}
						circle--;
					}
					if(circle < 0){
						while(goon) {
							if(!cpu.circle(reset))
								break;
							checkPcs();
							if(runto)
								needStop();
							count++;
							if(reset) {
								reset = false;
								pcs = new int[] {-1, -1, -1, -1, -1};
								count = 0;
							}
							Factory.getEdit().setCircle(count);
							Factory.getEdit().setPc(currentPc);
						}
						if(!goon)
							goon = true;
					}
					Factory.getMain().updata();
				}
			}
		};
		thread.start();
	}

	public void run(int circle, boolean reset) {
		this.circle = circle;
		this.reset = reset;
		if(reset) {
//			run(1);
			currentPc = 0;
			count = 0;
			pcs = new int[] {-1, -1, -1, -1, -1};
			circle = 0;
			goon = true;
//			private int pc;
//			private int loc;
			currentPc = 0;
			UnitPool.getRegisters().clear();
			pc2circle.clear();
			whichPart.clear();
//			reset = false;
		}
		synchronized (thread) {
			thread.notify();
		}
	}
	
	public void run(int circle) {
		this.circle = circle;
		synchronized (thread) {
			thread.notify();
		}
	}
	
	public void run(int pc, int loc) {
		goon = true;
		this.pc = pc;
		this.loc = loc;
		this.circle = -1;
		runto = true;
		synchronized (thread) {
			thread.notify();
		}
	}
	
	public void stop() {
		goon = false;
	}
	
	public int getCircle() {
		return circle;
	}

	public void setCircle(int circle) {
		this.circle = circle;
	}

	public boolean isGoon() {
		return goon;
	}

	public void setGoon(boolean goon) {
		this.goon = goon;
	}

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}
}
