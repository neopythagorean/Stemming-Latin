package stemmer.expressions;

import stemmer.Word;

public class DoubleConsonantExpression extends Expression {

	public DoubleConsonantExpression(String Source) {
		super(Source);
	}
	
	public boolean evaluate(Word w, Word wp) {
		return w.doubleConsonantEnding();
	}
	
}
