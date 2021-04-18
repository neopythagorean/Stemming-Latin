package stemmer;

import java.util.ArrayList;
import java.util.HashMap;

public class LatinStemmer {
	public static void main(String[] args) {
		ArrayList<Word> words = FileHandler.generateWordList(args[0]);
		ArrayList<Word> stemmed = new ArrayList<Word>();
		HashMap<String, RuleBin> ruleBins = RuleGenerator.generateRulesList(args[1]);
		
		for (Word w : words) {
			System.out.println(w.getRawString());
			System.out.println(stemWord(w, ruleBins).getRawString());
		}
		
		//Word u = new Word("aaaaaaaa");
		//System.out.println(stemWord(u, ruleBins).getRawString());
		
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
