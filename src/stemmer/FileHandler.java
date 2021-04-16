package stemmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

	/*
	 * Generate word list from file.
	 * 
	 * Returns a list of words to be stemmed from the file.
	 * 
	 * A word is some string in the file, separated by whitespace, which contains
	 * only valid Latin letters
	 */
	public static ArrayList<Word> generateWordList(String pathToFile) {
		ArrayList<Word> wordList = new ArrayList<Word>();

		File file = new File(pathToFile);

		Scanner reader = null;

		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] words = line.split("[ \t!?,.;]");
			for (String word : words) {
				// we don't care about preserving case.
				word = word.toLowerCase();

				if (word.length() == 0)
					continue;

				boolean valid = true;
				for (char c : word.toCharArray()) {
					if (!isLatinLetter(c)) {
						valid = false;
						break;
					}
				}

				if (valid)
					wordList.add(new Word(word));
			}
		}

		return wordList;
	}

	public static void stemmedWordFileWriter(ArrayList<Word> stemmedWords) {
		try {
			FileWriter out = new FileWriter("stemmedWords.txt");

			for (Word word : stemmedWords) {
				out.write(word.getRawString()+"\n");
			}
			out.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	private static boolean isLatinLetter(char c) {
		int cp = (int) c; // ascii code point of c
		// return if c is a-z but not w
		return (cp >= 97 && cp <= 122) && cp != 119;
	}

}
