package stemmer;

public class Rule {
	// Raw text of the rule as it appears in the .stem file
	String rawRule;
	
	// Left side of rule
	String context;
	// Right side of rule
	String production;
	
	RuleBin transition;
	
	public Rule(String raw) {
		this.rawRule = raw;
		String[] split = raw.split(" -> ");
		this.context = split[0];
		this.production = split[1];
	}
	
}
