package stemmer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LatinStemmer {
	public static void main(String[] args) {
		HashMap<String, RuleBin> ruleBins = RuleGenerator.generateRulesList(args[0]);
		
		if (args.length > 1) {
			ArrayList<Word> words = FileHandler.generateWordList(args[1]);
			ArrayList<Word> stemmed = new ArrayList<Word>();
			for (Word w : words) {
				stemmed.add(stemWord(w, ruleBins));
			}
			FileHandler.stemmedWordFileWriter(stemmed);
			return;
		}
		
		Scanner in = new Scanner(System.in);
		String s = "";
		for (;;s = in.nextLine())
			System.out.println(stemWord(new Word(s), ruleBins).getRawString());
	}
	
	public static Word stemWord(Word w, HashMap<String, RuleBin> ruleBins) {
		
		RuleBin currentBin = ruleBins.get("START");
		
		Word word = w;
		
		while (currentBin != null) {
			ArrayList<Rule> applicable = new ArrayList<Rule>();
			for (Rule r : currentBin.rules)
				if (r.ruleCheckApplies(word))
					applicable.add(r);
			
			if (applicable.isEmpty()) {
				currentBin = currentBin.defaultTransition;
				continue;
			}
			
			Rule ruleToApply = applicable.get(0);
			for (Rule r : applicable)
				if (r.end.length() > ruleToApply.end.length())
					ruleToApply = r;
			
			word = ruleToApply.ruleApply(word);
			currentBin = ruleToApply.transition;
		}
		
		return word;
	}
}
