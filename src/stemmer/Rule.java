package stemmer;

import java.util.HashMap;

import stemmer.expressions.Expression;

public class Rule {
	// Raw text of the rule as it appears in the .stem file
	String rawRule;
	
	// Left side of rule
	String context;
	String end;
	// Right side of rule
	String production;
	String addition;
	
	Expression condition;
	
	RuleBin transition;
	
	public Rule(String raw, HashMap<String, RuleBin> bins, RuleBin currentBin) {
		this.rawRule = raw;
		
		String[] split = raw.split(" -> ");
		
		this.context = split[0];
		// Might be able to get rid of this if
		if (context.startsWith("(")) {
			int stk = 0;
			int i = 0;
			while (i < context.length()) {
				char c = context.charAt(i);
				if (c == '(') stk++;
				else if (c == ')') stk--;
				i++;
				if (stk == 0) break;
			}
			this.end = context.substring(i).strip();
		} else {
			this.end = context;
		}
		
		this.production = split[1];
		String[] prodSplit = production.split(" ");
		if (prodSplit.length == 2) {
			String targetbin = prodSplit[1].substring(1, prodSplit[1].length()-1);
			this.transition = bins.get(targetbin);
		} else {
			this.transition = currentBin.defaultTransition;
		}
		this.addition = prodSplit[0];
	}
	
	public String toString() {
		return "RULE: " + end + " -> " + addition + " (" + (transition==null?"null":transition.identifier) + ")";
	}
	
}
