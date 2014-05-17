package genetics.utils;

public class MathUtils {
	public static long getDecFromChar(char c) {
		return ((int)c) - 48;
	}
	
	public static long binToDec(String binary) {
		long dec = 0;
		
		for(int i=0; i<binary.length(); i++) {
			long val = getDecFromChar(binary.charAt(i));			
			dec += val * Math.pow(2, (binary.length() - 1) - i);
		}
		return dec;
	}
	
	public static double getRealNumber(long min, long interval, String binary) {
		long binToDec = binToDec(binary);
		
		return ((double)min + ((double)interval * ((double)binToDec / (double)Math.pow(2, binary.length()))));
	}
}
