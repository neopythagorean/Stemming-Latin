package stemmer;

public class Word {
	private String raw;
	
	// Char array of the word to speed up some computations
	private char[] chars;
	
	public Word(String raw) {
		this.raw = raw;
		this.chars = raw.toCharArray();
	}
	
	public String breakupWord() {
		// the [C] @ start of word
		String startC = "";
		
		int i = 0;
		while (isConsonant(chars[i])) {
			startC += chars[i++];
		}
		
		String middleM = "";
		String lastV = "";
		while (i < raw.length()) {
			String partV = "";
			while (i < chars.length && isVowel(chars[i])) {
				partV += chars[i++];
			}
			String partC = "";
			while (i < chars.length && isConsonant(chars[i])) {
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
	
	public static boolean isVowel(char c) {
		return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
	}
	public static boolean isConsonant(char c) {
		return !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
	}
}
