package org.kde9.cpu;

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
		if (s.isReady_Mem() && !s.isIslwswOut_EXE())
			pcs[0] = s.getPC_PC();
		else
			pcs[0] = -1;
		if (s.getInsOut_IF() != NOP_INS)
			pcs[1] = s.getPCOut_IF();
		else
			pcs[1] = -1;
		pcs[2] = s.getPCOut_ID();
		pcs[3] = s.getPCOut_EXE();
		pcs[4] = s.getPCOut_MEM();
	}
	
	private void needStop() {
		if(pcs[loc] == pc)
			goon = false;
	}
	
	public FPGA() {
		cpu = new CPU();
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
			currentPc = 0;
			count = 0;
			pcs = new int[] {-1, -1, -1, -1, -1};
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
