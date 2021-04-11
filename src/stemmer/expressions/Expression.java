package stemmer.expressions;

import stemmer.Word;

public abstract class Expression {
	
	String source;
	
	public Expression(String source) {
		this.source = source;
	}
	
	public static Class<? extends Expression> getExpressionTyoe(String source) {
		String s = source.substring(1, source.length()-1);
		String[] split = s.split(" ");
		if (s.charAt(0) == '&') {
			return AndExpression.class;
		} else if (s.charAt(0) == '|') {
			return OrExpression.class;
		} else if (s.charAt(0) == '!') {
			return NotExpression.class;
		} else if (s.charAt(0) == 'm') {
			return NotExpression.class;
		} else if (split[0].equals("*d*")) {
			return DoubleConsonantExpression.class;
		} else if (split[0].matches("\\*[A-Z]+")) {
			return EndsInExpression.class;
		}
		return Expression.class;
	}
	
	public abstract boolean evaluate(Word w);
}
