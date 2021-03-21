package stemmer;

import java.util.ArrayList;

public class LatinStemmer {
	public static void main(String[] args) {
		ArrayList<Word> words = FileHandler.generateWordList(args[0]);
		
		for (Word w : words) {
			System.out.println(w.breakupWord());
		}
	}
}
