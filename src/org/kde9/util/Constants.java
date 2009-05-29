package org.kde9.util;

public interface Constants {
	static int MEM_INITIAL_VALUE = 0;
	
	static int REGISTER_INITIAL_VALUE = 0;
	
	static int NOP_INS = 0x8000;
	
	static String NEWLINE = System.getProperty("line.separator");
	
	static String DATA = "data";
	
	static int MEM_HIS_COUNT = 20;
	
	static int CACHE_SIZE = 128;
	
	// RegisterHeap
	static int RA_Reg = 14 ;    //RA
	static int T_Reg = 15 ;     //T
	static int ZERO_Reg = 8 ;   //ZERO
	static int SP_Reg = 13 ;    //SP
	static int PC_Reg = 9 ;     //PC
	static int IH_Reg = 10 ;    //IH
	
	// ALU
	static int Zero_ALU = 0;
	static int Add_ALU = 1 ;   //add
	static int Sub_ALU = 2 ;   //sub
	static int And_ALU = 3 ;   //and
	static int Or_ALU = 4 ;    //or
	static int Xor_ALU = 5 ;   //xor
	static int Not_ALU = 6 ;   //not
	
	// WE
	static boolean Able = true ;
	static boolean Unable= false ;
	
	// needStop | storePC | islwsw
	static boolean Yes = true;
	static boolean No = false;
	
	// CChoALU2
	static boolean Reg_ALU2 = false ;
	static boolean Im_ALU2 = true;
	
	// CChoRegWVal
	static int Mem_RegWVal = 0;
	static int ALU_RegWVal = 1;
	static int T_RegWval = 2;
	
	// CChoPCCtrl
	static int PC_PC = 0;
	static int RegVal_PC = 1;
	static int E0PC1PCIm = 2;
	static int E0PCIm1PC = 3;
}
