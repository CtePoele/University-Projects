import java.util.Arrays;

public class BinaryCalculation {
	static double num1;
	static double num2;
	
	public static void calcBin(String numString){
		num1 = Double.parseDouble(numString);
		num2 = num1;
		int[] binNum = new int[4];
		String binString;
//=====		
		if ((num2 / 922337203685477599L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 922337203685477599L;
		}	
		
		if ((num2 / 4611686018427387900L) >= 1){
			binNum[1] = 1;	
			num2 = num2 - 4611686018427387900L;
		}
//bin16		
		if ((num2 / 2305843009213694000L) >= 1){
			binNum[2] = 1;
			num2 = num2 - 2305843009213694000L;
		}
		
		if ((num2 / 1152921504606846980L) >= 1){
			binNum[3] = 1;
			num2 = num2 - 1152921504606846980L;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(16, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====		
		if ((num2 / 576460752303423490L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 576460752303423490L;
		}
		
		if ((num2 / 288230376151711740L) >= 1){
			binNum[1] = 1;
			num2 = num2 - 288230376151711740L;
		}
//bin15		
		if ((num2 / 144115188075855872L) >= 1){
			binNum[2] = 1;
			num2 = num2 - 144115188075855872L;
		}
		
		if ((num2 / 72057594037927936L) >= 1){
			binNum[3] = 1;
			num2 = num2 - 72057594037927936L;
		}
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(15, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====				
		if ((num2 / 36028797018963968L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 36028797018963968L;
		}
		
		if ((num2 / 18014398509481984L) >= 1){
			binNum[1] = 1;
			num2 = num2 - 18014398509481984L;
		}
//bin14		
		if ((num2 / 9007199254740992L) >= 1){
			binNum[2] = 1;
			num2 = num2 - 9007199254740992L;
		}
		
		if ((num2 / 4503599627370496L) >= 1){
			binNum[3] = 1;
			num2 = num2 - 4503599627370496L;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(14, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 2251799813685248L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 2251799813685248L;
		}
		
		if ((num2 / 1125899906842624L) >= 1){
			binNum[1] = 1;
			num2 = num2 - 11258999068426248L;
		}
//bin13		
		if ((num2 / 562949953421312L) >= 1){
			binNum[2] = 1;
			num2 = num2 - 562949953421312L;
		}
		
		if ((num2 / 281474976710656L) >= 1){
			binNum[3] = 1;
			num2 = num2 - 281474976710656L;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(13, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 140737488355328L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 140737488355328L;
		}
		
		if ((num2 / 70368744177664L) >= 1){
			binNum[1] = 1;
			num2 = num2 - 70368744177664L;
		}
//bin12		
		if ((num2 / 35184372088832L) >= 1){
			binNum[2] = 1;
			num2 = num2 - 35184372088832L;
		}
		
		if ((num2 / 17592186044416L) >= 1){
			binNum[3] = 1;
			num2 = num2 - 17592186044416L;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(12, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 8796093022208L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 8796093022208L;
		}
		
		if ((num2 / 4398046511104L) >= 1){
			binNum[1] = 1;
			num2 = num2 - 4398046511104L;
		}
//bin11		
		if ((num2 / 2199023255552L) >= 1){
			binNum[2] = 1;
			num2 = num2 - 2199023255552L;
		}
		
		if ((num2 / 1099511627776L) >= 1){
			binNum[3] = 1;
			num2 = num2 - 1099511627776L;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(11, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 549755813888L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 549755813888L;
		}
		
		if ((num2 / 274877906944L) >= 1){
			binNum[1] = 1;
			num2 = num2 - 274877906944L;
		}
//bin10		
		if ((num2 / 137438953472L) >= 1){
			binNum[2] = 1;
			num2 = num2 - 137438953472L;
		}
		
		if ((num2 / 68719476736L) >= 1){
			binNum[3] = 1;
			num2 = num2 - 68719476736L;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(10, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 34359738368L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 34359738368L;
		}
		
		if ((num2 / 17179869184L) >= 1){
			binNum[1] = 1;
			num2 = num2 - 17179869184L;
		}
//bin9		
		if ((num2 / 8589934592L) >= 1){
			binNum[2] = 1;
			num2 = num2 - 8589934592L;
		}

		if ((num2 / 4294967296L) >= 1){
			binNum[3] = 1;
			num2 = num2 - 4294967296L;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(9, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 2147483648L) >= 1){
			binNum[0] = 1;
			num2 = num2 - 2147483648L;
		}
		
		if ((num2 / 1073741824) >= 1){
			binNum[1] = 1;
			num2 = num2 - 1073741824;
		}
//bin8		
		if ((num2 / 536870912) >= 1){
			binNum[2] = 1;
			num2 = num2 - 536870912;
		}

		if ((num2 / 268435456) >= 1){
			binNum[3] = 1;
			num2 = num2 - 268435456;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(8, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 134217728) >= 1){
			binNum[0] = 1;
			num2 = num2 - 134217728;
		}
		
		if ((num2 / 67108864) >= 1){
			binNum[1] = 1;
			num2 = num2 - 67108864;
		}
//bin7		
		if ((num2 / 33554432) >= 1){
			binNum[2] = 1;
			num2 = num2 - 33554432;
		}

		if ((num2 / 16777216) >= 1){
			binNum[3] = 1;
			num2 = num2 - 16777216;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(7, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 8388608) >= 1){
			binNum[0] = 1;
			num2 = num2 - 8388608;
		}
		
		if ((num2 / 4194304) >= 1){
			binNum[1] = 1;
			num2 = num2 - 4194304;
		}
//bin6		
		if ((num2 / 2097152) >= 1){
			binNum[2] = 1;
			num2 = num2 - 2097152;
		}

		if ((num2 / 1048576) >= 1){
			binNum[3] = 1;
			num2 = num2 - 1048576;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(6, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 524288) >= 1){
			binNum[0] = 1;
			num2 = num2 - 524288;
		}
		
		if ((num2 / 262144) >= 1){
			binNum[1] = 1;
			num2 = num2 - 262144;
		}
//bin5		
		if ((num2 / 131072) >= 1){
			binNum[2] = 1;
			num2 = num2 - 131072;
		}

		if ((num2 / 65536) >= 1){
			binNum[3] = 1;
			num2 = num2 - 65536;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(5, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 32768) >= 1){
			binNum[0] = 1;
			num2 = num2 - 32768;
		}
		
		if ((num2 / 16384) >= 1){
			binNum[1] = 1;
			num2 = num2 - 16384;
		}
//bin4		
		if ((num2 / 8192) >= 1){
			binNum[2] = 1;
			num2 = num2 - 8192;
		}

		if ((num2 / 4096) >= 1){
			binNum[3] = 1;
			num2 = num2 - 4096;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(4, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 2048) >= 1){
			binNum[0] = 1;
			num2 = num2 - 2048;
		}
		
		if ((num2 / 1024) >= 1){
			binNum[1] = 1;
			num2 = num2 - 1024;
		}
//bin3		
		if ((num2 / 512) >= 1){
			binNum[2] = 1;
			num2 = num2 - 512;
		}

		if ((num2 / 256) >= 1){
			binNum[3] = 1;
			num2 = num2 - 256;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(3, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 128) >= 1){
			binNum[0] = 1;
			num2 = num2 - 128;
		}
		
		if ((num2 / 64) >= 1){
			binNum[1] = 1;
			num2 = num2 - 64;
		}
//bin2		
		if ((num2 / 32) >= 1){
			binNum[2] = 1;
			num2 = num2 - 32;
		}

		if ((num2 / 16) >= 1){
			binNum[3] = 1;
			num2 = num2 - 16;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(2, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
//=====
		if ((num2 / 8) >= 1){
			binNum[0] = 1;
			num2 = num2 - 8;
		}
		
		if ((num2 / 4) >= 1){
			binNum[1] = 1;
			num2 = num2 - 4;
		}
//bin1
		if ((num2 / 2) >= 1){
			binNum[2] = 1;
			num2 = num2 - 2;
		}
		
		if ((num2 / 1) >= 1){
			binNum[3] = 1;
			num2 = num2 - 1;
		}
		
		binString = Arrays.toString(binNum);
		ButtonPanel.binAssign(1, binString);
		for(int i = 0; i < 4; i++)
			binNum[i] = 0;
	}
}