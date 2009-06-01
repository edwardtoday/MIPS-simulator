package org.kde9.cpu;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Vector;

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
	private int insCount = 0;

	private int instemp = -1;
	
	private HashMap<Integer, Integer> pc2circle;
	private HashMap<Integer, Integer> whichPart;
	
	private Vector<Vector<Integer>> conflict;
	private int conflictSum = 0;
	
	private Vector<Vector<Integer>> pause;
	private int pauseSum = 0;

	public int getInsCount() {
		return insCount;
	}
	
	public Vector<Vector<Integer>> getPause() {
		return pause;
	}

	public int getPauseSum() {
		return pauseSum;
	}
	
	public Vector<Vector<Integer>> getConflict() {
		return conflict;
	}

	public int getConflictSum() {
		return conflictSum;
	}

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
		if(s.getPCOut_MEM() != instemp && s.getPCOut_MEM() != -1) {
			insCount++;
			instemp = s.getPCOut_MEM();
		}
		//if (s.isReady_Mem()) {// && !s.isIslwswOut_EXE())
			pcs[0] = s.getPC_PC();
			pc2circle.put(s.getPC_PC(), count);
			whichPart.put(s.getPC_PC(), 0);
//		}
//		else
//			pcs[0] = -1;
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
		if(s.getTChoALU1_T() == 2 || s.getTChoALU1_T() == 3) {
			Vector<Integer> c = new Vector<Integer>();
			c.add(s.getTChoALU1_T());
			c.add(pcs[2]);
			if(s.getTChoALU1_T() == 2) {
				c.add(pcs[3]);
			} else {
				c.add(pcs[4]);
			}
			c.add(s.getRegAddrOut1_ID());
			c.add(count);
			c.add(currentPc);
			conflict.add(c);
			conflictSum++;
		}
		if(!s.isCChoALUOut2_ID() && (s.getTChoALU2_T() == 2 || s.getTChoALU2_T() == 3)) {
			Vector<Integer> c = new Vector<Integer>();
			c.add(s.getTChoALU2_T());
			c.add(pcs[2]);
			if(s.getTChoALU2_T() == 2) {
				c.add(pcs[3]);
			} else {
				c.add(pcs[4]);
			}
			c.add(s.getRegAddrOut2_ID());
			c.add(count);
			c.add(currentPc);
			conflict.add(c);
			conflictSum++;
		}
		int pauseadd = 0;
		if(s.isIslwswOut_EXE()) {
			Vector<Integer> c = new Vector<Integer>();
			c.add(count);
			c.add(currentPc);
			if (s.isReady_Mem()) {
				c.add(1);
				c.add(pcs[3]);
				pauseadd = 1;
				pause.add(c);
			} else {
				c.add(3);
				c.add(pcs[3]);
				pauseadd = 1;
				pause.add(c);
			}
		}
		if(!s.isReady_Mem()) {
			Vector<Integer> c = new Vector<Integer>();
			c.add(count);
			c.add(currentPc);
			if(s.isIslwswOut_EXE()) {
//				c.add(3);
//				c.add(pcs[3]);
//				pauseadd = 1;
//				pause.add(c);
			} else {//if(!(s.isHold_Stop() && s.getInsOut_IF() != NOP_INS)){
				c.add(2);
				c.add(pcs[0]);
				pauseadd = 1;
				pause.add(c);
			}
		}
		if(s.isIslwsw_Ctrl() && s.isHold_Stop() && s.getInsOut_IF() != NOP_INS) {
			Vector<Integer> c = new Vector<Integer>();
			c.add(count);
			c.add(currentPc);
			c.add(0);
			c.add(pcs[1]);
			if(pauseadd != 0)
				c.add(0);
			pause.add(c);
			pauseadd = 2;
		}
		pauseSum += pauseadd;
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
		conflict = new Vector<Vector<Integer>>();
		pause = new Vector<Vector<Integer>>();
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
								count = 0;
							}
							Factory.getEdit().setCircle(count);
							Factory.getEdit().setPc(insCount);
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
			insCount = 0;
			instemp = -1;
//			private int pc;
//			private int loc;
			currentPc = 0;
			UnitPool.getRegisters().clear();
			try {
				pc2circle.clear();
				whichPart.clear();
				conflict.clear();
			} catch (ConcurrentModificationException e) {
			}
			conflictSum = 0;
			pause.clear();
			pauseSum = 0;
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
