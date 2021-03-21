package stemmer;

import java.util.ArrayList;

public class LatinStemmer {
	public static void main(String[] args) {
		ArrayList<String> words = FileHandler.generateWordList(args[0]);
		for (String word : words) {
			System.out.println(word);
		}
	}
}
