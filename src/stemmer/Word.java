package stemmer;

public class Word {
	private String raw;
	
	// Char array of the word to speed up some computations
	private char[] chars;
	
	// length of word, in letters
	private int length;
	
	// value of m in [C](VC){m}[V] format
	private int m;
	
	public Word(String raw) {
		this.raw = raw;
		this.length = raw.length();
		this.chars = raw.toCharArray();
		this.m = this.calculateMValue();
	}
	
	public String breakupWord() {
		// the [C] @ start of word
		String startC = "";
		
		int i = 0;
		while (i < length && isConsonant(chars[i])) {
			startC += chars[i++];
		}
		
		String middleM = "";
		String lastV = "";
		while (i < length) {
			String partV = "";
			while (i < length && isVowel(chars[i])) {
				partV += chars[i++];
			}
			String partC = "";
			while (i < length && isConsonant(chars[i])) {
				partC += chars[i++];
			}
			if (partC.length() == 0) {
				lastV = partV;
				break;
			}
			middleM += "{" + partV + partC + "}";
		}
		
		return "["+startC+"]"+middleM+"["+lastV+"]";
	}
	
	// Calculate the M value of the word.
	private int calculateMValue() {
		int i = 0;
		int m = 0;
		while (i < length && isConsonant(chars[i])) i++;
		
		while (i < length) {
			
			while (i < length && isVowel(chars[i])) i++;
			int c = 0;
			while (i < length && isConsonant(chars[i])) {
				i++;
				c++;
			}
			if (c != 0) m++;
		}
		return m;
	}
	
	public int getM() {
		return m;
	}
	
	// *d in specifications
	public boolean doubleConsonantEnding() {
		if (length < 2) return false;
		return isConsonant(chars[length-1]) && isConsonant(chars[length-2]);
	}
	
	public boolean endsIn(String ending) {
		return raw.endsWith(ending.toLowerCase());
	}
	
	public static boolean isVowel(char c) {
		return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
	}
	public static boolean isConsonant(char c) {
		return !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
	}
}
