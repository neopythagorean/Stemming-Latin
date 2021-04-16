package stemmer;

import java.util.ArrayList;
import java.util.HashMap;

public class LatinStemmer {
	public static void main(String[] args) {
		ArrayList<Word> words = FileHandler.generateWordList(args[0]);
		
		HashMap<String, RuleBin> ruleBins = RuleGenerator.generateRulesList(args[1]);
		
		
//		RuleBin b = new RuleBin("FAKE_BIN");
//		HashMap<String, RuleBin> bins = new HashMap<String, RuleBin>();
//		bins.put("FAKE_BIN", b);
//		Rule r = new Rule("(& (m>0) (| (*A) (*I))) BILIS -> B", bins, b);
//		System.out.println("-------");
//		Word out = null;
//		if (r.ruleCheckApplies(u))
//			out = r.ruleApply(u);
//		System.out.println(out.getRawString());
		
		Word u = new Word("worldhello");
		RuleBin startBin = ruleBins.get("SOME_BIN");
		ArrayList<Rule> applicable = new ArrayList<Rule>();
		for (Rule r : startBin.rules) {
			if (r.ruleCheckApplies(u))
				applicable.add(r);
		}
		applicable.sort((j,i) -> {return i.end.length() - j.end.length();});
		Rule ruleToApply = applicable.get(0);
		Word newWord = ruleToApply.ruleApply(u);
		System.out.println(newWord.getRawString());
	}
}
