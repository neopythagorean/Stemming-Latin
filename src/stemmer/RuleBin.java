package stemmer;

import java.util.ArrayList;

public class RuleBin {
	
	static ArrayList<RuleBin> ruleBins = new ArrayList<RuleBin>();
	
	String identifier;
	ArrayList<Rule> rules;
	
	public RuleBin(String identifier) {
		this.identifier = identifier;
		this.rules = new ArrayList<Rule>();
	}
	
	public static RuleBin getRuleBinByIdentifier(String identifier) {
		for (RuleBin b : ruleBins) {
			if (b.identifier.equals(identifier)) {
				return b;
			}
		}
		return null;
	}
	
}
