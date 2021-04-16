package stemmer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
			this.end = context.substring(i).trim();
			
			// Generate the Condition
			String condition = context.substring(0, i);
			
			Constructor<? extends Expression> constr = Expression.getConstructor(Expression.getExpressionType(condition));
			try {
				this.condition = (Expression) constr.newInstance(condition);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
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
		if (this.addition.equals("λ")) this.addition = "";
	}
	
	public boolean ruleCheckApplies(Word wordToCheck) {
		String s = wordToCheck.getRawString();
		return wordToCheck.endsIn(this.end) && 
				(condition == null || condition.evaluate(wordToCheck, new Word(s.substring(0, s.length() - end.length())))); 
		
	}
	
	public Word ruleApply(Word wordToApply) {
		String raw = wordToApply.getRawString();
		int lenghtOfEnd = this.end.length();
		raw = raw.substring(0, raw.length()-lenghtOfEnd);
		raw += this.addition.toLowerCase();
		return new Word(raw);
		
	}
	
	public String toString() {
		return "RULE: " + end + " -> " + addition + " (" + (transition==null?"null":transition.identifier) + ")";
	}
	
}
