package org.kde9.util;

import java.util.Vector;

public class Functions {
	public static int[] memAddr(int addr, int size) {
		int[] temp = {0, size-1};
		int bound = ((size-1)/16)*16 + 16;
		int offset = (addr/16)*16;
		if(offset > bound/2) {
			temp[0] = offset - bound/2;
			temp[1] = offset + bound/2 - 1;
		} else
			temp[1] = bound - 1;
		return temp;
	}
	
}
