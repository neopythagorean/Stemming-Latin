package stemmer.expressions;

import stemmer.Word;

public class DoubleConsonantExpression extends Expression {

	public DoubleConsonantExpression(String Source) {
		super(Source);
	}
	
	public boolean evaluate(Word w) {
		return w.doubleConsonantEnding();
	}
	
}
